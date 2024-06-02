package com.ibm.quarkusacademy.library.model;

/**
 * Holds information about book's name, author, year, hardcover and rating
 */
@SuppressWarnings("java:S1104")
public class Book {

  public Long id;

  public String name;

  public String author;

  public int year;

  public boolean hardCover;

  public Integer rating;

  public Book() {}

  public Book(String name, String author, int year, boolean hardCover, Integer rating) {
    this.name = name;
    this.author = author;
    this.year = year;
    this.hardCover = hardCover;
    this.rating = rating;
  }

}
