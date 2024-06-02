package com.ibm.quarkusacademy.book.rest;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import com.ibm.quarkusacademy.book.model.Book;
import com.ibm.quarkusacademy.book.service.BookService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.vertx.ext.web.RoutingContext;

@Path("/")
@SuppressWarnings("java:S6813")
public class BookUiResource {

  @Inject
  BookService service;

  // Inject the template file
  @Inject
  Template main;

  // Useful for getting additional routing information
  @Context
  RoutingContext routingContext;


  // Create a GET endpoint which gets all books and inserts it in the template under key "bookList"
  @GET
  public TemplateInstance main() {
    List<Book> books = this.service.getBooks();

    return this.main.data("bookList", books);
  }

}
