package com.example.test.service.kafka;

import com.example.test.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {
    // 消费监听
    @KafkaListener(topics = {Constants.TEST_TPOIC})
    public void onMessage1(ConsumerRecord<?, ?> record){
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费："+ record.topic()+" - "+record.partition()+" - "+record.value());
        log.info("Kafka消费：  topic{} , partition:{} ,value:{} ", record.topic(),record.partition(),record.value());
    }
}

