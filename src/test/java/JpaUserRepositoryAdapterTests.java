package adapter;

import entity.BuyerEntity;
import entity.SellerEntity;
import entity.UserEntity;
import model.Buyer;
import model.Seller;
import model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.UserEntityRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JpaUserRepositoryAdapterTest {

    @Mock
    private UserEntityRepository userEntityRepository;

    @Mock
    private UserEntity userEntity;

    @Mock
    private User userDomain;

    @InjectMocks
    private JpaUserRepositoryAdapter adapter;

    @Test
    void shouldSaveBuyerEntityWhenUserIsBuyer() {

        Buyer buyer = mock(Buyer.class);


        adapter.save(buyer);

        verify(userEntityRepository).save(any(BuyerEntity.class));
    }

    @Test
    void shouldSaveSellerEntityWhenUserIsSeller() {

        Seller seller = mock(Seller.class);

        adapter.save(seller);

        verify(userEntityRepository).save(any(SellerEntity.class));
    }

    @Test
    void shouldReturnUserWhenUserExists() {

        Long userId = 1L;
        when(userEntityRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userEntity.toDomain()).thenReturn(userDomain);

        Optional<User> result = adapter.findById(userId);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(userDomain);
        verify(userEntityRepository).findById(userId);
    }

    @Test
    void shouldReturnEmptyOptionalWhenUserDoesNotExist() {

        Long userId = 999L;
        when(userEntityRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> result = adapter.findById(userId);

        assertThat(result).isEmpty();
        verify(userEntityRepository).findById(userId);
    }
}