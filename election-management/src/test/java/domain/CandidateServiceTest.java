package domain;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;

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

}