package domain;

import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;

import java.util.List;
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

    @Test
    void shouldBeFindAllCandidates() {
        List<Candidate> candidates = Instancio.stream(Candidate.class).limit(10).toList();
        storage().save(candidates);

        List<Candidate> result = storage().getAllCandidates();

        assertTrue(result.size() == candidates.size() && result.containsAll(candidates) && candidates.containsAll(result));
    }

    @Test
    void shouldBeFindCandidateByName() {
        Candidate candidate1 = Instancio.create(Candidate.class);
        Candidate candidate2 = Instancio.of(Candidate.class)
                .set(Select.field("familyName"), "Moura").create();

        CandidateQuery query = new CandidateQuery.Builder().name("Moura").build();

        storage().save(List.of(candidate1, candidate2));

        List<Candidate> result = storage().getAllCandidates(query);
        assertEquals(1, result.size());
        assertEquals(candidate2, result.get(0));
    }
}
