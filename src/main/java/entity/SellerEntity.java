package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import model.Seller;

@Entity
@Table(name = "sellers")
public class SellerEntity extends UserEntity {

    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    public SellerEntity() {}

    @Override
    public Seller toDomain() {
        return new Seller(
                this.getId(),
                this.getUsername(),
                this.isArchived(),
                this.bankAccountNumber
        );
    }

    public static SellerEntity fromDomain(Seller seller) {
        if (seller == null) {
            return null;
        }

        SellerEntity entity = new SellerEntity();
        entity.setId(seller.getId());
        entity.setUsername(seller.getUsername());
        entity.setArchived(seller.getIsArchived());
        entity.setBankAccountNumber(seller.getBankAccountNumber());
        return entity;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}