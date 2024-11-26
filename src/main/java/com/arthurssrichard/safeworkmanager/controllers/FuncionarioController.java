package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.dtos.FuncionarioDTO;
import com.arthurssrichard.safeworkmanager.dtos.RiscoDTO;
import com.arthurssrichard.safeworkmanager.dtos.SetorDTO;
import com.arthurssrichard.safeworkmanager.models.*;
import com.arthurssrichard.safeworkmanager.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private ExameRepository exameRepository;

    @GetMapping("")
    public ModelAndView index() {
        Usuario usuario = usuarioService.getLoggedUser();
        List<Funcionario> funcionarios = funcionarioRepository.findByEmpresa(usuario.getEmpresa());
        ModelAndView mv = new ModelAndView("funcionarios/index");
        mv.addObject("funcionarios", funcionarios);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(FuncionarioDTO funcionarioDTO) {
        ModelAndView mv = new ModelAndView("funcionarios/new");
        Usuario usuario = usuarioService.getLoggedUser();

        List<Setor> setores = setorRepository.findByEmpresa(usuario.getEmpresa());
        List<Cargo> cargos = cargoRepository.findByEmpresa(usuario.getEmpresa());

        mv.addObject("funcionarioDTO", funcionarioDTO);
        mv.addObject("cargos", cargos);
        mv.addObject("setores", setores);
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid FuncionarioDTO funcionarioDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("redirect:/funcionarios/new");
        }

        ModelAndView mv = new ModelAndView("redirect:/funcionarios");
        Usuario usuario = usuarioService.getLoggedUser();

        Optional<Cargo> optionalCargo = cargoRepository.findById(funcionarioDTO.getIdCargo());
        Optional<Setor> optionalSetor = setorRepository.findById(funcionarioDTO.getIdSetor());


        if(usuario != null && optionalCargo.isPresent() && optionalSetor.isPresent()){

            Funcionario funcionario = new Funcionario();
            funcionario.setEmpresa(usuario.getEmpresa());

            funcionario.setNome(funcionarioDTO.getNome());
            funcionario.setDataAdmissao(funcionarioDTO.getDataAdmissao());

            funcionario.setCargo(optionalCargo.get());
            funcionario.setSetor(optionalSetor.get());

            funcionarioRepository.save(funcionario);
        }
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable int id){

        Optional<Funcionario> optional = funcionarioRepository.findById(id);

        if(optional.isEmpty()){
            ModelAndView mv = new ModelAndView("redirect:/funcionarios");
        }
        Funcionario funcionario = optional.get();
        ModelAndView mv = new ModelAndView("funcionarios/show");

        List<Exame> exames = exameRepository.findByCargo(funcionario.getCargo());
        mv.addObject("funcionario",funcionario);
        mv.addObject("exames",exames);
        return mv;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Integer id){
        ModelAndView mv = new ModelAndView("redirect:/funcionarios");
        try{
            funcionarioRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            System.out.println("Erro ao deletar funcionário: " + e.getMessage());
        }
        return mv;
    }


}
