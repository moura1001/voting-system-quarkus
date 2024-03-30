package domain;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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

}