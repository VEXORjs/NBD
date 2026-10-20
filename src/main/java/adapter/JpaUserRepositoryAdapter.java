package adapter;

import entity.BuyerEntity;
import entity.SellerEntity;
import entity.UserEntity;
import interfaces.UserRepository;
import model.Buyer;
import model.Seller;
import model.User;
import repository.UserEntityRepository;

import java.util.Optional;

public class JpaUserRepositoryAdapter implements UserRepository {

    private final UserEntityRepository userEntityRepository;

    public JpaUserRepositoryAdapter(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userEntityRepository.findById(id)
                .map(UserEntity::toDomain);
    }

    @Override
    public void save(User user) {
        UserEntity entity = null;
        if (user instanceof Buyer buyer) {
            entity = BuyerEntity.fromDomain(buyer);
        } else if (user instanceof Seller seller) {
            entity = SellerEntity.fromDomain(seller);
        }

        userEntityRepository.save(entity);
    }
}