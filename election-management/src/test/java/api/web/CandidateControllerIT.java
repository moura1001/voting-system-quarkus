package api.web;

import api.dto.request.CandidateCreateDTO;
import api.dto.request.CandidateUpdateDTO;
import api.dto.response.CandidateResponseDTO;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.instancio.Instancio;
import org.instancio.Select;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusIntegrationTest
@TestHTTPEndpoint(CandidateController.class)
class CandidateControllerIT {

    @Test
    void shouldBeCreateACandidate() {
        CandidateCreateDTO candidateDTO = Instancio.create(CandidateCreateDTO.class);

        given().contentType(MediaType.APPLICATION_JSON).body(candidateDTO)
                        .when().post()
                        .then().statusCode(RestResponse.StatusCode.CREATED);
    }

    @Test
    void shouldBeUpdateInfoAboutACandidate() {
        String id = UUID.randomUUID().toString();
        CandidateUpdateDTO updateDTO = Instancio.create(CandidateUpdateDTO.class);

        CandidateUpdateDTO candidateUpdateDTO = Instancio.of(CandidateUpdateDTO.class)
                .set(Select.field("photo"), updateDTO.photo())
                .set(Select.field("givenName"), "Genival")
                .set(Select.field("familyName"), "Moura")
                .set(Select.field("email"), updateDTO.email())
                .set(Select.field("phone"), updateDTO.phone())
                .set(Select.field("jobTitle"), updateDTO.jobTitle())
                .create();

        var response1 = given().contentType(MediaType.APPLICATION_JSON).body(updateDTO)
                .when().put("/"+id)
                .then().statusCode(RestResponse.StatusCode.ACCEPTED).extract().as(CandidateResponseDTO.class);

        var response2 = given().contentType(MediaType.APPLICATION_JSON).body(candidateUpdateDTO)
                .when().put("/"+id)
                .then().statusCode(RestResponse.StatusCode.ACCEPTED).extract().as(CandidateResponseDTO.class);

        assertEquals(id, response1.id());
        assertEquals(id, response2.id());
        assertNotEquals(response1.familyName(), response2.familyName());
        assertEquals(response1.email(), response2.email());
        assertEquals(response1.phone(), response2.phone());
        assertEquals(response1.jobTitle(), response2.jobTitle());
    }
}