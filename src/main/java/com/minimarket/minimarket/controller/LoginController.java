package com.minimarket.minimarket.controller;

import com.minimarket.minimarket.model.Usuario;
import com.minimarket.minimarket.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestParam String username,
                                @RequestParam String clave,
                                HttpSession session,
                                Model model) {
        Usuario usuario = usuarioService.autenticar(username, clave);
        if (usuario == null) {
            model.addAttribute("error", "Usuario o clave incorrectos");
            return "login";
        }
        session.setAttribute("usuario", usuario);
        return "redirect:/" + usuario.getRol().toLowerCase();
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
