package com.microservices.demo.kafka.admin.exception;

/**
 * Exception class for Kafka client error situations.
 * Created by Shahrooz on 6/24/2022.
 */
public class KafkaClientException extends RuntimeException {

    public KafkaClientException() {
    }

    public KafkaClientException(String message) {
        super(message);
    }

    public KafkaClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
