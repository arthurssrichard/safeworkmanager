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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/{cargoId}/edit")
    public ModelAndView edit(@PathVariable int cargoId, CargoDTO cargoDTO){
        Optional<Cargo> optCargo = cargoRepository.findById(cargoId);
        if(optCargo.isEmpty()){
            // tratamento de erro
        }
        Usuario usuario = usuarioService.getLoggedUser();
        List<Risco> riscos = riscoRepository.findByEmpresa(usuario.getEmpresa());

        Cargo cargo = optCargo.get();
        cargoDTO.setNome(cargo.getNome());
        cargoDTO.setDescricao(cargo.getDescricao());
        cargoDTO.setRiscosEdit(cargo.getRiscos());


        ModelAndView mv = new ModelAndView("cargos/edit");
        mv.addObject("cargoDTO", cargoDTO);
        mv.addObject("riscos", riscos);
        mv.addObject("cargoId", cargoId);

        return mv;
    }

    @PostMapping("/{cargoId}")
    public ModelAndView update(@Valid CargoDTO cargoDTO, BindingResult bindingResult, @PathVariable int cargoId){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("/cargos/edit");
            mv.addObject("cargoId", cargoId);
            System.out.println("******************************* BINDING RESULT");
            return mv;
        }
        Optional<Cargo> cargoOpt = cargoRepository.findById(cargoId);
        if(cargoOpt.isEmpty()){
            // trtamento de erro
            ModelAndView mv = new ModelAndView("/cargos/edit");
            System.out.println("******************************* OPTIONAL");
            return mv;
        }
        Usuario usuario = usuarioService.getLoggedUser();
        ModelAndView mv = new ModelAndView("redirect:/cargos");
        Cargo cargo = cargoOpt.get();
        cargo.setNome(cargoDTO.getNome());
        cargo.setDescricao(cargoDTO.getDescricao());
        cargo.setEmpresa(usuario.getEmpresa());

        List<Risco> riscos = riscoRepository.findAllById(cargoDTO.getRiscos());
        Set<Risco> riscosSet = new HashSet<>(riscos);
        cargo.setRiscos(riscosSet);
        cargoRepository.save(cargo);

        return mv;
    }
}
