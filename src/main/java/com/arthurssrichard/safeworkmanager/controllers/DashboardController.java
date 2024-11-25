package com.arthurssrichard.safeworkmanager.controllers;

import com.arthurssrichard.safeworkmanager.config.UsuarioService;
import com.arthurssrichard.safeworkmanager.models.Usuario;
import com.arthurssrichard.safeworkmanager.repositories.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    private DashboardRepository dashboardRepository;

    @Autowired
    public DashboardController(DashboardRepository dashboardRepository){
        this.dashboardRepository = dashboardRepository;
    }

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("dashboard/index");
        return mv;
    }

    @GetMapping("/dashboard/exames-inadequados")
    public ResponseEntity<List<Map<String, Object>>> getExamesInadequadosSetor() {
        Usuario usuario = usuarioService.getLoggedUser();
        List<Map<String, Object>> resultados = dashboardRepository.findExamesInadequadosPorSetor(usuario.getEmpresa().getId());
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/dashboard/funcionarios-com-mais-exames-inadequados")
    public ResponseEntity<List<Map<String, Object>>> getFuncionariosComMaisExamesInadequados() {
        Usuario usuario = usuarioService.getLoggedUser();
        List<Map<String, Object>> resultados = dashboardRepository.findFuncionariosComMaisExamesInadequados(usuario.getEmpresa().getId());
        return ResponseEntity.ok(resultados);
    }

}
