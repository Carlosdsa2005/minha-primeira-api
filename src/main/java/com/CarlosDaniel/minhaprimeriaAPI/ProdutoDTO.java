package com.CarlosDaniel.minhaprimeriaAPI;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProdutoDTO
{
    // @NotBlank garante que o usuário não mande um nome vazio ou apenas com espaços
    @NotBlank(message = "Erro: O nome do produto é obrigatório!")
    private String nome;

    // @Positive garante que o preço seja maior que zero
    @Positive(message = "Erro: O preço deve ser maior que zero!")
    private double preco;

    // Getters para o Spring conseguir ler o JSON
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}

