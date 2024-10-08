package ai.sahaj.kafkapubsub.config;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.*;

import java.util.*;

@Configuration
public class KafkaProducerConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Bean
  public ProducerFactory<String, Object> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(
      ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
      bootstrapAddress);
//    configProps.put(
//      ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//      StringSerializer.class);
//    configProps.put(
//      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//      StringSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps,
      new JsonSerializer<>(),
      new JsonSerializer<>());
  }

  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }


}
