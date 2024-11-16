package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.models.Usuario;
import com.arthurssrichard.safeworkmanager.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UsuarioController {
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }
    @GetMapping("/usuarios")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("usuarios/index");
        Usuario usuario = usuarioService.getLoggedUser();
        List<Usuario> usuarios = usuarioRepository.findByEmpresa(usuario.getEmpresa());
        mv.addObject("usuarios", usuarios);
        return mv;
    }
    @GetMapping("/usuarios/new")
    public ModelAndView nnew(){
        ModelAndView mv = new ModelAndView("usuarios/new");
        return mv;
    }
}
