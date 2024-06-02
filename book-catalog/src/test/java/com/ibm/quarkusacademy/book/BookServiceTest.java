package com.ibm.quarkusacademy.book;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import jakarta.ws.rs.core.Response;
import com.ibm.quarkusacademy.book.model.Book;
import com.ibm.quarkusacademy.book.model.BookAvailable;
import com.ibm.quarkusacademy.book.model.BooksAvailable;
import com.ibm.quarkusacademy.book.rest.LibraryApi;
import com.ibm.quarkusacademy.book.service.BookService;
import com.ibm.quarkusacademy.book.service.RatingService;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

@QuarkusTest
class BookServiceTest {

  @InjectSpy
  BookService bookService;

  @InjectMock
  RatingService ratingService;

  @InjectMock
  @RestClient
  LibraryApi libraryApi;

  // @InjectMock
  // Session session;

  @Test
  void test_getBooks() {
    // Create book which will be returned by the mock
    Book book = new Book("The Book", "Author", 2020, true, 5);

    // Mock PanacheEntity and stub calls to database
    PanacheMock.mock(Book.class);
    when(Book.listAll()).thenReturn(Arrays.asList(book));

    // Call tested service
    List<Book> books =
        assertDoesNotThrow(() -> this.bookService.getBooks(), "getBooks() throws Exception");

    // Assert
    assertEquals(1, books.size());
    assertEquals("The Book", books.get(0).name);

    // Verify that services were (not) called
    verify(this.ratingService, never()).rateBook(any());
    verify(this.libraryApi, never()).isAvailable(anyLong());
  }

  @Test
  void test_saveBook() {
    // Create book which will be returned by the mock
    Book book = new Book("The Book", "Author", 2020, true, null);

    // Mock PanacheEntity, create ArgumentCaptor for class Book
    PanacheMock.mock(Book.class);
    ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);

    // Stub calls to rating service and database. As persist() returns void, use PanacheMock.doNothing().when(...
    when(this.ratingService.rateBook(any())).thenReturn(5);
    PanacheMock.doNothing().when(Book.class).persist(bookCaptor.capture(), any());

    // Call tested service
    assertDoesNotThrow(() -> this.bookService.saveBook(book), "saveBook(book) throws Exception");

    // Assert
    assertNotNull(bookCaptor.getValue().rating);
    assertEquals(5, bookCaptor.getValue().rating);

    // Verify that services were (not) called
    verify(this.ratingService).rateBook(any());
    verify(this.libraryApi, never()).isAvailable(anyLong());
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

    // Mock PanacheEntity and stub calls to API
    PanacheMock.mock(Book.class);
    when(this.libraryApi.isAvailable(anyLong())).thenReturn(Response.ok(booksAvailable).build());

    // Call tested service
    List<UUID> actualPhysicalIds =
        assertDoesNotThrow(() -> this.bookService.physicalBooksAvailable(book),
            "physicalBooksAvailable(book) throws Exception");

    // Assert
    assertTrue(actualPhysicalIds.containsAll(Arrays.asList(available1, available2)));
    assertFalse(actualPhysicalIds.contains(notAvailable));

    // Verify that services were (not) called
    verify(this.ratingService, never()).rateBook(any());
    verify(this.libraryApi).isAvailable(anyLong());
  }
}
