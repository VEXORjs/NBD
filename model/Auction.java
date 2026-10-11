public class Auction {
    public void placeBid(Money bidAmount, BuyerId buyerid, LocalDateTime now){
        if (now.isBefore(startDate) || now.isAfter(endDate)){
            throw new IllegalArgumentException("Aukcja nie jest w tym momencie aktywna");
        }

        if (!bidAmount.isGreaterThan(currentPrice)){
            throw new IllegalArgumentException("Oferta musi byc wyzsza nic obecna cena");
        }

        this.currentPrice = bidAmount;
        Bid newBid = new Bid(bidAmount, buyerid, now);
        this.bids.add(newBid);
    }
}