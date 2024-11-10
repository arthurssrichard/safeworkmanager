package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.dtos.FuncionarioDTO;
import com.arthurssrichard.safeworkmanager.dtos.RiscoDTO;
import com.arthurssrichard.safeworkmanager.dtos.SetorDTO;
import com.arthurssrichard.safeworkmanager.models.Funcionario;
import com.arthurssrichard.safeworkmanager.models.Risco;
import com.arthurssrichard.safeworkmanager.models.Setor;
import com.arthurssrichard.safeworkmanager.models.Usuario;
import com.arthurssrichard.safeworkmanager.repositories.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private UsuarioService usuarioService;

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
        mv.addObject("funcionarioDTO", funcionarioDTO);
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid FuncionarioDTO funcionarioDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("redirect:/funcionarios/new");
        }

        ModelAndView mv = new ModelAndView("redirect:/setores");
        Usuario usuario = usuarioService.getLoggedUser();
        if(usuario != null){
            Funcionario funcionario = new Funcionario();
            // Fazer os sets
            funcionarioRepository.save(funcionario);
        }
        return mv;
    }

}
