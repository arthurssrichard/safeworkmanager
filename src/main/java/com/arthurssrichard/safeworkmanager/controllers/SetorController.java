package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.dtos.SetorDTO;
import com.arthurssrichard.safeworkmanager.models.Setor;
import com.arthurssrichard.safeworkmanager.models.Usuario;
import com.arthurssrichard.safeworkmanager.repositories.EmpresaRepository;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/setores")
public class SetorController {
    @Autowired
    SetorRepository setorRepository;

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("setores/index");
        List<Setor> setores = setorRepository.findAll();

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

    @PostMapping("")
    public ModelAndView create(@Valid SetorDTO setorDTO, BindingResult bindingResult){
        System.out.println("************** DEBUG NEW SETOR**********************");
        System.out.println("************** DEBUG NEW SETOR**********************");
        System.out.println("************** DEBUG NEW SETOR**********************");


        ModelAndView mv = new ModelAndView("redirect:/setores");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Usuario usuario = usuarioRepository.findByNome(username);

        if(usuario != null){
            Setor setor = new Setor();
            setor.setNome(setorDTO.getNome());
            setor.setDescricao(setorDTO.getDescricao());
            setor.setEmpresa(usuario.getEmpresa());

            setorRepository.save(setor);
        }

        return mv;
    }
}
