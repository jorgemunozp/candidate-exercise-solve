package basicflows;

import dataproviders.DogsDataProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static utils.Utils.validRandom;

public class BasicFlow {


    public static final String BASE_URL = "http://127.0.0.1:5000/dogs/";


    @Test(priority = 1)
    public void GetAllDogsWithNoData(){

        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .assertThat()
                .body("$", anEmptyMap());
    }


    @Test(dataProvider = "TestData", dataProviderClass = DogsDataProvider.class, priority = 2)
    public void CreateDogs(String breed, String breedValue, String age, int ageValue, String name, String nameValue){

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put(breed, breedValue);
        requestBody.put(age, ageValue);
        requestBody.put(name, nameValue);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(200)
                .body("breed", equalTo(breedValue))
                .body("age", equalTo(ageValue))
                .body("name", equalTo(nameValue));

    }

    @Test(priority = 3)
    public void ValidateDataTypesGettingAllDogs(){

        int item = validRandom();

        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .body(item + ".age", isA(Integer.class))
                .body(item + ".breed", isA(String.class))
                .body(item + ".id", isA(String.class))
                .body(item + ".name", isA(String.class));

        //int numberOfReturnedElements = response.jsonPath().getList("$").size();
    }


    @Test(priority = 4)
    public void ValidateResponseSizeGettingAllDogs(){

        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .body("$",aMapWithSize(5));

    }

    @Test(priority = 5)
    public void ValidateDataTypeForSpecificDog(){

        int item = validRandom();

        RestAssured.get(BASE_URL + item)
                .then()
                .statusCode(200)
                .body("age", isA(Integer.class))
                .body("breed", isA(String.class))
                .body("id", isA(String.class))
                .body("name", isA(String.class));

    }

    @Test(priority = 6)
    public static void DeleteDog(){

        int item = validRandom();

        Response response = RestAssured.get(BASE_URL + item);
        int age = response.jsonPath().getInt("age");
        String breed = response.jsonPath().getString("breed");
        String name = response.jsonPath().getString("name");
        String id = response.jsonPath().getString("id");

        RestAssured
                .delete(BASE_URL + item)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("breed", equalTo(breed))
                .body("age", equalTo(age));

        RestAssured
                .get(BASE_URL + item)
                .then()
                .statusCode(404);
    }

}
