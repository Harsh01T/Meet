import java.sql.*;

public class Database {
    private Connection connection;

    // Constructor for establishing the database connection
    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/university_social_network", "root", "password");
            System.out.println("Database connected successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insert a new user into the database
    public void addUser(String name, String email, String role) {
        String query = "INSERT INTO users (name, email, role) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, role);
            statement.executeUpdate();
            System.out.println("User added to the database: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a user by their email
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("role"),
                    resultSet.getString("location")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // User not found
    }

    // Add a friend connection between two users
    public void addFriend(int userId, int friendId) {
        String query = "INSERT INTO friends (user_id, friend_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, friendId);
            statement.executeUpdate();
            System.out.println("Friend added: user_id = " + userId + ", friend_id = " + friendId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a message between users
    public void sendMessage(int senderId, int receiverId, String message) {
        String query = "INSERT INTO messages (sender_id, receiver_id, message) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, senderId);
            statement.setInt(2, receiverId);
            statement.setString(3, message);
            statement.executeUpdate();
            System.out.println("Message sent from user " + senderId + " to user " + receiverId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Close the database connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
