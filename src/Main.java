package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Employee Payroll Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Filter Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    filterEmployees(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(id, name, designation, salary);
        employees.add(employee);
        System.out.println("Employee added successfully.");
    }

    private static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            System.out.println("Employee Details:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    private static void filterEmployees(Scanner scanner) {
        System.out.println("Filter Employees by:");
        System.out.println("1. Designation");
        System.out.println("2. Employee ID");
        System.out.println("3. Salary Range");
        System.out.println("4. Name");
        System.out.print("Enter your choice: ");
        int filterChoice = scanner.nextInt();
        scanner.nextLine();

        switch (filterChoice) {
            case 1:
                filterByDesignation(scanner);
                break;
            case 2:
                filterById(scanner);
                break;
            case 3:
                filterBySalaryRange(scanner);
                break;
            case 4:
                filterByName(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void filterByDesignation(Scanner scanner) {
        System.out.print("Enter designation to filter: ");
        String designation = scanner.nextLine();

        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> e.getDesignation().equalsIgnoreCase(designation))
                .collect(Collectors.toList());

        if (filteredEmployees.isEmpty()) {
            System.out.println("No employees found with the given designation.");
        } else {
            System.out.println("Filtered Employee Details:");
            for (Employee employee : filteredEmployees) {
                System.out.println(employee);
            }
        }
    }

    private static void filterById(Scanner scanner) {
        System.out.print("Enter Employee ID to filter: ");
        int id = scanner.nextInt();

        Employee employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (employee == null) {
            System.out.println("No employee found with the given ID.");
        } else {
            System.out.println("Employee Details:");
            System.out.println(employee);
        }
    }

    private static void filterBySalaryRange(Scanner scanner) {
        System.out.print("Enter minimum salary: ");
        double minSalary = scanner.nextDouble();
        System.out.print("Enter maximum salary: ");
        double maxSalary = scanner.nextDouble();

        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> e.getSalary() >= minSalary && e.getSalary() <= maxSalary)
                .collect(Collectors.toList());

        if (filteredEmployees.isEmpty()) {
            System.out.println("No employees found within the given salary range.");
        } else {
            System.out.println("Filtered Employee Details:");
            for (Employee employee : filteredEmployees) {
                System.out.println(employee);
            }
        }
    }

    private static void filterByName(Scanner scanner) {
        System.out.print("Enter name to filter: ");
        String name = scanner.nextLine();

        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        if (filteredEmployees.isEmpty()) {
            System.out.println("No employees found with the given name.");
        } else {
            System.out.println("Filtered Employee Details:");
            for (Employee employee : filteredEmployees) {
                System.out.println(employee);
            }
        }
    }
}
