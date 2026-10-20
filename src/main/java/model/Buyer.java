package model;

public class Buyer extends User {
    private float balance;

    public Buyer(Long id, String username, boolean isArchived, float balance) {
        super(id, username, isArchived);
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean canBid(float newBidBalance) {
        return this.balance >= newBidBalance;
    }
}