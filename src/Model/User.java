package Model;

/**Manage User objects within program. */
public class User {
    private int userID;
    private String userName;
    private String password;

    /**User class constructor.
     * @param userID int autogenerated ID.
     * @param userName String user's username.
     * @param password String user's password. */
    public User(int userID, String userName, String password) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }

    /**Get user ID.
     * @return this.userID int.*/
    public int getUserID() {
        return userID;
    }

    /**Set user ID.
     * @param userID int. */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**Get user name.
     * @return this.userName. */
    public String getUserName() {
        return userName;
    }

    /**Set user name.
     * @param userName String. */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**Get user password.
     * @return this.password. */
    public String getPassword() {
        return password;
    }

    /**Set user password.
     * @param password String.*/
    public void setPassword(String password) {
        this.password = password;
    }
}
