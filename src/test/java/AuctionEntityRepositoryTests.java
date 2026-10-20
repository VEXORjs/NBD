import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.AuctionEntityRepository;
import entity.AuctionEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AuctionEntityRepositoryTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private AuctionEntityRepository auctionRepository;

    @BeforeAll
    static void setUpClass() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("persistence_unit");
    }

    @BeforeEach
    void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        auctionRepository = new AuctionEntityRepository(entityManager);
    }

    @Test
    void shouldSaveAndFindAuction() {
        entityManager.getTransaction().begin();

        AuctionEntity auction = new AuctionEntity();
        auctionRepository.save(auction);

        entityManager.flush();
        Long id = auction.getId();

        entityManager.clear();

        Optional<AuctionEntity> found = auctionRepository.findById(id);

        assertTrue(found.isPresent());

        entityManager.getTransaction().commit();
    }

    @AfterAll
    static void tearDownClass() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}