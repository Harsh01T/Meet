/*Entry Point*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the University Social Networking App!");

        AuthenticationService authService = new AuthenticationService();
        SocialNetwork socialNetwork = new SocialNetwork();
        LocationService locationService = new LocationService();
        MessagingService messagingService = new MessagingService();
        
        Scanner scanner = new Scanner(System.in);

        // Sample Run: Sign Up and Login
        System.out.println("Sign up with your university email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        if (authService.signUp(name, email)) {
            System.out.println("Sign-up successful! Please log in.");
        } else {
            System.out.println("Invalid email. Only university emails allowed.");
            return;
        }

        System.out.println("Login with your email: ");
        String loginEmail = scanner.nextLine();

        User currentUser = authService.login(loginEmail);
        if (currentUser == null) {
            System.out.println("Login failed.");
            return;
        }

        System.out.println("Welcome, " + currentUser.getName() + "!");
        
        // Sample Social Feature - Adding Friend
        System.out.println("Enter the email of the person you want to connect with: ");
        String friendEmail = scanner.nextLine();
        User friend = socialNetwork.addFriend(currentUser, friendEmail);

        if (friend != null) {
            System.out.println("You are now friends with " + friend.getName());
        }

        // Map and Location Features
        locationService.addLocation(currentUser, "CSE Building");
        locationService.getUserLocation(currentUser);

        // Messaging Feature
        System.out.println("Enter a message for your friend:");
        String message = scanner.nextLine();
        messagingService.sendMessage(currentUser, friend, message);
    }
}
