package com.ibm.quarkusacademy.book.rest;

import jakarta.ws.rs.Path;

@Path("/stream")
@SuppressWarnings("java:S6813")
public class SseResource {

  // Inject ServerSentEvent service

  // Create GET endpoint which produces SERVER_SENT_EVENTS
  // Set @RestStreamElementType as APPLICATION_JSON
  public void bookStream() {
    // Create a stream using Multi(emitter), which registers emitter in SSE service
    // and removes it onTermination of emitter
  }
}
