package com.ibm.quarkusacademy.book.service;

import java.util.Random;
import jakarta.enterprise.context.ApplicationScoped;
import com.ibm.quarkusacademy.book.model.Book;

@ApplicationScoped
public class RatingService {

  public int rateBook(Book book) {
    if (book.rating != null) {
      return book.rating;
    }

    return new Random(book.hashCode()).nextInt(11);
  }

}
