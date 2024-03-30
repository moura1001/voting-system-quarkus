package infra.repository;

import domain.Candidate;
import domain.CandidateStorage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;

@ApplicationScoped
@Named("candidateRepositoryStorage")
public class CandidateRepositoryStorage implements CandidateStorage {
    @Override
    public List<Candidate> save(List<Candidate> candidates) {
        return null;
    }
}
