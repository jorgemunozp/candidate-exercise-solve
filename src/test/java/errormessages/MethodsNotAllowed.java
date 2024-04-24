package errormessages;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static utils.Utils.validRandom;

public class MethodsNotAllowed {

    public static final String ROOT_URL = "http://127.0.0.1:5001/";
    public static final String ERROR_1 = "Woof! Method Not Allowed";
    public static final String ERROR_2 = "Ru Roh Raggy, This should'nt have succeeded";

    @Test
    public void RootURLPostMethod(){

        RestAssured.post(ROOT_URL)
                .then()
                .statusCode(200)
                .body(equalTo(ERROR_2));
    }

    @Test
    public void RootURLGetMethod(){

        RestAssured.get(ROOT_URL)
                .then()
                .statusCode(405)
                .body(equalTo(ERROR_1));
    }

    @Test
    public void RootURLDeleteMethod(){
        RestAssured.delete(ROOT_URL)
                .then()
                .statusCode(405)
                .body(equalTo(ERROR_1));
    }

    @Test
    public void RootURLPutMethod(){
        RestAssured.put(ROOT_URL)
                .then()
                .statusCode(405)
                .body(equalTo(ERROR_1));
    }

    @Test
    public void RootURLPatchMethod(){
        RestAssured.patch(ROOT_URL)
                .then()
                .statusCode(405)
                .body(equalTo(ERROR_1));
    }

    @Test
    public void SlashDogSlashDeleteMethod(){
        RestAssured.delete(ROOT_URL + "dogs/")
                .then()
                .statusCode(405)
                .body(equalTo(ERROR_1));
    }

    @Test
    public void SlashDogSlashPutMethod(){
        RestAssured.put(ROOT_URL + "dogs/")
                .then()
                .statusCode(405)
                .body(equalTo(ERROR_1));
    }

    @Test
    public void SlashDogSlashPatchMethod(){
        RestAssured.patch(ROOT_URL + "dogs/")
                .then()
                .statusCode(405)
                .body(equalTo(ERROR_1));
    }

    @Test
    public void SlashDogSlashDogIdPostMethod(){

        int item = validRandom();

        RestAssured.post(ROOT_URL + "dogs/" + item)
                .then()
                .statusCode(405)
                .body(equalTo(ERROR_1));
    }

    @Test
    public void SlashDogSlashDogIdPutMethod(){

        int item = validRandom();

        RestAssured.put(ROOT_URL + "dogs/" + item)
                .then()
                .statusCode(405)
                .body(equalTo(ERROR_1));
    }

    @Test
    public void SlashDogSlashDogIdPatchMethod(){

        int item = validRandom();

        RestAssured.patch(ROOT_URL + "dogs/" + item)
                .then()
                .statusCode(405)
                .body(equalTo(ERROR_1));
    }

}
