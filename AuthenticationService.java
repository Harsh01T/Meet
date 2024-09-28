import java.util.HashSet;

public class AuthenticationService {
    private HashSet<User> users = new HashSet<>();
    private static final String UNIVERSITY_DOMAIN = "@university.edu";

    public boolean signUp(String name, String email) {
        if (!email.endsWith(UNIVERSITY_DOMAIN)) {
            return false;  // Only university email allowed
        }
        User newUser = new User(name, email);
        users.add(newUser);
        return true;
    }

    public User login(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;  // Login failed
    }
}
