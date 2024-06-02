package com.ibm.quarkusacademy.book.rest;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import com.ibm.quarkusacademy.book.model.Book;
import com.ibm.quarkusacademy.book.service.BookService;

@Path("/api/books")
@SuppressWarnings("java:S6813")
public class BookResource {

  @Inject
  BookService bookService;

  @GET
  @Produces(APPLICATION_JSON)
  public Response getBooks() {
    return Response.ok(this.bookService.getBooks()).build();
  }

  @POST
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response addBook(final Book book) {
    if (book == null) {
      return Response.status(Status.BAD_REQUEST).entity("Book was not provided").build();
    }

    this.bookService.saveBook(book);
    return Response.ok().build();
  }

  @POST
  @Path("/available")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getAvailableBooks(final Book book) {
    if (book == null || book.id == null) {
      return Response.status(Status.BAD_REQUEST).entity("Book or its ID was not provided").build();
    }
    return Response.ok(this.bookService.physicalBooksAvailable(book)).build();
  }

}
