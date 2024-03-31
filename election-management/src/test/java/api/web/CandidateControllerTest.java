package api.web;

import api.CandidateApi;
import api.dto.request.CandidateCreateDTO;
import api.dto.request.CandidateUpdateDTO;
import api.dto.response.CandidateResponseDTO;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.instancio.Instancio;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void shouldBeListAllCandidates() {
        List<CandidateResponseDTO> candidates = Instancio.stream(CandidateResponseDTO.class).limit(4).collect(Collectors.toList());

        when(candidateApi.list()).thenReturn(candidates);

        var response = given()
                .when().get()
                .then().statusCode(RestResponse.StatusCode.OK).extract().as(CandidateResponseDTO[].class);

        verify(candidateApi).list();
        verifyNoMoreInteractions(candidateApi);

        List<CandidateResponseDTO> result = Arrays.stream(response).toList();

        assertTrue(candidates.size() == result.size() && result.containsAll(candidates) && candidates.containsAll(result));
    }

    @Test
    void shouldBeUpdateInfoAboutACandidate() {
        String id = UUID.randomUUID().toString();
        CandidateUpdateDTO updateDTO = Instancio.create(CandidateUpdateDTO.class);
        CandidateResponseDTO result = CandidateResponseDTO.toDTO(updateDTO.toDomain(id));

        when(candidateApi.update(id, updateDTO)).thenReturn(result);

        given().contentType(MediaType.APPLICATION_JSON).body(updateDTO)
                .when().put("/"+id)
                .then().statusCode(RestResponse.StatusCode.ACCEPTED);

        verify(candidateApi).update(id, updateDTO);
        verifyNoMoreInteractions(candidateApi);
    }
}