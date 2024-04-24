package errormessages;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class InvalidDoggyPayload {

    public static final String BASE_URL = "http://127.0.0.1:5001/dogs/";
    public static final String ERROR = "Invalid Doggy Payload";

    @Test
    public void WrongBreedData(){
        String payload = "{\"breed\": true, \"age\": 2, \"name\": \"Marley\"}";
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(BASE_URL)
                .then()
                    .statusCode(422)
                    .body(equalTo(ERROR));
    }

    @Test
    public void WrongAgeData(){
        String payload = "{\"breed\": \"border collie\", \"age\": \"Two\", \"name\": \"Marley\"}";
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(BASE_URL)
                .then()
                    .statusCode(422)
                    .body(equalTo(ERROR));
    }

    @Test
    public void WrongAgeData2(){
        String payload = "{\"breed\": \"border collie\", \"age\": -3, \"name\": \"Marley\"}";
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(BASE_URL)
                .then()
                    .statusCode(422)
                    .body(equalTo(ERROR));
    }

    @Test
    public void WrongNameData(){
        String payload = "{\"breed\": \"border collie\", \"age\": 2, \"name\": 11}";
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(BASE_URL)
                .then()
                    .statusCode(422)
                    .body(equalTo(ERROR));
    }

    @Test
    public void MissingBreedKey(){
        String payload = "{\"age\": 2, \"name\": \"Marley\"}";
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(BASE_URL)
                .then()
                    .statusCode(422)
                    .body(equalTo(ERROR));
    }

    @Test
    public void MissingAgeKey(){
        String payload = "{\"breed\": \"border collie\", \"name\": \"Marley\"}";

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(BASE_URL)
                .then()
                    .statusCode(422)
                    .body(equalTo(ERROR));
    }

    @Test
    public void MissingNameKey(){
        String payload = "{\"breed\": \"border collie\", \"age\": 2}";

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(422)
                .body(equalTo(ERROR));
    }


}
