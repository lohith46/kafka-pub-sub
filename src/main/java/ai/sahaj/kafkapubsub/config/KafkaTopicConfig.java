package ai.sahaj.kafkapubsub.config;

import org.apache.kafka.clients.admin.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;

import java.util.*;

@Configuration
public class KafkaTopicConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Value(value = "${rides.kafka.topic.name}")
  private String ridesTopicName;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic topic1() {
    return new NewTopic(ridesTopicName, 1, (short) 1);
  }
}
