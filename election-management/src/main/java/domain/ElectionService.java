package domain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ApplicationScoped
public class ElectionService {
    @Inject
    private CandidateService candidateService;

    @Inject
    @Named("electionRepositoryStorage")
    private ElectionStorage electionRepositoryStorage;

    @Inject
    @Named("electionRedisStorage")
    private ElectionStorage electionRedisStorage;

    public void submit() {
        Election election = Election.create(candidateService.getAllCandidates());
        electionRepositoryStorage.submit(election);
        electionRedisStorage.submit(election);
    }

    public List<Election> getAllElections() {
        return electionRepositoryStorage.getAllElections();
    }
}
