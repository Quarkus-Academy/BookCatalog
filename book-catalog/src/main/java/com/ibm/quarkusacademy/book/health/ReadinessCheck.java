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

@Readiness
@ApplicationScoped
@SuppressWarnings("java:S6813")
public class ReadinessCheck implements HealthCheck {

  // Get library-api ConfigProperty
  @ConfigProperty(name = "quarkus.rest-client.\"library-api\".url")
  Optional<String> libraryApiUrl;

  @Override
  public HealthCheckResponse call() {
    // Create HC response builder
    HealthCheckResponseBuilder hcBuilder = HealthCheckResponse.named("library-api");
    // Get library-api URL from config, check if it is defined
    String libraryUrl = libraryApiUrl.orElse(null);
    if (libraryUrl != null) {
      hcBuilder.withData("url", libraryUrl);
    } else {
      return hcBuilder.down()
          .withData(HealthCheckMessages.HC_DATA_EXCEPTION, HealthCheckMessages.DOWN_URL_UNDEFINED)
          .build();
    }

    // Send GET request to the URL and get the response code
    int responseCode;
    try {
      URL url = new URL(libraryUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestMethod(HttpMethod.GET);
      responseCode = connection.getResponseCode();
    } catch (Exception exception) {
      Log.error(HealthCheckMessages.DOWN_CONNECT_EXCEPTION, exception);
      return hcBuilder.down().withData(HealthCheckMessages.HC_DATA_EXCEPTION,
          HealthCheckMessages.DOWN_CONNECT_EXCEPTION + " - " + exception.getMessage()).build();
    }

    // If response code is OK, return UP HC status, else DOWN status
    if (Status.OK.getStatusCode() == responseCode) {
      return hcBuilder.up().build();
    }

    return hcBuilder.down().withData(HealthCheckMessages.HC_DATA_EXCEPTION,
        HealthCheckMessages.DOWN_UNABLE_TO_CONNECT + responseCode).build();
  }

}
