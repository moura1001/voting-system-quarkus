package infra.repository;

import domain.*;
import infra.entity.ElectionCandidateEntity;
import infra.entity.ElectionEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@ApplicationScoped
@Named("electionRepositoryStorage")
public class ElectionRepositoryStorage implements ElectionStorage {
    @Inject
    private EntityManager entityManager;

    @Override
    @Transactional
    public void submit(Election election) {
        ElectionEntity electionEntity = ElectionEntity.toEntity(election);
        entityManager.merge(electionEntity);

        election.votes().entrySet().stream()
                .map(entry -> ElectionCandidateEntity.toEntity(election, entry.getKey(), entry.getValue()))
                .forEach(entityManager::merge);
    }

    @Override
    public List<Election> getAllElections() {
        return findAll();
    }

    public List<Election> findAll() {
        Stream<Object[]> stream = entityManager.createNativeQuery("SELECT e.id AS election_id " +
                        "c.id AS candidate_id, c.photo, c.given_name, c.family_name, c.email, c.phone, c.job_title, " +
                        "ec.votes " +
                        "FROM election e INNER JOIN election_candidate ec ON ec.election_id = e.id " +
                        "INNER JOIN candidate c ON ec.candidate_id = c.id")
                .getResultStream();

        Map<String, List<Object[]>> map = stream.collect(groupingBy(o -> (String) o[0]));

        return map.entrySet().stream()
                .map(entry -> {
                    Map.Entry<Candidate, Integer>[] candidates = entry.getValue().stream()
                            .map(row ->
                                Map.entry(
                                        new Candidate(
                                                (String) row[1],
                                                Optional.ofNullable((String) row[2]),
                                                (String) row[3],
                                                (String) row[4],
                                                (String) row[5],
                                                Optional.ofNullable((String) row[6]),
                                                Optional.ofNullable((String) row[7])
                                        ),
                                        (Integer) row[8]
                                )

                            )
                            .toArray(Map.Entry[]::new);

                    return new Election(entry.getKey(), Map.ofEntries(candidates));
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void sync(Election updatedElection) {
        updatedElection.votes().entrySet().stream()
                .map(entry -> ElectionCandidateEntity.toEntity(updatedElection, entry.getKey(), entry.getValue()))
                .forEach(entityManager::merge);
    }
}
