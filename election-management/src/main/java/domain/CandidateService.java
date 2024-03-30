package domain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ApplicationScoped
public class CandidateService {
    @Inject
    @Named("candidateRepositoryStorage")
    private CandidateStorage candidateStorage;

    public Candidate save(Candidate candidate) {
        return candidateStorage.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return candidateStorage.getAllCandidates();
    }
}
