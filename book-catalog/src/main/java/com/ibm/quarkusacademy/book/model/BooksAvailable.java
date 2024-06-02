package com.ibm.quarkusacademy.book.model;

import java.util.List;

/**
 * List of availability of physical books in the library
 */
public record BooksAvailable(List<BookAvailable> booksAvailable) {

}
