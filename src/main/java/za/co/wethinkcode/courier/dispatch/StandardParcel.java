package za.co.wethinkcode.courier.dispatch;

public class StandardParcel extends Parcel {

    public static final double BASE_FEE = 60.00;
    public static final double FEE_PER_KG = 8.00;
    public static final int DELIVERY_DAYS = 5;

    public StandardParcel(String trackingNumber, String senderName, String recipientAddress, double weightKg) {
        super(trackingNumber, senderName, recipientAddress, weightKg);
    }

    @Override
    public double deliveryFee() {
        return BASE_FEE + (getWeightKg() * FEE_PER_KG);
    }

    @Override
    public int deliveryDays() {
        return DELIVERY_DAYS;
    }

    @Override
    public String serviceLevel() {
        return "Standard";
    }

    @Override
    public String toString() {
        return serviceLevel() + " parcel: " + super.toString();
    }
}
