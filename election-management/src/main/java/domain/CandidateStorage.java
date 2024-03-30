package domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CandidateStorage {
    void save(List<Candidate> candidates);

    default void save(Candidate candidate) {
        save(List.of(candidate));
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
