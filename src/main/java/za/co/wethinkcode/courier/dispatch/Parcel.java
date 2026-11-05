package za.co.wethinkcode.courier.dispatch;

public abstract class Parcel {

    private final String trackingNumber;
    private final String senderName;
    private String recipientAddress;
    private double weightKg;
    private boolean delivered;

    public Parcel(String trackingNumber, String senderName, String recipientAddress, double weightKg) {
        if (weightKg <= 0) {
            throw new IllegalArgumentException("weightKg must be greater than zero");
        }
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.recipientAddress = recipientAddress;
        this.weightKg = weightKg;
        this.delivered = false;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void updateRecipientAddress(String recipientAddress) {
        if (delivered) {
            throw new IllegalStateException("Cannot change the address of a delivered parcel");
        }
        this.recipientAddress = recipientAddress;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void updateWeightKg(double weightKg) {
        if (weightKg <= 0) {
            throw new IllegalArgumentException("weightKg must be greater than zero");
        }
        this.weightKg = weightKg;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void markDelivered() {
        this.delivered = true;
    }

    @Override
    public String toString() {
        return trackingNumber + " from " + senderName + " to " + recipientAddress + " (" + weightKg + "kg)";
    }

    /**
     * Returns the delivery fee for this parcel in Rands.
     */
    public abstract double deliveryFee();

    /**
     * Returns the number of working days this parcel takes to be delivered.
     */
    public abstract int deliveryDays();

    /**
     * Returns a short label for this parcel's service level, e.g. "Standard".
     */
    public abstract String serviceLevel();
}
