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
class UserEntityRepositoryTest {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldSaveAndFindBuyerEntity() {
        BuyerEntity buyerEntity = new BuyerEntity("Jan", "Kowalski");

        BuyerEntity savedBuyer = userEntityRepository.save(buyerEntity);

        entityManager.flush();
        entityManager.clear();

        Optional<UserEntity> foundUser = userEntityRepository.findById(savedBuyer.getId());

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get()).isInstanceOf(BuyerEntity.class);
    }

    @Test
    void shouldSaveAndFindSellerEntity() {
        SellerEntity sellerEntity = new SellerEntity("Firma cos");

        SellerEntity savedSeller = userEntityRepository.save(sellerEntity);

        entityManager.flush();
        entityManager.clear();

        Optional<UserEnitity> foundUser = userEntityRepository.findById(savedSeller.getId());

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get()).isInstanceOf(SellerEntity.class);
    }
}