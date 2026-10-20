import entity.BuyerEntity;
import entity.SellerEntity;
import entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserEntityRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityRepositoryTest {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("persistence_unit");

    private EntityManager entityManager;
    private UserEntityRepository userEntityRepository;

    @BeforeEach
    void setUp() {
        entityManager = emf.createEntityManager();
        userEntityRepository = new UserEntityRepository(entityManager);
    }

    @Test
    void shouldSaveAndFindBuyerEntity() {
        entityManager.getTransaction().begin();

        BuyerEntity buyerEntity = new BuyerEntity();
        buyerEntity.setUsername("kacper");
        buyerEntity.setArchived(false);
        buyerEntity.setBalance(12.20f);

        userEntityRepository.save(buyerEntity);

        entityManager.flush();
        Long id = buyerEntity.getId();
        entityManager.clear();

        Optional<UserEntity> foundUser =
                userEntityRepository.findById(id);

        assertTrue(foundUser.isPresent());
        assertInstanceOf(BuyerEntity.class, foundUser.get());

        entityManager.getTransaction().commit();
    }

    @Test
    void shouldSaveAndFindSellerEntity() {
        entityManager.getTransaction().begin();

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setUsername("hubert");
        sellerEntity.setArchived(true);
        sellerEntity.setBankAccountNumber("34242434435");

        userEntityRepository.save(sellerEntity);

        entityManager.flush();
        Long id = sellerEntity.getId();
        entityManager.clear();

        Optional<UserEntity> foundUser =
                userEntityRepository.findById(id);

        assertTrue(foundUser.isPresent());
        assertInstanceOf(SellerEntity.class, foundUser.get());

        entityManager.getTransaction().commit();
    }

    @AfterAll
    static void tearDown() {
        emf.close();
    }
}
