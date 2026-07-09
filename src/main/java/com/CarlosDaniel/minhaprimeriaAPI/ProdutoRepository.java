package com.CarlosDaniel.minhaprimeriaAPI;

import org.springframework.data.jpa.repository.JpaRepository;

// Ao estender o JpaRepository, o Spring nos dá de brinde todos os comandos
// prontos para salvar, buscar, atualizar e deletar Produtos no banco.
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}