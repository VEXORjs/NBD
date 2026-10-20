package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import model.Buyer;

@Entity
@Table(name = "buyers")
public class BuyerEntity extends UserEntity {

    @Column(name = "balance")
    private float balance;

    public BuyerEntity() {}

    @Override
    public Buyer toDomain() {
        return new Buyer(
                this.getId(),
                this.getUsername(),
                this.isArchived(),
                this.balance);
    }

    public static BuyerEntity fromDomain(Buyer buyer) {
        if (buyer == null) {
            return null;
        }

        BuyerEntity entity = new BuyerEntity();
        entity.setId(buyer.getId());
        entity.setUsername(buyer.getUsername());
        entity.setArchived(buyer.getIsArchived());
        entity.setBalance(buyer.getBalance());
        return entity;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    }