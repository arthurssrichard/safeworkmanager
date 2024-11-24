package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.models.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private UsuarioService usuarioService;

    @ModelAttribute("servletPath")
    public String getRequestServletPath(HttpServletRequest request){
        return request.getServletPath();
    }

    @ModelAttribute("loggedUser")
    public Usuario getLoggedUser(Usuario usuario){
        usuario = usuarioService.getLoggedUser();
        return usuario;
    }
}
