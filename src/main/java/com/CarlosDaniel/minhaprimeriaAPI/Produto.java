package com.CarlosDaniel.minhaprimeriaAPI;

public class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    // ATENÇÃO: Os métodos Getters são OBRIGATÓRIOS aqui!
    // É através deles que o Spring (usando a biblioteca Jackson) consegue ler
    // os dados privados para convertê-los em JSON.
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
