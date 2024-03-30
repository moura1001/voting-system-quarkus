package infra.repository;

import domain.CandidateStorage;
import domain.CandidateStorageTest;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CandidateRepositoryStorageTest extends CandidateStorageTest {
    @Inject
    @Named("candidateRepositoryStorage")
    private CandidateRepositoryStorage candidateRepositoryStorage;

    @Inject
    private EntityManager entityManager;

    @Override
    public CandidateStorage storage() {
        return candidateRepositoryStorage;
    }

    @AfterEach
    @TestTransaction
    void tearDown() {
        entityManager.createNativeQuery("TRUNCATE TABLE candidate").executeUpdate();
    }
}