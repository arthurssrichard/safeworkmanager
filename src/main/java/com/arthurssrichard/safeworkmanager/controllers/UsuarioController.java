package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.dtos.UsuarioDTO;
import com.arthurssrichard.safeworkmanager.models.NivelAcesso;
import com.arthurssrichard.safeworkmanager.models.Usuario;
import com.arthurssrichard.safeworkmanager.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



import java.util.List;


@Controller
public class UsuarioController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
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
    public ModelAndView nnew(UsuarioDTO usuarioDTO){
        ModelAndView mv = new ModelAndView("usuarios/new");
        mv.addObject("usuarioDTO", usuarioDTO);
        mv.addObject("listNivelAcesso", NivelAcesso.values());

        return mv;
    }

    @PostMapping("/usuarios")
    public ModelAndView create(@Valid UsuarioDTO usuarioDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("usuarios/new");
            return mv;
        }
        Usuario loggedUser = usuarioService.getLoggedUser();

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmpresa(loggedUser.getEmpresa());
        usuario.setNivelAcesso(usuarioDTO.getNivelAcesso());
        String senha = bCryptPasswordEncoder.encode(usuarioDTO.getSenha());
        usuario.setSenha(senha);
        usuarioRepository.save(usuario);
        ModelAndView mv = new ModelAndView("redirect:/usuarios");
        return mv;
    }
}
