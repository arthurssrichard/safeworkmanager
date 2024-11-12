package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.dtos.ExameDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/exames")
public class ExameController {
    @GetMapping("/new")
    public ModelAndView nnew(ExameDTO exameDTO) {
        ModelAndView mv = new ModelAndView("exames/new");
        mv.addObject(exameDTO);
        return mv;
    }
}
