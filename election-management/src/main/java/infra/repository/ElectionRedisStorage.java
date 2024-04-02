package infra.repository;

import domain.Candidate;
import domain.Election;
import domain.ElectionStorage;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.quarkus.redis.datasource.sortedset.ScoreRange;
import io.quarkus.redis.datasource.sortedset.ScoredValue;
import io.quarkus.redis.datasource.sortedset.SortedSetCommands;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@Named("electionRedisStorage")
public class ElectionRedisStorage implements ElectionStorage {

    private final SortedSetCommands<String, String> commands;
    private final PubSubCommands<String> pubSubCommands;

    public ElectionRedisStorage(RedisDataSource redisDataSource) {
        commands = redisDataSource.sortedSet(String.class, String.class);
        pubSubCommands = redisDataSource.pubsub(String.class);
    }

    @Override
    public void submit(Election election) {
        Map<String, Double> rank = election.votes().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().id(),
                        entry -> entry.getValue().doubleValue()
                ));

        commands.zadd("election-"+election.id(), rank);
        pubSubCommands.publish("elections", election.id());
    }

    @Override
    public List<Election> getAllElections() {
        return null;
    }

    public Election sync(Election election) {
        List<ScoredValue<String>> scoredValues = commands.zrangebyscoreWithScores(
                "election-" + election.id(),
                ScoreRange.from(Integer.MIN_VALUE, Integer.MIN_VALUE)
        );
        var map = scoredValues.stream().map(scoredValue -> {
            Candidate candidate = election.votes().keySet().stream()
                    .filter(c -> c.id().equals(scoredValue.value()))
                    .findFirst()
                    .orElseThrow(() -> {
                        throw new RuntimeException("candidate " + scoredValue.value() + " does not have registration in the election " + election.id());
                    });
            return Map.entry(candidate, (int) scoredValue.score());

        }).toArray(Map.Entry[]::new);

        return new Election(election.id(), Map.ofEntries(map));
    }
}
