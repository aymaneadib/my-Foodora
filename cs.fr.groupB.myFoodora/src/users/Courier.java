package users;

public class Courier extends Person{
    
    private Location position;
    private String phoneNumber;
    private int deliveryCount;
    private boolean onDuty;

    public Courier(String name, String surname, String username, String password, Location position, String phoneNumber) throws BadUserCreationException {
        super(name, surname,username, password);
        if (phonesUsed.contains(phoneNumber)) {
            throw new BadUserCreationException("Phone number already used by another account: " + phoneNumber);
        }
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.deliveryCount = 0;
        this.onDuty = false;
    }

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws BadUserCreationException {
        if (phonesUsed.contains(phoneNumber)) {
            throw new BadUserCreationException("Phone number already used by another account: " + phoneNumber);
        }
        this.phoneNumber = phoneNumber;
    }

    public int getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(int deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public boolean isOnDuty() {
        return onDuty;
    }

    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }

    @Override
    public String toString() {
        return name + " " + surname + " ( " + username + " - Courrier with " + deliveryCount + " deliveries )"; 
    }

    public void incrementDeliveryCount() {
        this.deliveryCount++;
    }
}
