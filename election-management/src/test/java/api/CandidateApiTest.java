package api;

import api.dto.request.CandidateCreateDTO;
import api.dto.request.CandidateUpdateDTO;
import api.dto.response.CandidateResponseDTO;
import domain.Candidate;
import domain.CandidateService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.inject.Inject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class CandidateApiTest {
    @Inject
    private CandidateApi candidateApi;

    @InjectMock
    private CandidateService candidateService;

    @Test
    void shouldBeCreateACandidate() {
        CandidateCreateDTO candidateDTO = Instancio.create(CandidateCreateDTO.class);
        ArgumentCaptor<Candidate> captor = ArgumentCaptor.forClass(Candidate.class);

        candidateApi.create(candidateDTO);

        verify(candidateService).save(captor.capture());
        verifyNoMoreInteractions(candidateService);

        Candidate candidate = captor.getValue();

        assertEquals(candidateDTO.photo(), candidate.photo());
        assertEquals(candidateDTO.givenName(), candidate.givenName());
        assertEquals(candidateDTO.familyName(), candidate.familyName());
        assertEquals(candidateDTO.email(), candidate.email());
        assertEquals(candidateDTO.phone(), candidate.phone());
        assertEquals(candidateDTO.jobTitle(), candidate.jobTitle());
    }

    @Test
    void shouldBeUpdateInfoAboutACandidate() {
        String id = UUID.randomUUID().toString();
        CandidateUpdateDTO candidateDTO = Instancio.create(CandidateUpdateDTO.class);
        Candidate candidate = candidateDTO.toDomain(id);
        ArgumentCaptor<Candidate> captor = ArgumentCaptor.forClass(Candidate.class);

        when(candidateService.getCandidateById(id)).thenReturn(candidate);

        CandidateResponseDTO response = candidateApi.update(id, candidateDTO);

        verify(candidateService).save(captor.capture());
        verify(candidateService).getCandidateById(id);
        verifyNoMoreInteractions(candidateService);

        assertEquals(CandidateResponseDTO.toDTO(candidate), response);
    }

    @Test
    void shouldBeListAllCandidates() {
        List<Candidate> candidates = Instancio.stream(Candidate.class).limit(10).toList();
        when(candidateService.getAllCandidates()).thenReturn(candidates);

        List<CandidateResponseDTO> result = candidateApi.list();

        verify(candidateService).getAllCandidates();
        verifyNoMoreInteractions(candidateService);

        List<CandidateResponseDTO> expected = candidates.stream()
                .map(CandidateResponseDTO::toDTO)
                .collect(Collectors.toList());

        assertTrue(expected.size() == result.size() && result.containsAll(expected) && expected.containsAll(result));
    }
}