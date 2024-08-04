package src;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
            System.out.println("4. Edit Employee Details");
            System.out.println("5. Delete Employee");
            System.out.println("6. Calculate Payroll");
            System.out.println("7. Generate Payslip");
            System.out.println("8. Exit");
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
                    editEmployee(scanner);
                    break;
                case 5:
                    deleteEmployee(scanner);
                    break;
                case 6:
                    calculatePayroll(scanner);
                    break;
                case 7:
                    generatePayslip(scanner);
                    break;
                case 8:
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
        scanner.nextLine(); // Consume newline
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
        scanner.nextLine(); // Consume newline

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

    private static void editEmployee(Scanner scanner) {
        System.out.print("Enter the ID of the employee to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (employee == null) {
            System.out.println("No employee found with the given ID.");
            return;
        }

        System.out.println("Editing employee with ID " + id);
        System.out.print("Enter new name (leave empty to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            employee.setName(name);
        }

        System.out.print("Enter new designation (leave empty to keep current): ");
        String designation = scanner.nextLine();
        if (!designation.isEmpty()) {
            employee.setDesignation(designation);
        }

        System.out.print("Enter new salary (leave empty to keep current): ");
        String salaryInput = scanner.nextLine();
        if (!salaryInput.isEmpty()) {
            double salary = Double.parseDouble(salaryInput);
            employee.setSalary(salary);
        }

        System.out.println("Employee details updated successfully.");
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter the ID of the employee to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (employee == null) {
            System.out.println("No employee found with the given ID.");
            return;
        }

        System.out.print("Are you sure you want to delete the employee with ID " + id + "? (y/n): ");
        char confirm = scanner.nextLine().charAt(0);

        if (confirm == 'y' || confirm == 'Y') {
            employees.remove(employee);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Deletion cancelled.");
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

    private static void calculatePayroll(Scanner scanner) {
        for (Employee employee : employees) {
            System.out.print("Enter deduction percentage for " + employee.getName() + " (e.g., for 10% enter 10): ");
            double deductionPercentage = scanner.nextDouble();

            double deduction = employee.getSalary() * (deductionPercentage / 100);
            double netSalary = employee.getSalary() - deduction;

            System.out.printf("Employee ID: %d | Name: %s | Net Salary: $%.2f\n",
                    employee.getId(), employee.getName(), netSalary);
        }
    }

    private static void generatePayslip(Scanner scanner) {
        System.out.print("Enter Employee ID to generate payslip: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (employee == null) {
            System.out.println("No employee found with the given ID.");
            return;
        }

        System.out.print("Enter deduction percentage (e.g., for 10% enter 10): ");
        double deductionPercentage = scanner.nextDouble();

        double deduction = employee.getSalary() * (deductionPercentage / 100);
        double netSalary = employee.getSalary() - deduction;

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Payslip_" + employee.getId() + ".pdf"));
            document.open();
            document.add(new Paragraph("Payslip"));
            document.add(new Paragraph("Employee ID: " + employee.getId()));
            document.add(new Paragraph("Name: " + employee.getName()));
            document.add(new Paragraph("Designation: " + employee.getDesignation()));
            document.add(new Paragraph("Gross Salary: $" + employee.getSalary()));
            document.add(new Paragraph("Deductions: $" + deduction));
            document.add(new Paragraph("Net Salary: $" + netSalary));
            document.close();
            System.out.println("Payslip generated successfully.");
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("Error generating payslip: " + e.getMessage());
        }
    }
}
