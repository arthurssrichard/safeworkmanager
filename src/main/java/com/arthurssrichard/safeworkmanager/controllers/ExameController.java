package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.dtos.ExameDTO;
import com.arthurssrichard.safeworkmanager.models.*;
import com.arthurssrichard.safeworkmanager.repositories.CargoRepository;
import com.arthurssrichard.safeworkmanager.repositories.ExameRepository;
import com.arthurssrichard.safeworkmanager.repositories.ItemExameRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping(value = "/exames")
public class ExameController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private ItemExameRepository itemExameRepository;
    @Autowired
    private CargoRepository cargoRepository;


    @GetMapping("")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("exames/index");
        Usuario usuario = usuarioService.getLoggedUser();
        List<Exame> exames = exameRepository.findByEmpresa(usuario.getEmpresa());
        mv.addObject("exames",exames);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(ExameDTO exameDTO) {
        ModelAndView mv = new ModelAndView("exames/new");
        Usuario usuario = usuarioService.getLoggedUser();
        List<Cargo> cargosList = cargoRepository.findByEmpresa(usuario.getEmpresa());
        mv.addObject(exameDTO);
        mv.addObject("cargosList", cargosList);
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid ExameDTO exameDTO) {
        List<String> nomesNumericos = exameDTO.getNomeDadoNumerico();
        List<Double> maximosEsperados = exameDTO.getMaximoEsperado();
        List<Double> minimosEsperados = exameDTO.getMinimoEsperado();

        List<String> nomesBooleanos = exameDTO.getNomeDadoBooleano();
        List<String> resultadosBooleanosEsperados = exameDTO.getResultadoBooleanoEsperado();

        String nome = exameDTO.getNome();
        String descricao = exameDTO.getDescricao();

        List<Cargo> cargos = cargoRepository.findAllById(exameDTO.getCargos());

        Usuario usuario = usuarioService.getLoggedUser();

        if(usuario != null){
            Exame exame = new Exame();
            exame.setEmpresa(usuario.getEmpresa());
            exame.setNome(nome);
            exame.setDescricao(descricao);

            // setando cargos
            HashSet<Cargo> cargosSet = new HashSet<>(cargos);
            exame.setCargos(cargosSet);
            for(Cargo cargo : cargosSet){
                cargo.getExames().add(exame);
            }

            exameRepository.save(exame);

            // processando itens de resultado booleano
            if(nomesBooleanos != null){
                for(int i = 0; i < nomesBooleanos.size(); i++){
                    if(nomesBooleanos.get(i) != null){
                        boolean resultadoEsperado = resultadosBooleanosEsperados.get(i).equalsIgnoreCase("true");
                        ItemExame novoItem = new ItemExame(usuario.getEmpresa(), exame, nomesBooleanos.get(i), TipoDado.BOOLEANO, resultadoEsperado);
                        itemExameRepository.save(novoItem);
                    }
                }
            }


            //processando itens de resultado numerico
            if(nomesNumericos != null){
                for(int i = 0; i < nomesNumericos.size(); i++){
                    if(nomesNumericos.get(i) != null){
                        ItemExame novoItem = new ItemExame(usuario.getEmpresa(), exame, nomesNumericos.get(i),
                                TipoDado.NUMERICO, minimosEsperados.get(i), maximosEsperados.get(i));
                        itemExameRepository.save(novoItem);
                    }
                }
            }


            System.out.print("Novo exame salvo com sucesso ****************************************************");
        }

        return new ModelAndView("redirect:/exames");
    }

    @GetMapping("/{exameId}/edit")
    public ModelAndView edit(@PathVariable int exameId, ExameDTO exameDTO) {
        ModelAndView mv = new ModelAndView("redirect:/exames/"+exameId);

        return mv;
    }

    @GetMapping("/{exameId}")
    public ModelAndView show(@PathVariable int exameId, ExameDTO exameDTO) {
        ModelAndView mv = new ModelAndView("exames/show");

        Optional<Exame> optExame = exameRepository.findById(exameId);
        if (optExame.isEmpty()) {
            return new ModelAndView("redirect:/exames"); // tratamento de erro
        }

        Exame exame = optExame.get();
        exameDTO.setNome(exame.getNome());
        exameDTO.setDescricao(exame.getDescricao());
        exameDTO.setCargosEdit(exame.getCargos());
        exameDTO.setItensExameEdit(exame.getItensExame());

        mv.addObject("exameDTO", exameDTO);
        return mv;
    }


    @PostMapping("/{exameId}")
    public ModelAndView update(@PathVariable int exameId, @Valid ExameDTO exameDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/exames/" + exameId + "/edit");
        }

        Optional<Exame> optExame = exameRepository.findById(exameId);
        if (optExame.isEmpty()) {
            // tratamento de erro
            return new ModelAndView("redirect:/exames");
        }

        Exame exame = optExame.get();
        exame.setNome(exameDTO.getNome());
        exame.setDescricao(exameDTO.getDescricao());

        // Atualizando cargos associados ao exame
        List<Cargo> cargos = cargoRepository.findAllById(exameDTO.getCargos());
        exame.setCargos(new HashSet<>(cargos));

        // Atualizando itens de exame (primeiro remover os antigos)
        itemExameRepository.deleteAll(exame.getItensExame());
        exame.getItensExame().clear();

        // Recriando itens
        List<String> nomesNumericos = exameDTO.getNomeDadoNumerico();
        List<Double> maximosEsperados = exameDTO.getMaximoEsperado();
        List<Double> minimosEsperados = exameDTO.getMinimoEsperado();

        List<String> nomesBooleanos = exameDTO.getNomeDadoBooleano();
        List<String> resultadosBooleanosEsperados = exameDTO.getResultadoBooleanoEsperado();

        // Processando itens de resultado booleano
        if (nomesBooleanos != null) {
            for (int i = 0; i < nomesBooleanos.size(); i++) {
                if (nomesBooleanos.get(i) != null) {
                    boolean resultadoEsperado = resultadosBooleanosEsperados.get(i).equalsIgnoreCase("true");
                    ItemExame novoItem = new ItemExame(exame.getEmpresa(), exame, nomesBooleanos.get(i), TipoDado.BOOLEANO, resultadoEsperado);
                    exame.getItensExame().add(novoItem);
                }
            }
        }

        // Processando itens de resultado numérico
        if (nomesNumericos != null) {
            for (int i = 0; i < nomesNumericos.size(); i++) {
                if (nomesNumericos.get(i) != null) {
                    ItemExame novoItem = new ItemExame(exame.getEmpresa(), exame, nomesNumericos.get(i), TipoDado.NUMERICO, minimosEsperados.get(i), maximosEsperados.get(i));
                    exame.getItensExame().add(novoItem);
                }
            }
        }

        exameRepository.save(exame);

        return new ModelAndView("redirect:/exames");
    }


}
