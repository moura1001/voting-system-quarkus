package infra.repository;

import domain.Candidate;
import domain.CandidateQuery;
import domain.CandidateStorage;
import infra.entity.CandidateEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        cq.select(root).where(conditions(query, cb, root));

        return entityManager.createQuery(cq)
                .getResultStream()
                .map(candidateEntity -> candidateEntity.toDomain())
                .collect(Collectors.toList());
    }

    private Predicate[] conditions(CandidateQuery query, CriteriaBuilder cb, Root<CandidateEntity> root) {
        return Stream.of(query.ids().map(id -> cb.in(root.get("id")).value(id)),
                        query.name().map(name -> cb.or(cb.like(cb.lower(root.get("familyName")), name.toLowerCase() + "%"),
                                                        cb.like(cb.lower(root.get("givenName")), name.toLowerCase() + "%"))
                        ))
                .flatMap(Optional::stream)
                .toArray(Predicate[]::new);
    }
}
