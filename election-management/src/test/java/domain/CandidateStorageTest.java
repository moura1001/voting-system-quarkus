package domain;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public abstract class CandidateStorageTest {
    public abstract CandidateStorage storage();

    @Test
    void shouldBeSaveACandidate() {
        Candidate candidate = Instancio.create(Candidate.class);
        storage().save(candidate);

        Optional<Candidate> result = storage().getCandidateById(candidate.id());

        assertTrue(result.isPresent());
        assertEquals(candidate, result.get());
    }
}