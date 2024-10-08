package ai.sahaj.kafkapubsub.consumer;

import ai.sahaj.kafkapubsub.producer.*;
import org.apache.kafka.clients.consumer.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

@Component
public class RidesConsumer {

  @Value(value = "${spring.kafka.consumer.group-id}")
  private String groupId;

  @KafkaListener(topicPartitions
    = @TopicPartition(topic = "lohith-topic", partitions = { "0", "1" }), groupId = "consumer-1")
  public void consumerRideMessage(Ride message) {
    System.out.println("Received message in group : " + message);
  }
}
