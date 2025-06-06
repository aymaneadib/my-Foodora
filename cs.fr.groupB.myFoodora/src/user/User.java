package user;

import java.util.*;

/**
 * The User class represents a user in the MyFoodora system.
 * It provides methods for managing user information and ensuring unique usernames.
 * 
 * @author Aymane Adib
 */
public abstract class User {

    protected static int idCounter = 0; // Counter for unique user IDs
    protected static Set<String> usernamesUsed = new HashSet<>(); // Set of used usernames

    protected String name;
    protected String username;
    protected String password;
    protected int id;
    protected boolean active;

    /**
     * Constructor for the User class.
     *
     * @param name     the name of the user
     * @param username the username of the user
     * @param password the password of the user
     * @throws BadUserCreationException if there is an error creating the user
     */
    public User(String name, String username, String password) throws BadUserCreationException {
    	if (usernamesUsed.contains(username)) {
            throw new BadUserCreationException("Username already used: " + username);
        }
        this.name = name;
        this.username = username;
        this.password = password;
        this.id = idCounter++;
        usernamesUsed.add(username);
        this.active = true;
    }

    /**
     * Constructor for the User class with only a password.
     * This constructor is used when the name and username are not provided.
     * @param password
     */
    public User(String password) {
        this.password = password;
        this.id = idCounter++;
        this.active = true;
        this.name = "User " + this.id; // Default name if not provided
        this.username = "user_" + this.id; // Default username if not provided
    }
    
    /**
     * Adds the given username to the static collection of usernames already in use.
     *
     * @param username the username to be added to the set of used usernames.
     */
    public static void addUsernameToUsernamesUsed(String username) {
    	User.usernamesUsed.add(username);
    }
    
    /**
     * Removes the given username from the static collection of usernames already in use.
     *
     * @param username the username to be removed from the set of used usernames.
     */
    public static void removeUsernameFromUsernamesUsed(String username) {
    	User.usernamesUsed.remove(username);
    }
    
    /**
     * Removes all usernames from the static collection of usernames already in use.
     *
     */
    public static void clearUsernamesFromUsernamesUsed() {
    	User.usernamesUsed.clear();
    }
    
    /**
     * Returns if the user is active.
     *
     * @return the state of user.
     */
    public boolean isActive() {
    	return this.active;
    }
    
    /**
     * Activates user.
     */
    public void activateUser() {
    	this.active = true;
    }
    
    /**
     * Deactivates user.
     */
    public void deactivateUser() {
    	this.active = false;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Verifies if the argument user is equals to current user.
     * Two users are equals if they have the same id.
     *
     * @param obj the other user to be compared with
     * @return true if the ids are equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the new username of the user
     * @throws BadUserCreationException if the username is already used
     */
    public void setUsername(String username) throws BadUserCreationException {
        if (usernamesUsed.contains(username)) {
            throw new BadUserCreationException("Username already used: " + username);
        }
        this.username = username;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    public int getId() {
        return id;
    }

    @Override
    public abstract String toString();

}
