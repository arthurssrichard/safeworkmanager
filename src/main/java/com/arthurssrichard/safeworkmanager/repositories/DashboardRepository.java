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
                item_exame ie ON ie.id = iex.item_exame_id
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
        SELECT
            s.nome AS setorNome,
            f.nome AS funcionarioNome,
            COUNT(iex.id) AS quantExamesInadequados
        FROM empresa AS e
            JOIN setor AS s ON s.empresa_id = e.id
            JOIN funcionario AS f ON f.setor_id = s.id
            JOIN examinacao AS ex ON ex.funcionario_id = f.id
            JOIN item_examinacao AS iex ON iex.examinacao_id = ex.id
            JOIN item_exame AS ie ON iex.item_exame_id = ie.id
        WHERE e.id = :empresaId
        AND (
            (ie.tipo_dado = 'NUMERICO' AND (iex.resultado_numerico < ie.resultado_valor_minimo OR iex.resultado_numerico > ie.resultado_valor_maximo))
            OR (ie.tipo_dado = 'BOOLEANO' AND iex.resultado_booleano != ie.resultado_booleano)
        )
        GROUP BY f.id, s.nome, f.nome;    
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