package tms.project.pkg1;

public class User {
//    private String name;
//    private String email;
//    private String password;

    // Static valid credentials for login (for demonstration purposes)
    private static final String VALID_USER_EMAIL = "ad@ex.com";
    private static final String VALID_PASSWORD = "123";
    private String name;
    private String email;
    private String password;

    // Parameterized constructor for creating a new user with name, email, and password
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // No-argument constructor for creating a User instance for login validation
    public User() {
        // Empty constructor
    }

    // Login method for validating email and password
    public boolean login(String email, String password) {
        return email.equals(VALID_USER_EMAIL) && password.equals(VALID_PASSWORD);
    }

    // Method to simulate user registration (for demonstration purposes)
    public void register() {
        System.out.println("User registered: " + name);
    }

    // Method to request a tour (assumes Tour class exists)
    public Tour requestTour() {
        Tour tour = new Tour(this);  // Tour class should accept User as an argument in its constructor
        System.out.println("Tour requested by: " + name);
        return tour;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }
}
