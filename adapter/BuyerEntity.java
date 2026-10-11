@Entity
@Table(name = "buyers")
public class BuyerEntity extends UserEntity {
    @Column(name = "delivery_address")
    private String deliveryAddress;

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}