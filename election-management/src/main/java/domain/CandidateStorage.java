package domain;

import java.util.List;

public interface CandidateStorage {
    List<Candidate> save(List<Candidate> candidates);

    default Candidate save(Candidate candidate) {
        List<Candidate> candidates = save(List.of(candidate));
        if (candidates != null && !candidates.isEmpty())
            return candidates.get(0);

        return null;
    }
}
