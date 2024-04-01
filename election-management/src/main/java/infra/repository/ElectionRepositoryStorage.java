package infra.repository;

import domain.*;
import infra.entity.ElectionCandidateEntity;
import infra.entity.ElectionEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
}
