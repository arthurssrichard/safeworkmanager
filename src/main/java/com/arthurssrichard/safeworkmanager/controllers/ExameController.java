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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;

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
        return new ModelAndView("exames/index");
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

        /*debug*/
//        for(int i = 0; i < nomesBooleanos.size(); i++){
//            if(nomesBooleanos.get(i) != null){
//                System.out.printf("Dado: %s | Esperado: %s \n",nomesBooleanos.get(i),resultadosBooleanosEsperados.get(i));
//            }
//        }
//        for(int i = 0; i < nomesNumericos.size(); i++){
//            System.out.printf("Dado: %s | Min: %s | Max: %s \n",nomesNumericos.get(i),minimosEsperados.get(i),maximosEsperados.get(i));
//        }
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
}
