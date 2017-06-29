package com.github.vp.examples.scdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@SpringBootApplication
public class SinkApplication {
    private final static Logger logger = LoggerFactory.getLogger(SinkApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(
                SinkApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void loggerSink(String message) {
        logger.info("Received Message: " + message);
    }
}
