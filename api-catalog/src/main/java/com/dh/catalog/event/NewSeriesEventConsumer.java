package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.ChapterEntity;
import com.dh.catalog.model.SeasonEntity;
import com.dh.catalog.model.SeriesEntity;
import com.dh.catalog.model.dto.ChapterDTO;
import com.dh.catalog.model.dto.SeasonDTO;
import com.dh.catalog.model.dto.SeriesDTO;
import com.dh.catalog.repository.SeriesEntityRepository;
import lombok.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewSeriesEventConsumer {

    private final SeriesEntityRepository seriesEntityRepository;

    public NewSeriesEventConsumer(SeriesEntityRepository seriesEntityRepository){
        this.seriesEntityRepository = seriesEntityRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NEW_SERIES)
    public void execute(SeriesDTO seriesDTO) {
        SeriesEntity seriesNew = new SeriesEntity();
        BeanUtils.copyProperties(seriesDTO, seriesNew);

        for (SeasonDTO sDTO : seriesDTO.getSeasons()) {
            SeasonEntity season = new SeasonEntity();
            BeanUtils.copyProperties(sDTO, season);
            for (ChapterDTO cDTO :
                    sDTO.getChapters()) {
                ChapterEntity chapter = new ChapterEntity();
                BeanUtils.copyProperties(cDTO, chapter);
                season.getChapters().add(chapter);
            }

            seriesNew.getSeasons().add(season);
        }

        seriesEntityRepository.deleteById(seriesNew.getId());
        seriesEntityRepository.save(seriesNew);
    }

}
