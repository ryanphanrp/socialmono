package org.ryan.infrastructure.configuration;

import org.ryan.constant.RabbitMessage;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(RabbitMessage.DIRECT_EXCHANGE);
    }

    @Bean
    public Queue authQueue() {
        return new Queue(RabbitMessage.AUTH_REQUEST);
    }

    @Bean
    public Queue registerQueue() {
        return new Queue(RabbitMessage.REGISTER_REQUEST);
    }


    @Bean
    public Binding authBinding(Queue authQueue, DirectExchange exchange) {
        return BindingBuilder.bind(authQueue).to(exchange).with(RabbitMessage.AUTH_ROUTING_KEY);
    }

    @Bean
    public Binding registerBinding(Queue registerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(registerQueue).to(exchange).with(RabbitMessage.REGISTER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connection) {
        var rabbitTemplate = new RabbitTemplate(connection);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
