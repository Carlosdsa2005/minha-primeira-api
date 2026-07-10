package com.CarlosDaniel.minhaprimeriaAPI;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

// Imports estáticos essenciais
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProdutoRepository repository;

    @Autowired
    private TokenService tokenService;

    @Test
    public void deveListarProdutosComSucesso() throws Exception {

        // 1. Geramos o token real para passar pelo SecurityFilter
        String token = tokenService.gerarToken("admin");

        // 2. Preparamos o dublê (usando "Monitor" sem acentos para evitar bugs de UTF-8)
        Produto produtoFake = new Produto("Monitor", 350.0);
        Mockito.when(repository.findAll()).thenReturn(List.of(produtoFake));

        // 3. Executamos a chamada GET passando o Token e validamos a resposta completa
        mockMvc.perform(get("/produtos")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                // Abordagem infalível: Verifica se a resposta contém o bloco JSON exato
                .andExpect(content().json("[{\"nome\":\"Monitor\", \"preco\":350.0}]"));
    }

    @Test
    public void deveBloquearProdutoComPrecoNegativo() throws Exception {

        String token = tokenService.gerarToken("admin");
        String jsonInvalido = "{\"nome\": \"Mouse\", \"preco\": -10.0}";

        mockMvc.perform(post("/produtos")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInvalido))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveRetornarErroSemToken() throws Exception {

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isForbidden());
    }
}