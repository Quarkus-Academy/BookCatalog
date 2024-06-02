package com.ibm.quarkusacademy.library.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class LibraryService {

  private Random random;

  @PostConstruct
  private void init() {
    random = ThreadLocalRandom.current();
  }

  /**
   * @return random from 1-5 (inclusive)
   */
  public int getRandomAmount() {
    return random.nextInt(1, 6);
  }

  /**
   * @return random from 2 months before now and 2 months after now
   */
  public LocalDateTime getRandomTime() {
    long min = LocalDateTime.now().minusMonths(2).toInstant(ZoneOffset.UTC).toEpochMilli();
    long max = LocalDateTime.now().plusMonths(2).toInstant(ZoneOffset.UTC).toEpochMilli();

    long randomEpochMilli = random.nextLong(min, max);

    return LocalDateTime.ofInstant(Instant.ofEpochMilli(randomEpochMilli), ZoneOffset.UTC);
  }

}
