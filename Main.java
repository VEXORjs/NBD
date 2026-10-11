import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
        EntityManager entityManager = emf.createEntityManager();

        AuctionRepository repositoryAdapter = new JpaAuctionRepositoryAdapter(entityManager);
        AuctionService auctionService = new AuctionService(repositoryAdapter, entityManager);

        Long auctionId = 1L;
        Long buyerId = 100L;
        BigDecimal bidAmount = new BigDecimal("150.00");

        try {
            auctionService.placeBid(auctionId, buyerId, bidAmount);
            System.out.println("Oferta złożona pomyślnie!");
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        } finally {
            entityManager.close();
            emf.close();
        }
    }
}