package com.ibm.quarkusacademy.book.sse;

import jakarta.enterprise.context.ApplicationScoped;
import com.ibm.quarkusacademy.book.model.Book;

@ApplicationScoped
public class BookSseService extends SseService<Book> {

}
