package com.arthurssrichard.safeworkmanager.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

@Repository
public class DashboardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> findExamesInadequadosPorSetor(int empresaId) {
        String sql = """
            SELECT 
                s.nome AS setorNome, 
                COUNT(ex.id) AS quantExamesInadequados
            FROM 
                setor s
            JOIN 
                funcionario f ON f.setor_id = s.id
            JOIN 
                examinacao ex ON ex.funcionario_id = f.id
            JOIN 
                item_examinacao iex ON iex.examinacao_id = ex.id
            JOIN 
                item_exame ie ON ie.id = ex.exame_id 
            WHERE 
                s.empresa_id = :empresaId
                AND (
                    (
                        ie.tipo_dado = 'NUMERICO' 
                        AND (
                            iex.resultado_numerico < ie.resultado_valor_minimo
                            OR iex.resultado_numerico > ie.resultado_valor_maximo
                        )
                    ) 
                    OR (
                        ie.tipo_dado = 'BOOLEANO' 
                        AND iex.resultado_booleano != ie.resultado_booleano
                    )
                )
            GROUP BY 
                s.nome
        """;

        // Criação da Query
        List<Object[]> results = entityManager.createNativeQuery(sql)
                .setParameter("empresaId", empresaId)
                .getResultList();

        // Conversão dos resultados para uma lista de mapas
        return results.stream()
                .map(row -> Map.of(
                        "setorNome", row[0],
                        "quantExamesInadequados", row[1]
                ))
                .toList();
    }

    public List<Map<String, Object>> findFuncionariosComMaisExamesInadequados(int empresaId) {
        String sql = """
        WITH exames_inadequados AS (
            SELECT 
                setor.id AS setor_id,
                setor.nome AS setor_nome,
                funcionario.id AS funcionario_id,
                funcionario.nome AS funcionario_nome,
                COUNT(examinacao.id) AS quant_exames_inadequados
            FROM 
                setor
            JOIN 
                funcionario ON funcionario.setor_id = setor.id
            JOIN 
                examinacao ON examinacao.funcionario_id = funcionario.id
            JOIN 
                item_examinacao ON item_examinacao.examinacao_id = examinacao.id
            JOIN 
                item_exame ON item_exame.id = examinacao.exame_id
            WHERE 
                setor.empresa_id = :empresaId
                AND (
                    (
                        item_exame.tipo_dado = 'NUMERICO' 
                        AND (
                            item_examinacao.resultado_numerico < item_exame.resultado_valor_minimo
                            OR item_examinacao.resultado_numerico > item_exame.resultado_valor_maximo
                        )
                    ) 
                    OR (
                        item_exame.tipo_dado = 'BOOLEANO' 
                        AND item_examinacao.resultado_booleano != item_exame.resultado_booleano
                    )
                )
            GROUP BY 
                setor.id, setor.nome, funcionario.id, funcionario.nome
        ),
        max_exames_inadequados AS (
            SELECT 
                setor_id,
                MAX(quant_exames_inadequados) AS max_exames
            FROM 
                exames_inadequados
            GROUP BY 
                setor_id
        )
        SELECT 
            ei.setor_nome,
            ei.funcionario_nome,
            ei.quant_exames_inadequados
        FROM 
            exames_inadequados ei
        JOIN 
            max_exames_inadequados mei ON ei.setor_id = mei.setor_id AND ei.quant_exames_inadequados = mei.max_exames
        ORDER BY 
            ei.setor_nome
    """;

        List<Object[]> results = entityManager.createNativeQuery(sql)
                .setParameter("empresaId", empresaId)
                .getResultList();

        return results.stream()
                .map(row -> Map.of(
                        "setorNome", row[0],
                        "funcionarioNome", row[1],
                        "quantExamesInadequados", row[2]
                ))
                .toList();
    }

}