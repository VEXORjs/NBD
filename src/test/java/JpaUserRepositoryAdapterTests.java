
import adapter.JpaUserRepositoryAdapter;
import entity.BuyerEntity;
import entity.SellerEntity;
import entity.UserEntity;
import jakarta.persistence.EntityManager;
import model.Buyer;
import model.Seller;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.UserEntityRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JpaUserRepositoryAdapterTest {

    private FakeUserEntityRepository userEntityRepository;
    private JpaUserRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        userEntityRepository = new FakeUserEntityRepository();
        adapter = new JpaUserRepositoryAdapter(userEntityRepository);
    }

    @Test
    void shouldSaveBuyerEntityWhenUserIsBuyer() {
        Buyer buyer = new Buyer(45L, "kacper", false, 1.2323f);

        adapter.save(buyer);

        assertInstanceOf(BuyerEntity.class, userEntityRepository.savedEntity);
    }

    @Test
    void shouldSaveSellerEntityWhenUserIsSeller() {
        Seller seller = new Seller(343L, "hubert", true, "243434342425");

        adapter.save(seller);

        assertInstanceOf(SellerEntity.class, userEntityRepository.savedEntity);
    }

    @Test
    void shouldReturnUserWhenUserExists() {
        Long userId = 1L;
        BuyerEntity buyerEntity = new BuyerEntity();
        buyerEntity.setId(userId);
        buyerEntity.setUsername("kacper");

        userEntityRepository.save(buyerEntity);
        userEntityRepository.entityToReturn = buyerEntity;

        Optional<User> result = adapter.findById(userId);

        assertTrue(result.isPresent());
        assertEquals("kacper", result.get().getUsername());
        assertEquals(userId, userEntityRepository.lastRequestedId);
    }

    @Test
    void shouldReturnEmptyOptionalWhenUserDoesNotExist() {
        Long userId = 999L;
        userEntityRepository.entityToReturn = null;

        Optional<User> result = adapter.findById(userId);

        assertTrue(result.isEmpty());
        assertEquals(userId, userEntityRepository.lastRequestedId);
    }

    private static class FakeUserEntityRepository
            extends UserEntityRepository {

        private UserEntity savedEntity;
        private UserEntity entityToReturn;
        private Long lastRequestedId;

        FakeUserEntityRepository() {
            super((EntityManager) null);
        }

        @Override
        public void save(UserEntity entity) {
            savedEntity = entity;
        }

        @Override
        public Optional<UserEntity> findById(Long id) {
            lastRequestedId = id;
            return Optional.ofNullable(entityToReturn);
        }
    }
}
