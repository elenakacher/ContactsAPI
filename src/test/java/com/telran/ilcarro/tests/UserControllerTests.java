package com.telran.ilcarro.tests;

import com.jayway.restassured.RestAssured;
import com.telran.ilcarro.dto.RegistrationRequestDto;
import com.telran.ilcarro.dto.UserBaseDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class UserControllerTests {

    String token = "Basic aGVsZW4xMjNAZ21haWwuY29tOks3MTAwNTk2Y18=";

    @BeforeMethod
    public void ensurePrecondition() {
        RestAssured.baseURI = "https://java-3-ilcarro-team-b.herokuapp.com";
    }

    @Test (priority = 0)
    public void RegistrationPositiveTest() {
        RegistrationRequestDto requestDto = RegistrationRequestDto.builder()
                .first_name("Olena")
                .second_name("Kacher")
                .build();

        String firstName = given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(requestDto)
                .post("registration")
                .then()
                .assertThat().statusCode(200)
                .extract().path("first_name");
        System.out.println("First registration. First_name: " + firstName);


        int statusCode = given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(requestDto)
                .post("registration")
                .then()
                .assertThat().statusCode(409)
                .extract().statusCode();

        System.out.println("Second registration. StatusCode: " + statusCode);
        System.out.println("-------------");

    }

    @Test (priority = 1)
    public void LoginPositiveTest() {
        Object firstName = given().header("Authorization", token)
                .get("user/login")
                .then()
                .assertThat().statusCode(200)
                .extract().path("first_name");
        System.out.println("Login. First_name: " + firstName);
        System.out.println("-------------");

    }

    @Test (priority = 2)
    public void UpdateUserPositiveTest() {

        UserBaseDto userBaseDto = UserBaseDto.builder()
                .first_name("Oxana")
             //   .photo("Photo")
                .second_name("Kachr")
                .build();

        String firstName = given().header("Authorization", token)
                .contentType("application/json")
                .body(userBaseDto)
                .put("user")
                .then()
                .assertThat().statusCode(200)
                .extract().path("first_name");

        System.out.println("After User update first_name: " + firstName);
        System.out.println("-------------");
    }

    @Test (priority = 3)
    public void DeleteUserPositiveTest() {
        int s = given().header("Authorization", token)
                .delete("user")
                .then()
                .assertThat().statusCode(200)
                .extract().statusCode();

        System.out.println("User was deleted. StatusCode: " + s);
        System.out.println("-------------");
    }
}
