package com.minimarket.minimarket.controller;

import com.minimarket.minimarket.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PanelController {

    @GetMapping("/admin")
    public String admin(HttpSession session, Model model) {
        return abrirPanel(session, model, "ADMIN");
    }

    @GetMapping("/supervisor")
    public String supervisor(HttpSession session, Model model) {
        return abrirPanel(session, model, "SUPERVISOR");
    }

    @GetMapping("/cajero")
    public String cajero(HttpSession session, Model model) {
        return abrirPanel(session, model, "CAJERO");
    }

    @GetMapping("/cliente")
    public String cliente(HttpSession session, Model model) {
        return abrirPanel(session, model, "CLIENTE");
    }

    private String abrirPanel(HttpSession session, Model model, String rolRequerido) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        // El ADMIN puede entrar a cualquier panel; los demás solo al suyo
        boolean permitido = usuario.getRol().equals(rolRequerido)
                         || usuario.getRol().equals("ADMIN");
        if (!permitido) {
            return "redirect:/" + usuario.getRol().toLowerCase();
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("rolPanel", rolRequerido);
        return "panel";
    }
}
