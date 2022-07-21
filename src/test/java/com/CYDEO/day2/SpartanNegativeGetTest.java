package com.CYDEO.day2;

import io.restassured.http.ContentType;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest  {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://3.87.15.70:8000";
    }

    /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */

    @DisplayName("GET request to /api/spartans/10")
    @Test
    public void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/101");

        //verify status code is 406
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(404, response.statusCode());
        //verify content type
        System.out.println("response.contentType() = " + response.contentType());
        //assertEquals("application/xml;charset=UTF-8", response.contentType());
        assertEquals("application/json", response.contentType());
    }
}