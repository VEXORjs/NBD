package adapter;

import entity.AuctionEntity;
import model.Auction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.AuctionEntityRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JpaAuctionRepositoryAdapterTest {

    @Mock
    private AuctionEntityRepository entityRepository;

    @Mock
    private AuctionEntity auctionEntity;

    @Mock
    private Auction auctionDomain;

    @InjectMocks
    private JpaAuctionRepositoryAdapter adapter;

    @Test
    void shouldReturnAuctionWhenAuctionExists() {
        Long auctionId = 100L;
        when(entityRepository.findById(auctionId)).thenReturn(Optional.of(auctionEntity));
        when(auctionEntity.toDomain()).thenReturn(auctionDomain);

        Optional<Auction> result = adapter.findById(auctionId);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(auctionDomain);
        verify(entityRepository).findById(auctionId);
    }

    @Test
    void shouldReturnEmptyOptionalWhenAuctionDoesNotExist() {
        Long auctionId = 999L;
        when(entityRepository.findById(auctionId)).thenReturn(Optional.empty());

        Optional<Auction> result = adapter.findById(auctionId);

        assertThat(result).isEmpty();
        verify(entityRepository).findById(auctionId);
    }

    @Test
    void shouldSaveAuctionEntity() {
        Auction auction = mock(Auction.class);

        adapter.save(auction);

        verify(entityRepository).save(any(AuctionEntity.class));
    }
}