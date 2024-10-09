package ai.sahaj.kafkapubsub.producer;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

@Component
public class RidesPublisher {

  private static final Logger logger = LoggerFactory.getLogger(RidesPublisher.class);

  private final ObjectMapper objectMapper;

  public final KafkaTemplate<String, Object> kafkaTemplate;

  @Value(value = "${rides.kafka.topic.name}")
  private String ridesTopicName;

  @Autowired
  public RidesPublisher(ObjectMapper objectMapper, KafkaTemplate<String, Object> kafkaTemplate) {
    this.objectMapper = objectMapper;
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(Ride ride) throws JsonProcessingException {
    var message = this.objectMapper.writeValueAsString(ride);

    logger.info("Message sent -> {} is sent to Topic {}", message, ridesTopicName);

    this.kafkaTemplate.send(ridesTopicName, String.valueOf(ride.PULocationID), message);
  }
}
