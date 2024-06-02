package com.ibm.quarkusacademy.book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * Holds information about book's name, author, year, hardcover and rating
 */
@Entity
@SuppressWarnings("java:S1104")
public class Book extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column(name = "name", nullable = false)
  public String name;

  @Column(name = "author")
  public String author;

  @Column(name = "year", nullable = false)
  public int year;

  @Column(name = "hard_cover", nullable = false)
  public boolean hardCover;

  @Column(name = "rating")
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
