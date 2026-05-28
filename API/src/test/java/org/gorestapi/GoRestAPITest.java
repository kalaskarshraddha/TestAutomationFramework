package org.gorestapi;


import io.restassured.RestAssured;
import org.apiautomation.pojo.createuserapi.CreateUserRequest;
import org.apiautomation.pojo.createuserapi.CreateUserResponse;
import org.apiautomation.pojo.getuserapi.GetUserResponse;
import org.apiautomation.utilis.PropertiesFileReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GoRestAPITest {

    private CreateUserRequest createUserRqstBody(String name, String email, String gender, String status) {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName(name);
        createUserRequest.setEmail(email);
        createUserRequest.setGender(gender);
        createUserRequest.setStatus(status);
        return createUserRequest;
    }

    @Test
    public void createUser() throws IOException {
        GoRestAPITest goRestAPITest = new GoRestAPITest();
        CreateUserRequest requestBody = goRestAPITest.createUserRqstBody("PQR123456789", "pqr123456789@gmail.com", "female", "active");
        CreateUserResponse response = given().log().all()
                .baseUri(PropertiesFileReader.getDataFromPropertiesFile("qa_env_apiconfig","baseuri"))
                .auth().oauth2(PropertiesFileReader.getDataFromPropertiesFile("qa_env_apiconfig","bearer_token"))
                .body(requestBody)
                .header("Content-Type", "application/json")


                .when()
                .post("/public/v2/users")

                .then().log().all()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/createUserResponseSchema.json"))
                .extract().response().as(CreateUserResponse.class);

        String name = response.getName();
        int id = response.getId();
        //.body("name", equalTo("PQR1"))
        // .extract().response().asPrettyString();
        //Assert.assertEquals(name, "PQR1");
        System.out.println(id);
    }

    @Test
    public void getUser() {
        GetUserResponse response = given().log().all()
                .baseUri("https://gorest.co.in")
                .auth().oauth2("a57d70d4d4a523a348c344eed13207d0ea397036586825eb6e1f7492b15ff955")
                .header("Content-Type", "application/json")
                .pathParam("xyz",8477611)
                .queryParam("page", 2)

                .when()
                .get("/public/v2/users/{xyz}")

                .then()
                .statusCode(200)
                .extract().response().as(GetUserResponse.class);
        System.out.println(response);

    }

    @Test
    public void checkUserFlow() {
        //1. create user
        int id = given()
                .baseUri("https://gorest.co.in")
                //.header("Authorization", "Bearer bbc4d7508de4ddab7d4b31cd9c12907a0c756dd6b70f82f1cbe2962c1c512d00")
                .auth().oauth2("bbc4d7508de4ddab7d4b31cd9c12907a0c756dd6b70f82f1cbe2962c1c512d00")
                //.header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{ \"name\": \"XYZ ABC\", \"email\": \"xyz@example.com\", \"gender\": \"male\", \"status\": \"active\" }")

                .when()
                .post("/public/v2/users")

                .then()
                .statusCode(201)
                .extract().response().jsonPath().get("id");
        System.out.println(id);

        //2. get user
        String name = given()
                .baseUri("https://gorest.co.in")
                .header("Authorization", "Bearer bbc4d7508de4ddab7d4b31cd9c12907a0c756dd6b70f82f1cbe2962c1c512d00")
                .header("Content-Type", "application/json")

                .when()
                .get("/public/v2/users/" + id)

                .then()
                .statusCode(200)
                //.body("name", equalTo("XYZ ABC"))
                .extract().response().jsonPath().get("name");
        //.extract().response().asPrettyString();
        System.out.println(name);

        Assert.assertEquals(name, "XYZ ABC");

        //3. Delete created user
        given().log().all()
                .baseUri("https://gorest.co.in")
                .header("Authorization", "Bearer bbc4d7508de4ddab7d4b31cd9c12907a0c756dd6b70f82f1cbe2962c1c512d00")
                .header("Content-Type", "*/*")

                .when()
                .delete("public/v2/users/" + id)

                .then().log().all()
                .statusCode(204);


    }

}

