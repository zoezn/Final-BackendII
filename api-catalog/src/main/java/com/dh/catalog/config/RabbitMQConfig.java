package com.dh.catalog.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "catalogExchange";
    public static final String TOPIC_NEW_MOVIE = "com.dh.movies.newMovie";
    public static final String TOPIC_NEW_SERIES = "com.dh.series.newSeries";
    public static final String QUEUE_NEW_MOVIE = "newMovieQueue";
    public static final String QUEUE_NEW_SERIES =  "newSeriesQueue";


    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue newMovieQueue() {
        return new Queue(QUEUE_NEW_MOVIE);
    }


    @Bean
    public Queue newSeriesQueue() {
        return new Queue(QUEUE_NEW_SERIES);
    }


    @Bean
    public Binding declareBindingSpecificNewMovie() {
        return BindingBuilder.bind(newMovieQueue()).to(appExchange()).with(TOPIC_NEW_MOVIE);
    }

    @Bean
    public Binding declareBindingSpecificNewSeries() {
        return BindingBuilder.bind(newSeriesQueue()).to(appExchange()).with(TOPIC_NEW_SERIES);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }







}
