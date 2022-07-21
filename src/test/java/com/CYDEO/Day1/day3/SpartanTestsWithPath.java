package com.CYDEO.Day1.day3;

import com.CYDEO.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestsWithPath extends SpartanTestBase {





    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
     */

    @Test
    public void test1() {
        Response response = given().log().all().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("api/spartans/{id}");
        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);

        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);
    }


    @DisplayName("GET all spartan and navigate with Path()")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        //response.prettyPrint();

        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String name = response.path("name[0]");
        System.out.println("name = " + name);

        String lastFirstName = response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);

        //save names inside the list of string
        List<String> names = response.path("name");
        System.out.println(names);
        //print each name one by one
        for (String n : names) {
            System.out.println(n);
        }
    }
}
