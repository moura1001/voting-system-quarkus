package infra.repository;

import domain.Candidate;
import domain.CandidateStorage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named("candidateRepositoryStorage")
public class CandidateRepositoryStorage implements CandidateStorage {
    @Override
    public List<Candidate> save(List<Candidate> candidates) {
        return null;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return null;
    }

    @Override
    public Optional<Candidate> getCandidateById(String id) {
        return Optional.empty();
    }
}
