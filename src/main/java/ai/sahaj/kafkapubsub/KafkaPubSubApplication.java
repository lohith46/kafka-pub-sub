package ai.sahaj.kafkapubsub;

import ai.sahaj.kafkapubsub.producer.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.*;

@SpringBootApplication
public class KafkaPubSubApplication {

  public static void main(String[] args) {

    SpringApplication.run(KafkaPubSubApplication.class, args);
  }

}
