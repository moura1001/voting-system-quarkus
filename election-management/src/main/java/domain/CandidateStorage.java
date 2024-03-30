package domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CandidateStorage {
    List<Candidate> save(List<Candidate> candidates);

    default Candidate save(Candidate candidate) {
        List<Candidate> candidates = save(List.of(candidate));
        if (candidates != null && !candidates.isEmpty())
            return candidates.get(0);

        return null;
    }

    List<Candidate> getAllCandidates(CandidateQuery query);

    default List<Candidate> getAllCandidates() {
        return getAllCandidates(new CandidateQuery.Builder().build());
    }

    default Optional<Candidate> getCandidateById(String id) {
        CandidateQuery query = new CandidateQuery.Builder().ids(Set.of(id)).build();
        return getAllCandidates(query).stream().findFirst();
    }
}
