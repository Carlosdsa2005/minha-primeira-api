package com.CarlosDaniel.minhaprimeriaAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    // A anotação @Autowired injeta a nossa interface automaticamente.
    // É através da variável 'repository' que daremos comandos ao PostgreSQL.
    @Autowired
    private ProdutoRepository repository;

    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {
        // Vai no banco de dados e busca todas as linhas da tabela 'produto'
        return repository.findAll();
    }

    @PostMapping("/produtos")
    public String cadastrarProduto(@RequestBody Produto novoProduto) {
        // O comando save() insere o dado na tabela e o banco gera o ID automaticamente
        Produto produtoSalvo = repository.save(novoProduto);

        return "Sucesso! Produto " + produtoSalvo.getNome() + " salvo no banco com o ID: " + produtoSalvo.getId();
    }
}