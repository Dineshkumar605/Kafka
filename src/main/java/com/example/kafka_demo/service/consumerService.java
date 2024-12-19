package com.example.kafka_demo.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class consumerService {

    @KafkaListener(topics = "kafka-topic", groupId = "group-id")
    public void kafkaListener(String message){
        System.out.println("Received message from producer : "+ message);
    }


//    @RetryableTopic(attempts = "3", backoff = @Backoff(delay = 3000, multiplier =2 ))
//    @KafkaListener(topics = "payment-topic", groupId = "group_id", topicPartitions = @TopicPartition(topic = "payment-topic", partitions = {"0,1"}))
    @RetryableTopic(attempts = "3", backoff = @Backoff(delay = 3000, multiplier =2 ))
    @KafkaListener(topics = "payment-topic", groupId = "group_id")
    public void consume(ConsumerRecord<String,String> message) {
        System.out.println("Got the message");
        System.out.println("Key: " + message.key() + " Value: " +message.value());
//        Integer.parseInt(message.value());
    }

    @DltHandler
    public void processFailureMessages(ConsumerRecord<String,String> message){
        System.out.println("Key: " + message.key() + " Value: " +message.value());

    }
}
