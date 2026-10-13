import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Auction;
import model.Buyer;
import repository.JpaAuctionRepositoryAdapter;
import repository.JpaUserRepositoryAdapter;
import service.AuctionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("Inicjalizacja połączenia z bazą danych...");
        // 1. Uruchomienie Hibernate z użyciem konfiguracji "default" z persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // 2. Utworzenie instancji adapterów (warstwa infrastruktury)
        JpaUserRepositoryAdapter userRepository = new JpaUserRepositoryAdapter(em);
        JpaAuctionRepositoryAdapter auctionRepository = new JpaAuctionRepositoryAdapter(em);

        // 3. Utworzenie serwisów aplikacyjnych
        AuctionService auctionService = new AuctionService(auctionRepository, em);

        try {
            System.out.println("Tworzenie danych testowych...");
            // Otwieramy transakcję do wygenerowania początkowych danych
            em.getTransaction().begin();

            // Tworzymy czysty obiekt domenowy Kupującego (ID = null, bo wygeneruje je baza)
            Buyer buyer = new Buyer(null, "jan_kowalski", false, 1500.0f);
            userRepository.save(buyer);

            // Tworzymy czysty obiekt domenowy Aukcji
            LocalDateTime start = LocalDateTime.now().minusDays(1);
            LocalDateTime end = LocalDateTime.now().plusDays(7);
            Auction auction = new Auction(null,null, "Laptop Dell", new BigDecimal("2500.00"), new ArrayList<>(), start, end);
            auctionRepository.save(auction);

            // Zatwierdzamy zapis początkowy do bazy
            em.getTransaction().commit();
            System.out.println("Dane zapisane w bazie.");

            // 4. Testowanie logiki biznesowej i współbieżności (Serwis obsługuje własne transakcje)
            System.out.println("Składanie oferty w serwisie...");

            // Zakładamy ID = 1L, ponieważ wrzuciliśmy to do czystej bazy jako pierwsze rekordy
            Long buyerId = 1L;
            Long auctionId = 1L;
            BigDecimal bidAmount = new BigDecimal("2600.00");

            // Uruchamiamy scenariusz użycia z Twojego AuctionService
            auctionService.placeBid(auctionId, buyerId, bidAmount);

            System.out.println("Oferta przebiegła pomyślnie! Kwota: " + bidAmount);

        } catch (Exception e) {
            // Obsługa błędów, np. cofnięcie transakcji w razie wystąpienia błędu
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Wystąpił błąd podczas działania aplikacji: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 5. Zawsze pamiętaj o zamknięciu zasobów na końcu
            em.close();
            emf.close();
            System.out.println("Zakończono działanie aplikacji i zamknięto połączenie.");
        }
    }
}