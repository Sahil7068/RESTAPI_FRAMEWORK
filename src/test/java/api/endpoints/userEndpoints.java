package api.endpoints;


// created for CRUD methods

import api.payload.User;
import io.restassured.response.Response;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.given;

public class userEndpoints {

    public static Response createUser(User payload){

        Response res = given()

                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)


                .when()
                .post(routes.post_url);

        return res;
    }

    public static Response getUser(String username){

        Response res = given()
                .pathParam("username", username)




                .when()
                .get(routes.get_url);

        return res;

    }

    public static Response updateUser(User payload, String username){

        Response res = given()

                .pathParam("username", username)

                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)


                .when()
                .put(routes.put_url);

        return res;
    }

    public static Response deleteUser(String username){

        Response res = given()

                .pathParam("username", username)


                .when()
                .delete(routes.delete_url);

        return res;
    }


}
