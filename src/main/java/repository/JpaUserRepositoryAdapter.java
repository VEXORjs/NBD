package repository;

import adapter.UserEntity;
import jakarta.persistence.EntityManager;
import model.User;

import java.util.Optional;

public class JpaUserRepositoryAdapter implements UserRepository {

    private final EntityManager entityManager;

    public JpaUserRepositoryAdapter(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> findById(Long id) {
        UserEntity userEntity = this.entityManager.find(UserEntity.class, id);

        if (userEntity != null) {
            return Optional.of(userEntity.toDomain());
        }

        return Optional.empty();
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = UserEntity.fromDomain(user);

        this.entityManager.merge(userEntity);
    }
}