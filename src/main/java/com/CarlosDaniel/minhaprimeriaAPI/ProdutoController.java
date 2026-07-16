package com.CarlosDaniel.minhaprimeriaAPI;

import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

@RestController
public class ProdutoController {

    // 1. AJUSTE ARQUITETURAL: Removemos o @Autowired e usamos 'final'
    private final ProdutoRepository repository;
    private final RabbitTemplate rabbitTemplate;

    // A injeção de dependências acontece toda aqui no construtor de forma segura
    public ProdutoController(ProdutoRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/produtos")
    @Cacheable(value = "produtos")
    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    @CacheEvict(value = "produtos", allEntries = true)
    @PostMapping("/produtos")
    public String cadastrarProduto(@Valid @RequestBody ProdutoDTO dto) {

        Produto novoProduto = new Produto(dto.getNome(), dto.getPreco());
        Produto produtoSalvo = repository.save(novoProduto);

        // 2. A MÁGICA ASSÍNCRONA FALTANTE AQUI!
        // Avisamos ao RabbitMQ que um produto foi criado
        String mensagem = "Uhuul! Novo produto cadastrado: " + produtoSalvo.getNome();
        rabbitTemplate.convertAndSend("produtos.criados", mensagem);

        return "Sucesso! Produto " + produtoSalvo.getNome() + " salvo no banco com ID: " + produtoSalvo.getId();
    }
}