package infra.repository;

import domain.Candidate;
import domain.CandidateQuery;
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

    @Override
    public List<Candidate> getAllCandidates(CandidateQuery query) {
        return null;
    }
}
