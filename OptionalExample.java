import java.util.Optional;


public class OptionalExample {


    public static Optional<String> getUsername(int userId) {
        if (userId == 1) {
            return Optional.of("Alice");
        } else {
            return Optional.empty();
        }
    }


    public static void main(String[] args) {
        int userId = 1;
        Optional<String> usernameOptional = getUsername(userId);


        if (usernameOptional.isPresent()) {
            String username = usernameOptional.get();
            System.out.println("Username: " + username.toUpperCase());
        } else {
            System.out.println("Username not found for userId: " + userId);
        }
    }
}






