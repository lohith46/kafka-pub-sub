package ai.sahaj.kafkapubsub.consumer;

import org.slf4j.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

@Component
public class RidesConsumer {

  private static final Logger logger = LoggerFactory.getLogger(RidesConsumer.class);

  @KafkaListener(topics = "rides-data", groupId = "consumer-1")
  public void consume(String message) {
    logger.info("Consumer Message received as JSON -> {}", message);
  }
}
