package errormessages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static utils.Utils.invalidRandom;


public class DoggoNotFound {

    public static final String BASE_URL = "http://127.0.0.1:5000/dogs/";
    public static final String ERROR = "Doggo Not Found";

    @Test
    public void SearchDoggoNotFoundReturns404() {

        Random random = new Random();
        int item = random.nextInt(100) + 10;

        RestAssured.get(BASE_URL + item)
                .then()
                .statusCode(404);


    }

    @Test
    public void SearchDoggoNotFoundReturnsErrorMessage() {

        Random random = new Random();
        int item = random.nextInt(100) + 10;

        RestAssured.get(BASE_URL + item)
                .then()
                .body(equalTo(ERROR));

    }

    @Test
    public void DeleteDoggoNotFoundReturns404() {

        Random random = new Random();
        int item = random.nextInt(100) + 10;

        RestAssured.delete(BASE_URL + item)
                .then()
                .statusCode(404);


    }

    @Test
    public void DeleteDoggoNotFoundReturnsErrorMessage() {

        Random random = new Random();
        int item = random.nextInt(100) + 10;

        RestAssured.delete(BASE_URL + item)
                .then()
                .body(equalTo(ERROR));

    }

}
