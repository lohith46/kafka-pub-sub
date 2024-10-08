package ai.sahaj.kafkapubsub.producer;

import com.opencsv.exceptions.*;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

@Component
public class RidesPublisher {

  public final KafkaTemplate<String, Object> kafkaTemplate;

  @Autowired
  public RidesPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @PostConstruct
  public void init() throws IOException, CsvException {
    sendMessage();
  }

  public void sendMessage() throws IOException, CsvException {
    List<Ride> rides = getRides();
    rides.forEach(this::sendRideData);
  }

  private void sendRideData(Ride message) {
    CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("lohith-topic",
      String.valueOf(message.PULocationID), message);
    future.whenComplete((result, ex) -> {
      if (ex == null) {
        System.out.println("Sent message=[" + message +
          "] with offset=[" + result.getRecordMetadata().offset() + "]");
      } else {
        System.out.println("Unable to send message=[" +
          message + "] due to : " + ex.getMessage());
      }
    });
  }

  private static List<Ride> getRides() throws IOException, CsvException {
    RidesUtil ridesUtil = new RidesUtil();
    return ridesUtil.getRides();
  }
}
