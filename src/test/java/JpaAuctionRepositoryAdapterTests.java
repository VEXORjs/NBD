
import entity.AuctionEntity;
import jakarta.persistence.EntityManager;
import model.Auction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.AuctionEntityRepository;
import adapter.JpaAuctionRepositoryAdapter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JpaAuctionRepositoryAdapterTest {

    private FakeAuctionEntityRepository entityRepository;
    private JpaAuctionRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        entityRepository = new FakeAuctionEntityRepository();
        adapter = new JpaAuctionRepositoryAdapter(entityRepository);
    }

    @Test
    void shouldReturnAuctionWhenAuctionExists() {
        Long auctionId = 100L;

        AuctionEntity entity = new AuctionEntity();
        entity.setId(auctionId);
        entityRepository.auctionToReturn = entity;

        Optional<Auction> result = adapter.findById(auctionId);

        assertTrue(result.isPresent());
        assertEquals(entity.getId(), result.get().getId());
        assertEquals(auctionId, entityRepository.lastRequestedId);
    }

    @Test
    void shouldReturnEmptyOptionalWhenAuctionDoesNotExist() {
        Long auctionId = 999L;

        entityRepository.auctionToReturn = null;

        Optional<Auction> result = adapter.findById(auctionId);

        assertTrue(result.isEmpty());
        assertEquals(auctionId, entityRepository.lastRequestedId);
    }

    @Test
    void shouldSaveAuctionEntity() {
        Auction auction = new Auction(1L, BigDecimal.valueOf(1233.23), Collections.emptyList(), LocalDateTime.now(), LocalDateTime.now().plusDays(7), 0L);

        adapter.save(auction);

        assertNotNull(entityRepository.savedEntity);
    }

    private static class FakeAuctionEntityRepository
            extends AuctionEntityRepository {

        private AuctionEntity auctionToReturn;
        private AuctionEntity savedEntity;
        private Long lastRequestedId;

        FakeAuctionEntityRepository() {
            super((EntityManager) null);
        }

        @Override
        public Optional<AuctionEntity> findById(Long id) {
            lastRequestedId = id;
            return Optional.ofNullable(auctionToReturn);
        }

        @Override
        public void save(AuctionEntity entity) {
            savedEntity = entity;
        }
    }
}
