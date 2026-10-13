package adapter;

import jakarta.persistence.*;
import model.User;
import model.Buyer;
import model.Seller;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(name = "is_archived")
    private boolean isArchived;

    protected UserEntity() {}

    public UserEntity(Long id, String username, boolean isArchived) {
        this.id = id;
        this.username = username;
        this.isArchived = isArchived;
    }

    public abstract User toDomain();

    public static UserEntity fromDomain(User domain) {
        if (domain == null) {
            return null;
        }

        if (domain instanceof Buyer buyer) {
            return BuyerEntity.fromDomain(buyer);
        }

        // else if (domain instanceof Seller seller) {
        //     return SellerEntity.fromDomain(seller);
        // }

        throw new IllegalArgumentException("Nieznany typ obiektu dziedzinowego: " + domain.getClass().getSimpleName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isArchived() { return  isArchived; }

    public void setArchived(boolean isArchived) { this.isArchived = isArchived; }
}