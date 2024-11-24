package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.dtos.UsuarioDTO;
import com.arthurssrichard.safeworkmanager.models.NivelAcesso;
import com.arthurssrichard.safeworkmanager.models.Usuario;
import com.arthurssrichard.safeworkmanager.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.Optional;


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

    @GetMapping("usuarios/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> optional = this.usuarioRepository.findById(id);

        if (optional.isPresent()) {
            Usuario usuario = optional.get();
            usuarioDTO.fromUsuario(usuario);

            ModelAndView mv = new ModelAndView("usuarios/edit");
            mv.addObject("usuarioDTO", usuarioDTO);
            mv.addObject("listNivelAcesso", NivelAcesso.values());
            mv.addObject("usuarioId", id);
            return mv;
        } else {
            System.out.printf("Falha, ID não encontrado!");
            return new ModelAndView("redirect:/usuarios");
        }
    }
    @PostMapping("usuarios/{id}")
    public ModelAndView update(@PathVariable Integer id, @Valid UsuarioDTO usuarioDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("usuarios/edit");
            mv.addObject("usuarioDTO", usuarioDTO);
            mv.addObject("listNivelAcesso", NivelAcesso.values());
            return mv;
        }

        Optional<Usuario> optional = this.usuarioRepository.findById(id);

        if (optional.isPresent()) {
            Usuario usuario = optional.get(); // Obter o usuário existente
            usuarioDTO.toUsuario(usuario);    // Atualizar os dados no mesmo objeto

            // Atualizar a senha (com encriptação)
            usuario.setSenha(bCryptPasswordEncoder.encode(usuarioDTO.getSenha()));

            this.usuarioRepository.save(usuario); // Salvar as alterações
            return new ModelAndView("redirect:/usuarios");
        } else {
            System.out.printf("Falha, usuário de id %d não encontrado!", id);
            return new ModelAndView("redirect:/usuarios");
        }
    }
    @GetMapping("usuarios/{id}/delete")
    public ModelAndView delete(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView("redirect:/usuarios");

        try {
            this.usuarioRepository.deleteById(id);
            mv.addObject("mensagem", "Usuário #" + id + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
            mv = retornaErroUsuario("DELETE ERROR: Usuário #" + id + " não encontrado no banco!");
        }

        return mv;
    }

    private ModelAndView retornaErroUsuario(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/usuarios");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }


}