package com.dh.series.event;

import com.dh.series.config.RabbitMQConfig;
import com.dh.series.model.Series;
import com.dh.series.model.dto.SeriesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
@Slf4j
public class NewSerieEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public NewSerieEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void execute(Series seriesNew) {
        NewSerieEventProducer.Data data= new NewSerieEventProducer.Data();
        BeanUtils.copyProperties(seriesNew,data.getSeries());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_NEW_MUSIC, data);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;
        private SeriesDTO series = new SeriesDTO();


    }
}