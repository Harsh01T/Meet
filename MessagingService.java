/*Basic messaging*/

public class MessagingService {
    public void sendMessage(User sender, User receiver, String message) {
        System.out.println(sender.getName() + " to " + receiver.getName() + ": " + message);
    }
}
