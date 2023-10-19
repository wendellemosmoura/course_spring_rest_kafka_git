package com.wendellemos.data.message;

import com.wendellemos.data.dto.CarPostDTO;
import com.wendellemos.data.service.PostAnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerMessage {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
    private PostAnalyticsService postAnalyticsService;

    @KafkaListener(topics = "car-post-topic", groupId = "analytics-posts-group")
    public void listening(@Payload CarPostDTO carPost) {

        LOG.info("Received Car Post information: {}", carPost);
        postAnalyticsService.saveDataAnalytics(carPost);
    }
}
