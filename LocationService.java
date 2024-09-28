import java.util.HashMap;

public class LocationService {
    private HashMap<String, String> userLocations = new HashMap<>();
    private HashMap<String, Coordinates> campusMap = new HashMap<>();

    public LocationService() {
        // Define some static locations on the campus
        campusMap.put("CSE Building", new Coordinates(37.7749, -122.4194));
        campusMap.put("Math Building", new Coordinates(37.7750, -122.4183));
    }

    public void addLocation(User user, String location) {
        userLocations.put(user.getEmail(), location);
        user.setLocation(location);
        System.out.println(user.getName() + " is now located at " + location);
    }

    public void getUserLocation(User user) {
        String location = userLocations.get(user.getEmail());
        System.out.println(user.getName() + " is currently at: " + location);
    }
}
