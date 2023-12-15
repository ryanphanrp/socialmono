package org.ryan.infrastructure.configuration;

import org.ryan.constant.RabbitMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
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
