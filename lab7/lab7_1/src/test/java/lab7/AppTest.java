package lab7;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class AppTest 
{
    private final String BASE_URL = "https://jsonplaceholder.typicode.com/todos";


    @Test
    public void testGetAllTodos() {
        given()
                .when()
                .get(BASE_URL)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testToDo4() {
        given()
            .when()
            .get(BASE_URL + "/4")
            .then()
            .assertThat()
            .body("title", equalTo("et porro tempora"));
    }

    @Test
    public void testCountIds() {
        given()
            .when()
            .get(BASE_URL) 
            .then()
            .assertThat()
            .body("id", hasItems(198, 199));
    }

    @Test
    public void testGetAllTodos_Performance() {
    given()
        .when()
        .get(BASE_URL)
        .then()
        .assertThat()
        .statusCode(200)
        .time(lessThan(2000L));
    }
}
