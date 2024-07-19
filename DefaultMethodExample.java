interface Vehicle {
    void start();
    
    default void stop() {
        System.out.println("Vehicle stopped");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car started");
    }
}

public class DefaultMethodExample {
    public static void main(String[] args) {
        Car car = new Car();
        car.start(); // Output: Car started
        car.stop();  // Output: Vehicle stopped (default implementation)
    }
}
