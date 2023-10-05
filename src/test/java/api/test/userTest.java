package api.test;

import api.endpoints.userEndpoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class userTest {

    Faker faker;
    User userPayload;

    public Logger logger;

    @BeforeClass
    public void setupData(){

        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //Logs
        logger = LogManager.getLogger(this.getClass());



    }

    @Test(priority = 1)
    public void testPostuser(){

        logger.info("*** create user ***");

        Response response = userEndpoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("*** user is created ***");



    }

    @Test(priority = 2)

    public void testGetuserbyname(){

        logger.info("*** Reading user ***");


        Response response = userEndpoints.getUser(this.userPayload.getUsername());
        response.then().log().all();

        logger.info("*** user info is read ***");




    }

    @Test(priority = 3)

    public void testUpdateuser(){

        logger.info("*** update user ***");


        //update data
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());


        Response response = userEndpoints.updateUser(userPayload, this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);


        logger.info("*** user is updated ***");



    }

    @Test(priority = 4)
    public void testDeleteuserbyname(){

        logger.info("*** deleteuser ***");


        Response response = userEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();

        logger.info("*** deleted user ***");




    }
}
