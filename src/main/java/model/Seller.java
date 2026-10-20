package model;

public class Seller extends User {

    private String bankAccountNumber;

    public Seller(Long id, String username, boolean isArchived, String bankAccountNumber) {
        super(id, username, isArchived);
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}