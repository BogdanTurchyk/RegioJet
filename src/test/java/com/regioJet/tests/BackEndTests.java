package com.regioJet.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

public class BackEndTests {

    @Test
    public void apiTest() {

        baseURI = "https://brn-ybus-pubapi.sa.cz/restapi/routes/search/simple";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParam("tariffs", "REGULAR")
                .queryParam("toLocationType", "CITY")
                .queryParam("toLocationId", "10202002")
                .queryParam("fromLocationType", "CITY")
                .queryParam("fromLocationId", "10202000")
                .queryParam("departureDate", "2023-04-24")
                .when().get()
                .then().statusCode(200)
                .extract().response();

        // to print response body
        response.prettyPrint();

/**
 * Next, we can use deserialization to convert JSON Response to Java Collection/Data structure or convert JSON to Custom Java Classes
 * and perform the actions we need, for example, comparing the expected and actual results.
 * I did it in the task class for Front End test cases, so I won't repeat it.
 */
    }
}


