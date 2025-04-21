package com.ohno.consumer;

import com.ohno.dto.WikimediaDto;
import com.ohno.repository.WikimediaDtoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
    private final WikimediaDtoRepository dtoRepository;

    @Autowired
    public KafkaDatabaseConsumer(WikimediaDtoRepository dtoRepository) {
        this.dtoRepository = dtoRepository;
    }

    @KafkaListener(topics = "wikimedia_recent_change", groupId = "myGroup")
    public void consume(String eventMessage) {
        LOGGER.info(String.format("Message received -> %s", eventMessage));
        WikimediaDto wikimediaDto = new WikimediaDto();
        wikimediaDto.setWikiEventData(eventMessage);
        dtoRepository.save(wikimediaDto);
    }
}
