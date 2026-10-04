package com.minimarket.minimarket.model;

public class Usuario {
    private int id;
    private String username;
    private String nombre;
    private String rol;

    public Usuario(int id, String username, String nombre, String rol) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.rol = rol;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getNombre() { return nombre; }
    public String getRol() { return rol; }
}
