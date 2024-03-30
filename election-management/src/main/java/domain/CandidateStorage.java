package domain;

import java.util.List;
import java.util.Optional;

public interface CandidateStorage {
    List<Candidate> save(List<Candidate> candidates);

    default Candidate save(Candidate candidate) {
        List<Candidate> candidates = save(List.of(candidate));
        if (candidates != null && !candidates.isEmpty())
            return candidates.get(0);

        return null;
    }

    List<Candidate> getAllCandidates();

    Optional<Candidate> getCandidateById(String id);
}
