package users;

public class Courier extends Person{
    
    private Location position;
    private String phoneNumber;
    private int deliveryCounter;
    private boolean onDuty;

    public Courier(String name, String surname, String username, String password, String phoneNumber, Location position) throws BadUserCreationException {
        super(name, surname,username, password);
        if (phonesUsed.contains(phoneNumber)) {
            throw new BadUserCreationException("Phone number already used by another account: " + phoneNumber);
        }
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.deliveryCounter = 0;
        this.onDuty = false;
        phonesUsed.add(phoneNumber);
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

    public int getDeliveryCounter() {
        return deliveryCounter;
    }

    public void setDeliveryCounter(int deliveryCounter) {
        this.deliveryCounter = deliveryCounter;
    }

    public boolean isOnDuty() {
        return onDuty;
    }

    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }

    @Override
    public String toString() {
        return name + " " + surname + " ( " + username + " - Courrier with " + deliveryCounter + " deliveries )"; 
    }

    public void incrementDeliveryCount() {
        this.deliveryCounter++;
    }
}
