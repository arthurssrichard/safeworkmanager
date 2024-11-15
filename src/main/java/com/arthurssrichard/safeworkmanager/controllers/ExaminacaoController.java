package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.models.Cargo;
import com.arthurssrichard.safeworkmanager.models.Exame;
import com.arthurssrichard.safeworkmanager.models.Funcionario;
import com.arthurssrichard.safeworkmanager.repositories.CargoRepository;
import com.arthurssrichard.safeworkmanager.repositories.ExameRepository;
import com.arthurssrichard.safeworkmanager.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/funcionarios/{id}/examinacoes")
public class ExaminacaoController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping("/new")
    public ModelAndView nnew(@PathVariable int id){

        Optional<Funcionario> optional = funcionarioRepository.findById(id);

        if(optional.isEmpty()){ //tratamento de erro
            ModelAndView mv = new ModelAndView("redirect:/funcionarios/"+id);
        }
        Funcionario funcionario = optional.get();
        List<Exame> exames = exameRepository.findByCargo(funcionario.getCargo());

        ModelAndView mv = new ModelAndView("funcionarios/examinacoes/new");
        mv.addObject("exames",exames);
        mv.addObject("funcionario",funcionario);
        return mv;
    }


}
