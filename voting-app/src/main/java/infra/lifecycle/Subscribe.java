package infra.lifecycle;

import domain.Election;
import infra.repository.ElectionRedisRepository;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.runtime.Startup;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@Startup
@ApplicationScoped
public class Subscribe {
    private static final Logger LOGGER = Logger.getLogger(Subscribe.class);

    public Subscribe(
            RedisDataSource redisDataSource,
            ElectionRedisRepository repository
    ) {
        LOGGER.info("Startup: Subscribe");

        redisDataSource.pubsub(String.class).subscribe("elections", id -> {
            LOGGER.info("Election " + id + "received from subscription");
            Election election = repository.getElectionById(id);
            LOGGER.info("Election " + election.id() + " starting...");
        });
    }
}
