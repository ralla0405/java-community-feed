package store.kirinit.communityfeed.acceptance.steps;

import io.restassured.RestAssured;
import org.springframework.http.MediaType;
import store.kirinit.communityfeed.auth.application.dto.SendEmailRequestDto;

public class SignUpAcceptanceSteps {

    public static Integer requestSendEmail(SendEmailRequestDto dto) {
        return RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/signup/send-verification-email")
            .then()
            .extract()
            .jsonPath().get("code");
    }

    public static Integer requestVerifyEmail(String email, String token) {
        return RestAssured
            .given()
            .queryParam("email", email)
            .queryParam("token", token)
            .when()
            .get("/signup/verify-token")
            .then()
            .extract()
            .jsonPath().get("code");
    }
}
