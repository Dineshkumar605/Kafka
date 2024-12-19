package com.example.kafka_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.kafka_demo.service.producerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class kafkaController {

    @Autowired
    private producerService producerService;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String topic, @RequestParam String message) {
        producerService.sendMessage(topic, message);
        return "Message sent : " + message;

    }
}
