import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class RestfulBookerTests {
    @BeforeEach
    public void setup() {
        // Establece la URL base para las solicitudes
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
    @Test
    public void pingTest(){
        Response response = RestAssured
                .when().get("/ping");
        response.then().assertThat().statusCode(201);
        response.then().log().body();
    }
    @Test
    public void getBookingTest() {
        Response response = RestAssured
                .given().pathParam("id", "1")
                .when().get("/booking/{id}");

        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("size()", not(0)); // Verificar que el cuerpo no esté vacío
        response.then().log().body(); // Mostrar el cuerpo de la respuesta
    }
    @Test
    public void getAllBookings(){
        Response response = RestAssured
                .when().get("/booking");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("size()", not(0)); // Verificar que el cuerpo no esté vacío
        response.then().log().body(); // Mostrar el cuerpo de la respuesta
    }
    @Test
    public void createBookingWithClassesTest() {
        // Crear un objeto BookingDates con las fechas de check-in y check-out
        BookingDates bookingDates = new BookingDates("2023-10-10", "2023-10-15");

        // Crear un objeto Booking con los detalles de la reserva
        Booking booking = new Booking("Sebas", "Davalos", 150, true, bookingDates, "Breakfast");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json") // Header 'Accept' agregado
                .body(booking)
                .when().post("/booking");

        response.then().assertThat().statusCode(200);

        response.then().assertThat().body("booking.firstname", equalTo("Sebas"));
        response.then().log().body();
    }
    @Test
    public void testBookingsWithSpecificDates() {
        // Cambia las fechas según sea necesario
        String checkin = "2019-03-14";
        String checkout = "2019-12-15";

        Response response = given()
                .header("Accept", "application/json") // Header 'Accept' agregado
                .queryParam("checkin", checkin)        // Parámetro dinámico de checkin
                .queryParam("checkout", checkout)      // Parámetro dinámico de checkout
                .when().get("/booking");

        // Verificar que el código de respuesta sea 200
        response.then().assertThat().statusCode(200);
        response.then().log().body();
    }

}
