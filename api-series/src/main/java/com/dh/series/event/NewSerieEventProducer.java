package com.dh.series.event;

import com.dh.series.config.RabbitMQConfig;
import com.dh.series.model.Chapter;
import com.dh.series.model.ChapterDTO;
import com.dh.series.model.Season;
import com.dh.series.model.Series;
import com.dh.series.model.dto.SeasonDTO;
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


//    public void execute(Series seriesNew) {
//        NewSerieEventProducer.Data data= new NewSerieEventProducer.Data();
//        BeanUtils.copyProperties(seriesNew,data.getSeries());
//        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_NEW_SERIES, data);
//    }

    public void execute(Series serie) {
        SeriesDTO serieDTO = new SeriesDTO();
        BeanUtils.copyProperties(serie, serieDTO);
        for (Season s :
                serie.getSeasons()) {
            SeasonDTO sDTO = new SeasonDTO();
            BeanUtils.copyProperties(s, sDTO);
            for (Chapter c :
                    s.getChapters()) {
                ChapterDTO cDTO = new ChapterDTO();
                BeanUtils.copyProperties(c, cDTO);
                sDTO.getChapters().add(cDTO);
            }

            serieDTO.getSeasons().add(sDTO);
        }

        if (serieDTO.getSeasons().get(0).getChapters().get(0) != null && serie.getSeasons().get(0).getChapters().get(0) != null) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_NEW_SERIES, serieDTO);
        }

    }

}