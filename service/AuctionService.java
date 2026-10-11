import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.OptimisticLockException;

public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final EntityManager entityManager;

    public AuctionService(AuctionRepository auctionRepository, EntityManager entityManager) {
        this.auctionRepository = auctionRepository;
        this.entityManager = entityManager;
    }

    public void placeBid(AuctionId auctionId, BuyerId buyerId, Money bidAmount) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            // 1. Otwarcie transakcji (Atomicity)
            transaction.begin();

            // 2. Pobranie czystego modelu z bazy
            Auction auction = auctionRepository.findById(auctionId);

            // 3. Wywołanie logiki biznesowej z modelu DDD
            auction.placeBid(bidAmount, buyerId, LocalDateTime.now());

            // 4. Zapisanie zmian
            auctionRepository.save(auction);

            // 5. Zatwierdzenie transakcji
            transaction.commit();

        } catch (OptimisticLockException e) {
            transaction.rollback();
            throw new ConcurrentBiddingException("Aukcja została zaktualizowana przez innego użytkownika.", e);
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}