package infra.repository;

import domain.Candidate;
import domain.CandidateQuery;
import domain.CandidateStorage;
import infra.entity.CandidateEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Named("candidateRepositoryStorage")
public class CandidateRepositoryStorage implements CandidateStorage {
    @Inject
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(List<Candidate> candidates) {
        candidates.stream()
                .map(CandidateEntity::toEntity)
                .forEach(entityManager::merge);
    }

    @Override
    public List<Candidate> getAllCandidates(CandidateQuery query) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(CandidateEntity.class);
        var root = cq.from(CandidateEntity.class);

        var where = query.ids().map(id -> cb.in(root.get("id")).value(id)).get();

        cq.select(root).where(where);

        return entityManager.createQuery(cq)
                .getResultStream()
                .map(candidateEntity -> candidateEntity.toDomain())
                .collect(Collectors.toList());
    }
}
