package com.CarlosDaniel.minhaprimeriaAPI;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // Rota para listar os produtos salvos no banco
    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    // Rota protegida com @Valid e ProdutoDTO para cadastrar novos produtos
    @PostMapping("/produtos")
    public String cadastrarProduto(@Valid @RequestBody ProdutoDTO dto) {

        // Transformamos o DTO validado na Entidade real
        Produto novoProduto = new Produto(dto.getNome(), dto.getPreco());

        // Salvamos no banco de dados
        Produto produtoSalvo = repository.save(novoProduto);

        return "Sucesso! Produto " + produtoSalvo.getNome() + " salvo no banco com ID: " + produtoSalvo.getId();
    }
}