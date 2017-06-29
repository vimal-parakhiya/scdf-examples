package com.github.vp.examples.scdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@EnableBinding(Source.class)
@SpringBootApplication
public class SourceApplication
{
    private final static Logger logger = LoggerFactory.getLogger(SourceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(
                SourceApplication.class, args);
    }

    @Bean
    @InboundChannelAdapter(
            value = Source.OUTPUT,
            poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1")
    )
    public MessageSource<String> stringMessageSource() {
        return () -> {
            String payload = randomAlphanumeric(1, 10);
            logger.info("Generated Message: " + payload);
            return MessageBuilder.withPayload(payload).build();
        };
    }
}
