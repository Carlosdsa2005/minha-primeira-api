package com.CarlosDaniel.minhaprimeriaAPI;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component // Avisa ao Spring que este é um componente genérico que ele deve carregar
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Pega o token do cabeçalho da requisição
        var token = recuperarToken(request);

        // 2. Se tiver um token, nós o validamos
        if (token != null) {
            var subject = tokenService.validarToken(token);

            // 3. Dizemos ao Spring Security: "Pode deixar passar, este usuário está autenticado!"
            var authentication = new UsernamePasswordAuthenticationToken(subject, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 4. Continua o fluxo normal da requisição (vai para o próximo filtro ou para o Controller)
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            // O padrão da web é enviar a palavra "Bearer " antes do token, então nós a removemos aqui
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
