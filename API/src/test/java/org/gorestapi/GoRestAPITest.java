package org.gorestapi;


import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestAPITest {
    @Test
    public void createUser() {
        String response = given().log().all()
                .baseUri("https://gorest.co.in")
                .auth().oauth2("a57d70d4d4a523a348c344eed13207d0ea397036586825eb6e1f7492b15ff955")
                .body("{\n" +
                        "    \"name\": \"Veer\",\n" +
                        "    \"email\": \"veer1@gmail.com\",\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}")
                .header("Content-Type", "application/json")

                .when()
                .post("/public/v2/users")

                .then().log().all()
                .statusCode(201)
                .body("name", equalTo("Veer"))
                .extract().response().asPrettyString();
        System.out.println(response);
    }

    @Test
    public void getUser() {
        String response = given()
                .baseUri("https://gorest.co.in")
                .auth().oauth2("a57d70d4d4a523a348c344eed13207d0ea397036586825eb6e1f7492b15ff955")
                .header("Content-Type", "application/json")

                .when()
                .get("/public/v2/users")

                .then()
                .statusCode(200)
                .extract().response().asPrettyString();
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

        Assert.assertEquals(name,"XYZ ABC");

        //3. Delete created user
        given().log().all()
                .baseUri("https://gorest.co.in")
                .header("Authorization", "Bearer bbc4d7508de4ddab7d4b31cd9c12907a0c756dd6b70f82f1cbe2962c1c512d00")
                .header("Content-Type" ,"*/*")

                .when()
                .delete("public/v2/users/" + id)

                .then().log().all()
                .statusCode(204);



    }

}

