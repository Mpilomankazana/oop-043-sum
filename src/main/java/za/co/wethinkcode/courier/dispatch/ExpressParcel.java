package za.co.wethinkcode.courier.dispatch;

public class ExpressParcel extends Parcel implements Insurable {

    public static final double BASE_FEE = 120.00;
    public static final double FEE_PER_KG = 12.00;
    public static final int DELIVERY_DAYS = 1;
    public static final double INSURANCE_RATE = 0.015;

    private final double declaredValue;

    public ExpressParcel(String trackingNumber, String senderName, String recipientAddress, double weightKg,
                         double declaredValue) {
        super(trackingNumber, senderName, recipientAddress, weightKg);
        if (declaredValue < 0) {
            throw new IllegalArgumentException("declaredValue cannot be negative");
        }
        this.declaredValue = declaredValue;
    }

    @Override
    public double getDeclaredValue() {
        return declaredValue;
    }

    @Override
    public double insurancePremium() {
        return declaredValue * INSURANCE_RATE;
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
        return "Express";
    }

    @Override
    public String toString() {
        return serviceLevel() + " parcel: " + super.toString();
    }
}
