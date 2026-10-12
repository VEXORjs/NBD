package repository;

import pl.lodz.p.auction.domain.model.User;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    void save(User user);
}