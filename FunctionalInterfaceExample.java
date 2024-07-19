import java.util.function.Predicate;
import java.util.function.Consumer;

public class FunctionalInterfaceExample {

    public static void main(String[] args) {
        
        Predicate<Integer> isPositive = num -> num > 0;
        System.out.println("Is 10 positive? " + isPositive.test(10)); 
        System.out.println("Is -5 positive? " + isPositive.test(-5)); 
        
        
        Consumer<String> printUpperCase = str -> System.out.println(str.toUpperCase());
        printUpperCase.accept("hello"); 
    }
}
