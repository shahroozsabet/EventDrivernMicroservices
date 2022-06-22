package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import twitter4j.TwitterException;

import java.util.Arrays;

/**
 * Created by Shahrooz on 6/22/2022.
 */
@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    private final StreamRunner streamRunner;

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws TwitterException {
        log.info("{}", twitterToKafkaServiceConfigData.getWelcomeMessage());
        log.info("twitterKeywords= {}", Arrays.toString(twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[]{})));
        streamRunner.start();
    }
}
