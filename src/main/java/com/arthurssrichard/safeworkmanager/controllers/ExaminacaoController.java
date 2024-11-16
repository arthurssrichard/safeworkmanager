package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.dtos.ExaminacaoDTO;
import com.arthurssrichard.safeworkmanager.models.*;
import com.arthurssrichard.safeworkmanager.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/funcionarios/{funId}/examinacoes")
public class ExaminacaoController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private ItemExameRepository itemExameRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ItemExaminacaoRepository itemExaminacaoRepository;
    @Autowired
    private ExaminacaoRepository examinacaoRepository;

    @GetMapping("/{exId}/new")
    public ModelAndView nnew(@PathVariable int funId, @PathVariable int exId, ExaminacaoDTO examinacaoDTO) {

        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(funId);
        Optional<Exame> exameOpt = exameRepository.findById(exId);

        if(funcionarioOpt.isEmpty() || exameOpt.isEmpty()){ //tratamento de erro
            ModelAndView mv = new ModelAndView("redirect:/funcionarios/"+funId);
        }
        Funcionario funcionario = funcionarioOpt.get();
        Exame exame = exameOpt.get();

        ModelAndView mv = new ModelAndView("funcionarios/examinacoes/new");
        mv.addObject("exame",exame);
        mv.addObject("funcionario",funcionario);
        mv.addObject("examinacaoDTO",examinacaoDTO);
        return mv;
    }
    @PostMapping
    public ModelAndView create(@PathVariable int funId, @Valid ExaminacaoDTO examinacaoDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("redirect:/funcionarios/"+funId);
        }
        ModelAndView mv = new ModelAndView("redirect:/funcionarios/"+funId);
        Usuario usuario = usuarioService.getLoggedUser();
        List<Double> numericos = examinacaoDTO.getResultadoNumerico();
        List<String> nomesNumericos = examinacaoDTO.getNomesNumericos();

        List<String> booleanos = examinacaoDTO.getResultadoBooleano();
        List<String> nomesBooleanos = examinacaoDTO.getNomesBooleanos();

        Examinacao examinacao = new Examinacao();
        examinacao.setDataRealizada(examinacaoDTO.getDataRealizada());
        examinacao.setEmpresa(usuario.getEmpresa());

        // setando funcionario e exame da exminacao
        Optional<Exame> optionalExame = exameRepository.findById(examinacaoDTO.getIdExame());
        Exame exame = optionalExame.get();
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(funId);
        Funcionario funcionario = optionalFuncionario.get();

        examinacao.setExame(exame);
        examinacao.setFuncionario(funcionario);
        examinacaoRepository.save(examinacao);
        /* iniciando preenchimento dos campos */
        // BOOLEANOS
        if(booleanos != null){//verificar se tem algum checkbox selecionado na examinacao
            for (int i = 0; i < nomesBooleanos.size(); i++) {
                    ItemExaminacao itemExaminacao = new ItemExaminacao();
                    itemExaminacao.setTipoDado(TipoDado.BOOLEANO);
                    itemExaminacao.setNomeDado(nomesBooleanos.get(i));
                    itemExaminacao.setEmpresa(usuario.getEmpresa());
                    itemExaminacao.setExaminacao(examinacao);

                    if(booleanos.get(i) != null){//verificar se a checkbox da vez foi selecionada
                        System.out.println("Selecionado");
                        itemExaminacao.setResultadoBooleano(true);
                    }else{
                        System.out.println("Não selecionado");
                        itemExaminacao.setResultadoBooleano(false);
                    }
                itemExaminacaoRepository.save(itemExaminacao);
                }
        }else{//caso nao tenha nenhuma checkbox selecionada
            System.out.println("Nada selecionado");
            for(int i = 0; i < nomesBooleanos.size(); i++){
                ItemExaminacao itemExaminacao = new ItemExaminacao();
                itemExaminacao.setTipoDado(TipoDado.BOOLEANO);
                itemExaminacao.setNomeDado(nomesBooleanos.get(i));
                itemExaminacao.setResultadoBooleano(false);
                itemExaminacao.setEmpresa(usuario.getEmpresa());
                itemExaminacao.setExaminacao(examinacao);
                itemExaminacaoRepository.save(itemExaminacao);
            }
        }

        // NUMERICOS
        if(numericos != null){
            for(int i = 0; i < nomesNumericos.size(); i++){
                ItemExaminacao itemExaminacao = new ItemExaminacao();
                itemExaminacao.setTipoDado(TipoDado.NUMERICO);
                itemExaminacao.setNomeDado(nomesNumericos.get(i));
                itemExaminacao.setResultadoNumerico(numericos.get(i));
                itemExaminacao.setEmpresa(usuario.getEmpresa());
                itemExaminacao.setExaminacao(examinacao);
                itemExaminacaoRepository.save(itemExaminacao);
            }
        }else{
            System.out.println("Nada selecionado");
        }
        return mv;
    }


}
