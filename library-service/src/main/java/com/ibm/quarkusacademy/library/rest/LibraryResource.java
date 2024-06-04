package com.ibm.quarkusacademy.library.rest;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import com.ibm.quarkusacademy.library.model.Book;
import com.ibm.quarkusacademy.library.model.BookAvailable;
import com.ibm.quarkusacademy.library.model.BooksAvailable;
import com.ibm.quarkusacademy.library.service.LibraryService;
import io.quarkus.logging.Log;

@Path("/api")
@SuppressWarnings("java:S6813")
public class LibraryResource {

  @Inject
  LibraryService libraryService;

  /**
   * Gets availability status for each physical book in the library
   * @param bookId ID of {@link Book} in the database
   * @return all physical books with their availability status
   */
  @GET
  @Path("/{bookId}")
  @Produces(APPLICATION_JSON)
  public Response isAvailable(@PathParam("bookId") Long bookId) {
    Log.info("Getting available status for book ID " + bookId);

    List<BookAvailable> listOfBooksWithAvailability =
        IntStream.range(0, this.libraryService.getRandomAmount())
            .mapToObj(
                index -> new BookAvailable(UUID.randomUUID(), this.libraryService.getRandomTime()))
            .toList();
    BooksAvailable booksAvailable = new BooksAvailable(listOfBooksWithAvailability);
    Log.info("Books' available status: " + booksAvailable);

    return Response.ok(booksAvailable).build();
  }

  /**
   * Pings library-api
   */
  @GET
  public Response ping() {
    Log.info("LibraryService was pinged");

    return Response.ok("pong").build();
  }

}
