package api.web;

import api.CandidateApi;
import api.dto.request.CandidateCreateDTO;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.instancio.Instancio;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@QuarkusTest
@TestHTTPEndpoint(CandidateController.class)
class CandidateControllerTest {
    @InjectMock
    private CandidateApi candidateApi;

    @Test
    void shouldBeCreateACandidate() {
        CandidateCreateDTO candidateDTO = Instancio.create(CandidateCreateDTO.class);

        given().contentType(MediaType.APPLICATION_JSON).body(candidateDTO)
                        .when().post()
                        .then().statusCode(RestResponse.StatusCode.CREATED);

        verify(candidateApi).create(candidateDTO);
        verifyNoMoreInteractions(candidateApi);
    }
}