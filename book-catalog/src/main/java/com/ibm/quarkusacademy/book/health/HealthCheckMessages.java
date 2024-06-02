package com.ibm.quarkusacademy.book.health;

/**
 * Helper class for Health check error messages
 */
public class HealthCheckMessages {
  public static final String HC_DATA_EXCEPTION = "exception";
  public static final String DOWN_URL_UNDEFINED = "URL of library API is not defined";
  public static final String DOWN_CONNECT_EXCEPTION =
      "Exception was thrown when connecting to library API";
  public static final String DOWN_UNABLE_TO_CONNECT =
      "Unable to connect to library API - status code ";

  private HealthCheckMessages() {}
}
