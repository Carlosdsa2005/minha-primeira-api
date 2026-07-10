package com.CarlosDaniel.minhaprimeriaAPI;

public class LoginDTO {

    private String usuario;
    private String senha;

    // Getters para o Spring conseguir ler o JSON que virá do Postman
    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
}
