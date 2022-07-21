package com.CYDEO.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanTestBase {

    @BeforeAll
    public static void init(){
        baseURI = "http://3.87.15.70:8000";
    }



}
