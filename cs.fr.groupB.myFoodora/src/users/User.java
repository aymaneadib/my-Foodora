package users;

import java.util.*;

public abstract class User {

    protected static int idCounter = 0;
    protected static Set<String> usernamesUsed = new HashSet<>();

    protected String name;
    protected String username;
    protected String password;
    protected int id;

    public User(String name, String username, String password) throws BadUserCreationException {
        if (usernamesUsed.contains(username)) {
            throw new BadUserCreationException("Username already used: " + username);
        }
        this.name = name;
        this.username = username;
        this.password = password;
        this.id = idCounter++;
        usernamesUsed.add(username);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws BadUserCreationException {
        if (usernamesUsed.contains(username)) {
            throw new BadUserCreationException("Username already used: " + username);
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    @Override
    public abstract String toString();

}
