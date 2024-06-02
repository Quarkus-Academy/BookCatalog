package com.ibm.quarkusacademy.book.health;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.core.Response.Status;
import io.quarkus.logging.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@SuppressWarnings("java:S6813")
public class ReadinessCheck {

  // Get library-api ConfigProperty

  public void call() {
    // Create HC response builder

    // Get library-api URL from config, check if it is defined

    // Send GET request to the URL and get the response code, you can use HttpURLConnection
    int responseCode;

    // If response code is OK, return UP HC status, else DOWN status

  }

}
