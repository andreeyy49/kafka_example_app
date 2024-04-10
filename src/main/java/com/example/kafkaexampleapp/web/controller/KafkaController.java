package com.example.kafkaexampleapp.web.controller;

import com.example.kafkaexampleapp.model.KafkaMessage;
import com.example.kafkaexampleapp.service.KafkaMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class KafkaController {

    @Value("${app.kafka.kafkaMessageTopic}")
    private String topicName;

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    private final KafkaMessageService kafkaMessageService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody KafkaMessage Message) {
        kafkaTemplate.send(topicName, Message);

        return ResponseEntity.ok("Message send to kafka");
    }

    @GetMapping("/{id}")
    public ResponseEntity<KafkaMessage> getById(@PathVariable Long id){
        return ResponseEntity.ok(kafkaMessageService.getById(id).orElseThrow());
    }
}
