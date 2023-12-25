package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    protected void initTests() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    protected RequestSpecification getRequestSpecification(String host) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(host)
                .build();
    }

    protected RequestSpecification getRequestSpecificationWithHeaders(String host, String headerKey, String headerValue) {
        return new RequestSpecBuilder()
                .addHeader(headerKey, headerValue)
                .setContentType(ContentType.JSON)
                .setBaseUri(host)
                .build();
    }

    protected ResponseSpecification getResponseSpecification(String host) {
        return new ResponseSpecBuilder()
                .setDefaultParser(Parser.JSON)
                .build();
    }
}
