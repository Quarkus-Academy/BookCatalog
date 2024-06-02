package com.ibm.quarkusacademy.book;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@QuarkusTest
class BookResourceTest {

  @Test
  @Disabled("Not used")
  void testHelloEndpoint() {
    given().when().get("/hello").then().statusCode(200).body(is("Hello from RESTEasy Reactive"));
  }

}
