package com.microservices.demo.twitter.to.kafka.service.exception;

/**
 * Created by Shahrooz on 6/22/2022.
 */
public class TwitterToKafkaServiceException extends RuntimeException {

    public TwitterToKafkaServiceException() {
        super();
    }

    public TwitterToKafkaServiceException(String message) {
        super(message);
    }

    public TwitterToKafkaServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
