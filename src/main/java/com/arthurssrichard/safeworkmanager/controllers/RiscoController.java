package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.dtos.RiscoDTO;
import com.arthurssrichard.safeworkmanager.models.Agente;
import com.arthurssrichard.safeworkmanager.models.Risco;
import com.arthurssrichard.safeworkmanager.models.Usuario;
import com.arthurssrichard.safeworkmanager.repositories.AgenteRepository;
import com.arthurssrichard.safeworkmanager.repositories.RiscoRepository;
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
@RequestMapping("/riscos")
public class RiscoController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RiscoRepository riscoRepository;
    @Autowired
    private AgenteRepository agenteRepository;

    @GetMapping("")
    public ModelAndView index() {
        Usuario usuario = usuarioService.getLoggedUser();
        List<Risco> riscos = riscoRepository.findByEmpresa(usuario.getEmpresa());
        ModelAndView mv = new ModelAndView("riscos/index");
        mv.addObject("riscos", riscos);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("riscos/new");
        mv.addObject("riscoDTO", new RiscoDTO());
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid RiscoDTO riscoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("riscos/new");
        }
        Usuario usuario = usuarioService.getLoggedUser();

        Risco risco = new Risco();
        risco.setNome(riscoDTO.getNome());
        risco.setDescricao(riscoDTO.getDescricao());
        risco.setObservacao(riscoDTO.getObservacao());
        risco.setEmpresa(usuario.getEmpresa());
        riscoRepository.save(risco);

        List<String> nomes = riscoDTO.getAgenteNome();
        List<String> descricoes = riscoDTO.getAgenteDescricao();

        if(nomes != null && descricoes != null) {
            System.out.println("*** DEBUG 1****");
            for (int i = 0; i < nomes.size(); i++) {
                System.out.println("********** DEBUG 2: "+nomes.get(i));
                Agente agente = new Agente();
                agente.setNome(nomes.get(i));
                agente.setDescricao(descricoes.get(i));
                agente.setRisco(risco); // Associar o agente ao risco
                agente.setEmpresa(usuario.getEmpresa()); // Associar o agente à empresa do usuário logado
                agenteRepository.save(agente);
            }
        }

        return new ModelAndView("redirect:/riscos");
    }

    @GetMapping("/edit")
    public ModelAndView edit() {
        ModelAndView mv = new ModelAndView("riscos/edit");
        return mv;
    }


}
