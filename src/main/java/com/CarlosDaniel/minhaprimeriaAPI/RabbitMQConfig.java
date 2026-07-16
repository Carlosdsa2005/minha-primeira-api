package com.CarlosDaniel.minhaprimeriaAPI;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Esse método cria uma fila chamada "produtos.criados" lá no painel do RabbitMQ
    @Bean
    public Queue filaProdutos() {
        return new Queue("produtos.criados", true);
    }
}