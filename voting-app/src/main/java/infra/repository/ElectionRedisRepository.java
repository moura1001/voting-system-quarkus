package infra.repository;

import domain.Candidate;
import domain.Election;
import domain.ElectionStorage;
import io.quarkus.cache.CacheResult;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.KeyCommands;
import io.quarkus.redis.datasource.sortedset.SortedSetCommands;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Named("electionRedisRepository")
public class ElectionRedisRepository implements ElectionStorage {
    private static final Logger LOGGER = Logger.getLogger(ElectionRedisRepository.class);
    private final SortedSetCommands<String, String> sortedSetCommands;
    private final KeyCommands<String> keyCommands;

    public ElectionRedisRepository(RedisDataSource redisDataSource) {
        sortedSetCommands = redisDataSource.sortedSet(String.class, String.class);
        keyCommands = redisDataSource.key(String.class);
    }

    @Override
    @CacheResult(cacheName = "memoization")
    public Election getElectionById(String id) {
        LOGGER.info("Retrieving election " + id + " from Redis");

        List<Candidate> candidates = sortedSetCommands.zrange("election-" + id, 0, -1).stream()
                .map(s -> new Candidate(s)).collect(Collectors.toList());

        return new Election(id, candidates);
    }

    @Override
    public List<Election> getAllEctions() {
        LOGGER.info("Retrieving elections from Redis");
        return keyCommands.keys("election-*").stream()
                .map(id -> getElectionById(id.replace("election-", "")))
                .collect(Collectors.toList());
    }

    @Override
    public void vote(String electionId, String candidateId) {
        LOGGER.info("Voting into election " + electionId + " for candidate " + candidateId);
        sortedSetCommands.zincrby("election-"+electionId, 1, candidateId);
    }
}
