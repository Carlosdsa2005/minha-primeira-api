package com.CarlosDaniel.minhaprimeriaAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private TokenService tokenService;

    // Rota pública para receber as credenciais e devolver a "pulseira VIP"
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {

        // Em um sistema real, iríamos ao PostgreSQL verificar se a senha está correta (focaremos nisso depois).
        // Por enquanto, vamos fazer uma simulação simples (Hardcoded) para testar a geração do Token!
        if ("admin".equals(loginDTO.getUsuario()) && "1234".equals(loginDTO.getSenha())) {

            // Se a senha bater, nós chamamos o TokenService para fabricar o Token
            String token = tokenService.gerarToken(loginDTO.getUsuario());
            return token;

        }

        // Se errar a senha, retorna erro
        return "Usuário ou senha inválidos!";
    }
}
