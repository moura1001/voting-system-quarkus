package infra.repository;

import domain.Election;
import domain.ElectionStorage;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.quarkus.redis.datasource.sortedset.SortedSetCommands;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
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
}
