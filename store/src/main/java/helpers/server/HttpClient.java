package helpers.server;

import static io.restassured.RestAssured.given;

public class HttpClient {

    public static final String CLIENT_USERNAME = "user";
    public static final String CLIENT_PASSWORD = "password";

    public void getProducts() {
        given().
                auth().
                basic(CLIENT_USERNAME, CLIENT_PASSWORD).
        when().
                get("http://localhost:8088/online-store").
        then().
                log().body();
    }

    public void addToCart() {
        given().
                auth().
                basic(CLIENT_USERNAME, CLIENT_PASSWORD).
        when().
                get("http://localhost:8088/cart").
        then().
                log().body();
    }
}
