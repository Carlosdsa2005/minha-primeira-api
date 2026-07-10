package com.CarlosDaniel.minhaprimeriaAPI;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service // Avisa ao Spring que esta é uma classe de serviço injetável
public class TokenService {

    // Em um sistema real, essa chave secreta NUNCA fica no código.
    // Ela ficaria escondida nas variáveis de ambiente do servidor.
    private String secret = "minha_chave_super_secreta_123";

    // Método que "cria a pulseira VIP" (Gera o Token)
    public String gerarToken(String usuario) {
        Algorithm algoritmo = Algorithm.HMAC256(secret); // Usa nossa chave para assinar
        return JWT.create()
                .withIssuer("MinhaPrimeiraAPI") // Quem está emitindo o token
                .withSubject(usuario) // Para quem é este token
                .withExpiresAt(dataExpiracao()) // Quando a pulseira perde a validade
                .sign(algoritmo);
    }

    // Método que o porteiro usa para ver se a pulseira é falsa ou expirou
    public String validarToken(String token) {
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)
                .withIssuer("MinhaPrimeiraAPI")
                .build()
                .verify(token) // Se o token for falso ou expirado, o código trava aqui
                .getSubject(); // Se der certo, ele devolve o nome do usuário
    }

    // Define que a validade do Token é de 2 horas a partir de agora
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
