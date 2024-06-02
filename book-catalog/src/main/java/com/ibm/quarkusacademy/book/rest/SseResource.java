package com.ibm.quarkusacademy.book.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.ibm.quarkusacademy.book.model.Book;
import com.ibm.quarkusacademy.book.sse.BookSseService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.subscription.Cancellable;
import org.jboss.resteasy.reactive.RestStreamElementType;

@Path("/stream")
@SuppressWarnings("java:S6813")
public class SseResource {

  // Inject ServerSentEvent service
  @Inject
  BookSseService bookSseService;

  // Create GET endpoint which produces SERVER_SENT_EVENTS
  // Set @RestStreamElementType as APPLICATION_JSON
  @GET
  @Path("/book")
  @Produces(MediaType.SERVER_SENT_EVENTS)
  @RestStreamElementType(MediaType.APPLICATION_JSON)
  public Multi<Book> bookStream() {
    // Create a stream using Multi, which registers emitter in SSE service
    // and removes it after emitter was terminated
    return Multi.createFrom().emitter(emitter -> {
      Cancellable cancellable = this.bookSseService.registerEmitter(emitter);
      emitter.onTermination(cancellable::cancel);
    });
  }
}
