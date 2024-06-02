package com.ibm.quarkusacademy.book.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import com.ibm.quarkusacademy.book.model.Book;
import com.ibm.quarkusacademy.book.model.BookAvailable;
import com.ibm.quarkusacademy.book.model.BooksAvailable;
import com.ibm.quarkusacademy.book.rest.LibraryApi;
import com.ibm.quarkusacademy.book.sse.BookSseService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@SuppressWarnings({"java:S6813", "java:S3252"})
public class BookService {

  @Inject
  RatingService ratingService;

  @Inject
  BookSseService bookSseService;

  @Inject
  @RestClient
  LibraryApi libraryApi;

  public List<Book> getBooks() {
    return Book.listAll();
  }

  @Transactional
  public void saveBook(final Book book) {
    book.rating = this.ratingService.rateBook(book);

    Book.persist(book);
    this.bookSseService.emit(book);
  }

  public List<UUID> physicalBooksAvailable(final Book book) {
    Response response = this.libraryApi.isAvailable(book.id);
    if (!Status.OK.equals(response.getStatusInfo().toEnum())) {
      throw new NotFoundException("Book was not found in library API");
    }

    BooksAvailable booksAvailable = response.readEntity(BooksAvailable.class);
    List<UUID> physicalBooksId = new ArrayList<>();
    LocalDateTime now = LocalDateTime.now();
    for (BookAvailable bookAvailable : booksAvailable.booksAvailable()) {
      if (!bookAvailable.availableFrom().isAfter(now)) {
        physicalBooksId.add(bookAvailable.physicalBookId());
      }
    }

    return physicalBooksId;
  }

}
