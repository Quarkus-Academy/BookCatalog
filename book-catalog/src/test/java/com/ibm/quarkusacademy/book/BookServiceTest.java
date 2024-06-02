package com.ibm.quarkusacademy.book;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import com.ibm.quarkusacademy.book.model.Book;
import com.ibm.quarkusacademy.book.model.BookAvailable;
import com.ibm.quarkusacademy.book.model.BooksAvailable;
import com.ibm.quarkusacademy.book.rest.LibraryApi;
import com.ibm.quarkusacademy.book.service.BookService;
import com.ibm.quarkusacademy.book.service.RatingService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class BookServiceTest {

  // Annotate services and api
  BookService bookService;

  RatingService ratingService;

  LibraryApi libraryApi;

  @Test
  void test_getBooks() {
    // Create book which will be returned by the mock
    Book book = new Book("The Book", "Author", 2020, true, 5);

    // Mock PanacheEntity and stub listAll

    // Call bookService.getBooks()

    // Assert

    // Verify that services were (not) called
  }

  @Test
  void test_saveBook() {
    // Create book which will be returned by the mock
    Book book = new Book("The Book", "Author", 2020, true, null);

    // Mock PanacheEntity, create ArgumentCaptor for class Book

    // Stub ratingService.rateBook() and Book.persist(). As persist() returns void, use PanacheMock.doNothing().when(...

    // Call bookService.saveBook()

    // Assert

    // Verify that services were (not) called
  }

  @Test
  void test_physicalBooksAvailable() {
    // Create book which will be sent to tested service
    Book book = new Book("The Book", "Author", 2020, true, null);
    book.id = 1L;

    // Create BooksAvailable response which will be returned by the mock
    UUID available1 = UUID.randomUUID();
    UUID available2 = UUID.randomUUID();
    UUID notAvailable = UUID.randomUUID();
    BooksAvailable booksAvailable = new BooksAvailable(
        Arrays.asList(new BookAvailable(available1, LocalDateTime.now().minusYears(10)),
            new BookAvailable(available2, LocalDateTime.now().minusSeconds(1)),
            new BookAvailable(notAvailable, LocalDateTime.now().plusDays(2))));

    // Mock PanacheEntity and stub libraryApi.isAvailable()

    // Call bookService.physicalBooksAvailable()

    // Assert

    // Verify that services were (not) called
  }
}
