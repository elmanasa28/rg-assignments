import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EmployeeCRUD {
    private List<Employee> employees;

    public EmployeeCRUD() {
        this.employees = new ArrayList<>();
    }

    public void createEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee readEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null; // Employee not found
    }

    public void updateEmployee(int id, String name, String department) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setName(name);
                employee.setDepartment(department);
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found. Update failed.");
    }

    public void deleteEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
                System.out.println("Employee with ID " + id + " deleted successfully.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found. ");
        
    }

    
    public List<Employee> getEmployees() {
        return employees;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeCRUD employeeCRUD = new EmployeeCRUD();

        int choice = 0;
        while (choice != 5) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Create Employee");
            System.out.println("2. Read Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter employee department: ");
                    String department = scanner.nextLine();
                    employeeCRUD.createEmployee(new Employee(id, name, department));
                    System.out.println("Employee created successfully.");
                    break;
                case 2:
                    System.out.print("Enter employee ID to read: ");
                    int readId = scanner.nextInt();
                    Employee foundEmployee = employeeCRUD.readEmployee(readId);
                    if (foundEmployee != null) {
                        System.out.println("Employee found:");
                        System.out.println(foundEmployee);
                    } else {
                        System.out.println("Employee with ID " + readId + " not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter employee ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new department: ");
                    String newDepartment = scanner.nextLine();
                    employeeCRUD.updateEmployee(updateId, newName, newDepartment);
                    break;
                case 4:
                    System.out.print("Enter employee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    employeeCRUD.deleteEmployee(deleteId);
                    
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
        scanner.close();
    }
}
