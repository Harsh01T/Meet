import java.util.HashMap;

public class SocialNetwork {
    private HashMap<String, User> users = new HashMap<>();

    public SocialNetwork() {
        // Simulate pre-existing users
        users.put("john@university.edu", new User("John Doe", "john@university.edu"));
        users.put("jane@university.edu", new User("Jane Smith", "jane@university.edu"));
    }

    public User addFriend(User currentUser, String friendEmail) {
        User friend = users.get(friendEmail);
        if (friend != null) {
            System.out.println(currentUser.getName() + " and " + friend.getName() + " are now connected.");
        } else {
            System.out.println("User not found.");
        }
        return friend;
    }
}
