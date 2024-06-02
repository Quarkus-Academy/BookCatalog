package com.ibm.quarkusacademy.book.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import com.ibm.quarkusacademy.book.service.BookService;
import io.vertx.ext.web.RoutingContext;

@Path("/")
@SuppressWarnings("java:S6813")
public class BookUiResource {

  @Inject
  BookService service;

  // Inject the template file 'main.qute.html'

  // Useful for getting additional routing information
  @Context
  RoutingContext routingContext;


  // Create a GET endpoint which gets all books and inserts it in the template under key "bookList"

}
