package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.dtos.CargoDTO;
import com.arthurssrichard.safeworkmanager.dtos.RiscoDTO;
import com.arthurssrichard.safeworkmanager.dtos.SetorDTO;
import com.arthurssrichard.safeworkmanager.models.*;
import com.arthurssrichard.safeworkmanager.repositories.CargoRepository;
import com.arthurssrichard.safeworkmanager.repositories.RiscoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/cargos")
public class CargoController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private RiscoRepository riscoRepository;

    @GetMapping("")
    public ModelAndView index() {
        Usuario usuario = usuarioService.getLoggedUser();
        List<Cargo> cargos = cargoRepository.findByEmpresa(usuario.getEmpresa());
        ModelAndView mv = new ModelAndView("cargos/index");
        mv.addObject("cargos", cargos);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(CargoDTO cargoDTO) {
        ModelAndView mv = new ModelAndView("cargos/new");
        mv.addObject("cargoDTO",cargoDTO);

        Usuario usuario = usuarioService.getLoggedUser();
        List<Risco> riscos = riscoRepository.findByEmpresa(usuario.getEmpresa());

        mv.addObject("riscos",riscos);

        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid CargoDTO cargoDTO, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView("redirect:/cargos");
        Usuario usuario = usuarioService.getLoggedUser();

        if(usuario != null){
            Cargo cargo = new Cargo();
            cargo.setNome(cargoDTO.getNome());
            cargo.setDescricao(cargoDTO.getDescricao());
            cargo.setEmpresa(usuario.getEmpresa());

            List<Risco> riscos = riscoRepository.findAllById(cargoDTO.getRiscos());
            Set<Risco> riscosSet = new HashSet<>(riscos);
            cargo.setRiscos(riscosSet);

            cargoRepository.save(cargo);
        }
        return mv;
    }
}
