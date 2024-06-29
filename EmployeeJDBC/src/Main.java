import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employeesdb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "1234";

    // JDBC variables for opening and managing connection
    private static Connection connection;

    // Constructor
    public Main() {
        try {
            // Connect to MySQL database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to create an Employee
    public void createEmployee(Employee employee) {
        String sql = "INSERT INTO employees (id, name, department) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getDepartment());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new employee was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to read all Employees
    public List<Employee> readEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");

                Employee employee = new Employee(id, name, department);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Method to read an Employee by ID
    public Employee readEmployeeById(int id) {
        Employee employee = null;
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String department = resultSet.getString("department");

                    employee = new Employee(id, name, department);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    // Method to update an Employee
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getDepartment());
            preparedStatement.setInt(3, employee.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee with ID " + employee.getId() + " was updated successfully!");
            } else {
                System.out.println("No employee found with ID " + employee.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete an Employee
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee with ID " + id + " was deleted successfully!");
            } else {
                System.out.println("No employee found with ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close JDBC connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to handle user input for CRUD operations
    // Method to handle user input for CRUD operations
    public void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 6) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Create Employee");
            System.out.println("2. Read all Employees");
            System.out.println("3. Read Employee by ID");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            // Validate input for choice
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.next(); // Consume invalid input
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Enter employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after nextInt()
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter employee department: ");
                    String department = scanner.nextLine();

                    Employee newEmployee = new Employee(id, name, department);
                    createEmployee(newEmployee);
                    break;
                case 2:
                    List<Employee> allEmployees = readEmployees();
                    System.out.println("\nAll employees:");
                    allEmployees.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Enter employee ID to search: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after nextInt()

                    Employee foundEmployee = readEmployeeById(searchId);
                    if (foundEmployee != null) {
                        System.out.println("\nEmployee found:");
                        System.out.println(foundEmployee);
                    } else {
                        System.out.println("\nEmployee not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter employee ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after nextInt()
                    System.out.print("Enter updated name: ");
                    String updatedName = scanner.nextLine();
                    System.out.print("Enter updated department: ");
                    String updatedDepartment = scanner.nextLine();

                    Employee employeeToUpdate = new Employee(updateId, updatedName, updatedDepartment);
                    updateEmployee(employeeToUpdate);
                    break;
                case 5:
                    System.out.print("Enter employee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after nextInt()

                    deleteEmployee(deleteId);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Main employeeJDBC = new Main();

        // Start handling user input for CRUD operations
        employeeJDBC.handleUserInput();

        // Close JDBC connection when done
        employeeJDBC.closeConnection();
    }
}
