package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.dtos.SetorDTO;
import com.arthurssrichard.safeworkmanager.models.Funcionario;
import com.arthurssrichard.safeworkmanager.models.Setor;
import com.arthurssrichard.safeworkmanager.models.Usuario;
import com.arthurssrichard.safeworkmanager.repositories.EmpresaRepository;
import com.arthurssrichard.safeworkmanager.repositories.FuncionarioRepository;
import com.arthurssrichard.safeworkmanager.repositories.SetorRepository;
import com.arthurssrichard.safeworkmanager.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping(value = "/setores")
public class SetorController {
    @Autowired
    SetorRepository setorRepository;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("setores/index");
        Usuario usuario = usuarioService.getLoggedUser();
        List<Setor> setores = setorRepository.findByEmpresa(usuario.getEmpresa());

        for(Setor setor : setores){
            System.out.println(setor);
        }
        mv.addObject("setores",setores);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(){
        ModelAndView mv = new ModelAndView("setores/new");
        mv.addObject("setorDTO", new SetorDTO());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable int id){
        ModelAndView mv = new ModelAndView("setores/show");

        Optional<Setor> optional = this.setorRepository.findById(id);
        if(optional.isPresent()){
            Setor setor = optional.get();
            mv.addObject("setor", setor);

            Usuario usuario = usuarioService.getLoggedUser();
            List<Funcionario> funcionarios = funcionarioRepository.findBySetor(setor);
            mv.addObject("funcionarios",funcionarios);
        }
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid SetorDTO setorDTO, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView("redirect:/setores");
        Usuario usuario = usuarioService.getLoggedUser();
        if(usuario != null){
            Setor setor = new Setor();
            setor.setNome(setorDTO.getNome());
            setor.setDescricao(setorDTO.getDescricao());
            setor.setEmpresa(usuario.getEmpresa());

            setorRepository.save(setor);
        }
        return mv;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable int id, @Valid SetorDTO setorDTO){
        Optional<Setor> optional = setorRepository.findById(id);
        if(optional.isPresent()){
            Setor setor = optional.get();
            ModelAndView mv = new ModelAndView("setores/edit");

            return mv;
        }else{
            System.out.println("Setor não encontrado");
            return new ModelAndView("redirect:/setores");
        }
    }
}
