package domain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ApplicationScoped
public class ElectionService {
    @Inject
    @Named("electionRedisRepository")
    private ElectionStorage electionStorage;

    public List<Election> getAllEctions() {
        return electionStorage.getAllEctions();
    }
}
