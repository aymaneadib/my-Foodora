package users;

import java.util.HashSet;
import java.util.Set;

public abstract class Person extends User {

    protected static Set<String> phonesUsed = new HashSet<>();

    protected String surname;

    public Person(String name, String surname, String username, String password) throws BadUserCreationException {
        super(name, username, password);
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public abstract String toString() ;

}