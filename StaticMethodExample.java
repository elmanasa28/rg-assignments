interface MathOperation {
    static int add(int a, int b) {
        return a + b;
    }
    
    static int subtract(int a, int b) {
        return a - b;
    }
}

public class StaticMethodExample {
    public static void main(String[] args) {
        int sum = MathOperation.add(10, 5);       // Using static method add()
        int difference = MathOperation.subtract(10, 5);  // Using static method subtract()
        
        System.out.println("Sum: " + sum);              // Output: Sum: 15
        System.out.println("Difference: " + difference); // Output: Difference: 5
    }
}
