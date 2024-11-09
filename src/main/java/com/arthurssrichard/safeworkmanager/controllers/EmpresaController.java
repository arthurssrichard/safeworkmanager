package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.dtos.EmpresaDTO;
import com.arthurssrichard.safeworkmanager.models.Empresa;
import com.arthurssrichard.safeworkmanager.models.NivelAcesso;
import com.arthurssrichard.safeworkmanager.models.Usuario;
import com.arthurssrichard.safeworkmanager.repositories.EmpresaRepository;
import com.arthurssrichard.safeworkmanager.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class EmpresaController {

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/empresas/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("empresas/new");
        mv.addObject("empresaDTO", new EmpresaDTO());
        return mv;
    }

    @PostMapping("/empresas")
    public ModelAndView create(@Valid EmpresaDTO request, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("dashboard/index");

        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }

        // empresa
        Empresa empresa = new Empresa();
        empresa.setNome(request.getNomeEmpresa());
        empresa.setCnpj(request.getCnpjEmpresa());
        empresa.setDataCriacao(LocalDate.now());
        empresaRepository.save(empresa);

        //usuario
        Usuario administrador = new Usuario();
        administrador.setNome(request.getNomeAdministrador());
        String senha = bCryptPasswordEncoder.encode(request.getSenhaAdministrador());
        administrador.setSenha(senha);
        administrador.setNivelAcesso(NivelAcesso.ADMINISTRADOR);
        administrador.setEmpresa(empresa);
        usuarioRepository.save(administrador);

        return mv;
    }


}
