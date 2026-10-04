package com.minimarket.minimarket.service;

import com.minimarket.minimarket.dao.UsuarioDAO;
import com.minimarket.minimarket.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario autenticar(String username, String clave) {
        if (username == null || username.isBlank() || clave == null || clave.isBlank()) {
            return null;
        }
        try {
            return usuarioDAO.buscarPorCredenciales(username.trim(), clave);
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la base de datos", e);
        }
    }
}
