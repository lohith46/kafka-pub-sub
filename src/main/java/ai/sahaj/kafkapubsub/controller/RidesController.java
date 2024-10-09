package ai.sahaj.kafkapubsub.controller;

import ai.sahaj.kafkapubsub.producer.*;
import com.fasterxml.jackson.core.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka/v1")
public class RidesController {

  private final RidesPublisher ridesPublisher;

  public RidesController(RidesPublisher ridesPublisher) {
    this.ridesPublisher = ridesPublisher;
  }

  @PostMapping("/send")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void publish(@RequestBody Ride ride) throws JsonProcessingException {
    this.ridesPublisher.sendMessage(ride);
  }
}
