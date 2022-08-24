package com.telran.contacts.tests;

import com.jayway.restassured.RestAssured;
import com.telran.contacts.dto.ContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class DeleleContactByIdRestAssuredTests {

    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InNhc2NoYUBnbWFpbC5jb20ifQ.zQj-8z_wlZDanL2YGSS5wQ8wfBdysEnHQN8zMYyDioc";
    int id;

    @BeforeMethod
    public void ensurePrecondition() {
        RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
        RestAssured.basePath = "api";

        int i = (int) ((System.currentTimeMillis()/1000)%3600);

        ContactDto contactDto = ContactDto.builder()
                .address("Jena")
                .description("Manager")
                .email("tom" + i + "@mail.ru")
                .lastName("Tom")
                .name("Black")
                .phone("234" + i + "567 765")
                .build();

        id = given().header("Authorization", token)
                .contentType("application/json")
                .body(contactDto)
                .post("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().path("id");
    }

    @Test
    public void deleteByIdTest() {
        String status = given().header("Authorization", token)
                .delete("/contact/" + id)
                .then()
                .assertThat().statusCode(200)
                .extract().path("status");

        System.out.println(status);
    }


}
