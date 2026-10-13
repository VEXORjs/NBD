package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.OptimisticLockException;
import model.Auction;
import repository.AuctionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final EntityManager entityManager;

    public AuctionService(AuctionRepository auctionRepository, EntityManager entityManager) {
        this.auctionRepository = auctionRepository;
        this.entityManager = entityManager;
    }

    public void placeBid(Long auctionId, Long buyerId, BigDecimal bidAmount) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Auction auction = auctionRepository.findById(auctionId)
                    .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono aukcji o ID: " + auctionId));

            auction.placeBid(bidAmount, buyerId, LocalDateTime.now());

            auctionRepository.save(auction);

            transaction.commit();

        } catch (OptimisticLockException e) {
            transaction.rollback();
            throw new RuntimeException("Aukcja została zaktualizowana przez innego użytkownika.", e);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}