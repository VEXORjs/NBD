package repository;

import entity.BuyerEntity;
import entity.SellerEntity;
import entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuctionEntityRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private AuctionEntityRepository auctionRepository;

    @BeforeEach
    void setUp() {
        auctionRepository = new AuctionEntityRepository(testEntityManager.getEntityManager());
    }

    @Test
    void shouldSaveAndFindAuction() {
        AuctionEntity auction = new AuctionEntity("cos");

        auctionRepository.save(auction);

        testEntityManager.flush();
        testEntityManager.clear();

        Optional<AuctionEntity> found = auctionRepository.findById(auction.getId());
        assertThat(found).isPresent();
    }
}