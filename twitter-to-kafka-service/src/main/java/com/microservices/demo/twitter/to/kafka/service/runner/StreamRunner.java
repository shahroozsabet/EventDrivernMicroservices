package com.microservices.demo.twitter.to.kafka.service.runner;

import twitter4j.TwitterException;

/**
 * Created by Shahrooz on 6/22/2022.
 */
public interface StreamRunner {
    void start() throws TwitterException;
}
