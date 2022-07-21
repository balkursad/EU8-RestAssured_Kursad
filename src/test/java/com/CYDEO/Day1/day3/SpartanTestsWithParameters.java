package com.CYDEO.Day1.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {

    @BeforeAll
    public static void init(){
        baseURI = "http://3.87.15.70:8000";
    }

    /*   Given accept type is Json
             And Id parameter value is 5
             When user sends GET request to /api/spartans/{id}
             Then response status code should be 200
             And response content-type: application/json
             And "Blythe" should be in response payload
          */

    @DisplayName("GET request to /api/spartans/{id} with ID 5")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 5)
                .get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType() );

        assertTrue(response.body().asString().contains("Blythe"));

        System.out.println("response.body().toString() = " + response.body().toString());
        System.out.println("response.body().asString() = " + response.body().asString());

    }

    /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get("/api/spartans/{id}");

        assertEquals(404, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("404"));



    }

    /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get("/api/spartans/search");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("Female"));

        assertFalse(response.body().asString().contains("Male"));

        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();


    }
    @Test
    public void test4(){
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("gender", "Female");
        testMap.put("nameContains", "e");
        Response response = given().accept(ContentType.JSON)
                .and().params(testMap)
                .and().get("/api/spartans/search");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("Female"));

        assertFalse(response.body().asString().contains("Male"));

        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();


    }

}
