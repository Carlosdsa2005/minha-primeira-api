package com.CarlosDaniel.minhaprimeriaAPI;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

// Essa anotação avisa ao Spring: "Ei, esta classe vai receber requisições da internet!"
@RestController
public class ProdutoController {

    // Mapeamos o verbo HTTP "GET" para o caminho (URI) "/produtos"
    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {

        // Em um sistema real, buscaríamos isso do Banco de Dados.
        // Aqui, estamos apenas simulando uma lista em memória.
        return Arrays.asList(
                new Produto("Teclado Mecânico", 350.00),
                new Produto("Mouse Sem Fio", 120.00)
        );
    }
}
