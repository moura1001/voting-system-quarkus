package infra.scheduler;

import domain.Election;
import infra.repository.ElectionRedisStorage;
import infra.repository.ElectionRepositoryStorage;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class SyncFromRedis {
    @Inject
    private ElectionRepositoryStorage repositoryStorage;

    @Inject
    private ElectionRedisStorage redisStorage;

    @Scheduled(cron = "*/10 * * * * ?")
    void sync() {
        List<Election> elections = repositoryStorage.findAll();

        elections.forEach(election -> {
            Election updatedElection = redisStorage.sync(election);
            repositoryStorage.sync(updatedElection);
        });
    }
}
