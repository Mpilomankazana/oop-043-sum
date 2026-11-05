package za.co.wethinkcode.courier.dispatch;

public class FragileParcel extends Parcel implements Insurable {

    public static final double BASE_FEE = 90.00;
    public static final double FEE_PER_KG = 10.00;
    public static final int DELIVERY_DAYS = 3;
    public static final double INSURANCE_RATE = 0.03;

    private final double declaredValue;
    private final String handlingNotes;

    public FragileParcel(String trackingNumber, String senderName, String recipientAddress, double weightKg,
                         double declaredValue, String handlingNotes) {
        super(trackingNumber, senderName, recipientAddress, weightKg);
        if (declaredValue < 0) {
            throw new IllegalArgumentException("declaredValue cannot be negative");
        }
        this.declaredValue = declaredValue;
        this.handlingNotes = handlingNotes;
    }

    public String getHandlingNotes() {
        return handlingNotes;
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
        return "Fragile";
    }

    @Override
    public String toString() {
        return serviceLevel() + " parcel: " + super.toString() + " [handling: " + handlingNotes + "]";
    }
}
