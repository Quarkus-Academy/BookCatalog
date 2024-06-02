package com.ibm.quarkusacademy.book.rest;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import com.ibm.quarkusacademy.book.model.Book;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api")
@RegisterRestClient(configKey = "library-api")
public interface LibraryApi {

  /**
   * Gets availability status for each physical book in the library
   * @param bookId ID of {@link Book} in the database
   * @return all physical books with their availability status
   */
  @GET
  @Path("/{bookId}")
  @Produces(APPLICATION_JSON)
  public Response isAvailable(@PathParam("bookId") Long bookId);

  /**
   * Pings library-api
   */
  @GET
  public Response ping();

}
