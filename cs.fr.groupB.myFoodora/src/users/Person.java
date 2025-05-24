package users;

import java.util.HashSet;
import java.util.Set;

/**
 * The Person class represents a person in the MyFoodora system.
 * It extends the User class and provides methods for managing personal information.
 * 
 * @author Aymane Adib
 */
public abstract class Person extends User {

    protected static Set<String> phonesUsed = new HashSet<>(); // Set of used phone numbers

    protected String surname;

    /**
     * Constructor for the Person class.
     *
     * @param name     the name of the person
     * @param surname  the surname of the person
     * @param username the username of the person
     * @param password the password of the person
     * @throws BadUserCreationException if there is an error creating the user
     */
    public Person(String name, String surname, String username, String password) throws BadUserCreationException {
        super(name, username, password);
        this.surname = surname;
    }
    
    /**
     * Adds the given phone to the static collection of phones already in use.
     *
     * @param phone the phone to be added to the set of used phones.
     */
    public static void addPhoneToPhonesUsed(String phone) {
    	Person.phonesUsed.add(phone);
    }
    
    /**
     * Removes the given phone from the static collection of phones already in use.
     *
     * @param phone the phone to remove from the set of used phones.
     */
    public static void removePhoneFromPhonesUsed(String phone) {
    	Person.phonesUsed.remove(phone);
    }

    /**
     * Returns the surname of the person.
     *
     * @return the surname of the person
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the person.
     *
     * @param surname the new surname of the person
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the string representation of the person.
     *
     * @return the string representation of the person
     */
    @Override
    public abstract String toString() ;

}