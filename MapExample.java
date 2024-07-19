import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapExample {

    public static void main(String[] args) {
        // Sample list of strings
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        // Convert each string to uppercase using map
        List<String> uppercaseWords = words.stream()
                                          .map(String::toUpperCase)
                                          .collect(Collectors.toList());

        // Print the uppercase words
        System.out.println("Uppercase Words:");
        uppercaseWords.forEach(System.out::println);
    }
}
