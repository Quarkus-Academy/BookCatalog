package com.ibm.quarkusacademy.library.model;

import java.util.List;

/**
 * List of availability of physical books in the library
 */
public record BooksAvailable(List<BookAvailable> booksAvailable) {

}
