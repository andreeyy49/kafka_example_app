package com.example.kafkaexampleapp.model;

import lombok.Data;

@Data
public class KafkaMessage {

    private Long id;

    private String message;
}
