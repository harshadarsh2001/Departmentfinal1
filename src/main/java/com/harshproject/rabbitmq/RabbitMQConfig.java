package com.harshproject.rabbitmq;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig{

    @Bean
    Queue queue() {
        return new Queue("department-queue", true);
        
    }
	
//	@Bean
//    public Queue queue() {
//        return QueueBuilder.durable("department-queue")
//            .withArgument("x-message-ttl", 40000) // Set TTL to 5000 milliseconds (5 seconds)
//            .build();
//    }
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("department-exchange");

    }
    @Bean
    Binding binding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with("myRoutingKey");
    }
}

