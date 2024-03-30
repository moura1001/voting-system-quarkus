package domain;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class CandidateServiceTest {

    @Inject
    private CandidateService candidateService;

    @InjectMock
    @Named("candidateRepositoryStorage")
    private CandidateStorage candidateStorage;

    @Test
    void shouldBeSaveACandidate() {
        Candidate candidate = Instancio.create(Candidate.class);
        candidateService.save(candidate);

        verify(candidateStorage).save(candidate);
        verifyNoMoreInteractions(candidateStorage);
    }

    @Test
    void shouldBeFindAllCandidates() {
        List<Candidate> candidates = Instancio.stream(Candidate.class).limit(10).toList();
        when(candidateStorage.getAllCandidates()).thenReturn(candidates);

        List<Candidate> result = candidateService.getAllCandidates();

        verify(candidateStorage).getAllCandidates();
        verifyNoMoreInteractions(candidateStorage);

        assertEquals(candidates, result);
    }

    @Test
    void shouldBeFindCandidateById() {
        Candidate candidate = Instancio.create(Candidate.class);

        when(candidateStorage.getCandidateById(candidate.id())).thenReturn(Optional.of(candidate));

        Candidate result = candidateService.getCandidateById(candidate.id());

        verify(candidateStorage).getCandidateById(candidate.id());
        verifyNoMoreInteractions(candidateStorage);

        assertEquals(candidate, result);
    }

    @Test
    void shouldBeThrowsExceptionWhenCandidateIsNotFound() {
        Candidate candidate = new Candidate(
                "1a2b3c",
                Optional.empty(),
                "",
                "",
                "",
                Optional.empty(),
                Optional.empty()
        );

        when(candidateStorage.getCandidateById(candidate.id())).thenReturn(Optional.empty());

        RuntimeException thorwn = assertThrows(RuntimeException.class, () -> {
            Candidate result = candidateService.getCandidateById(candidate.id());
        });
        assertEquals("candidate " + candidate.id() + " does not exists", thorwn.getMessage());

        verify(candidateStorage).getCandidateById(candidate.id());
        verifyNoMoreInteractions(candidateStorage);
    }

}