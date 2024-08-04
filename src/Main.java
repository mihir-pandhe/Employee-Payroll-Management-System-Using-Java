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
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nEmployee Payroll Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Filter Employees");
            System.out.println("4. Edit Employee Details");
            System.out.println("5. Delete Employee");
            System.out.println("6. Calculate Payroll");
            System.out.println("7. Generate Payslip");
            System.out.println("8. Manage Leave");
            System.out.println("9. Add Bonus/Deductions");
            System.out.println("10. Generate Reports");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        displayEmployees();
                        break;
                    case 3:
                        filterEmployees();
                        break;
                    case 4:
                        editEmployee();
                        break;
                    case 5:
                        deleteEmployee();
                        break;
                    case 6:
                        calculatePayroll();
                        break;
                    case 7:
                        generatePayslip();
                        break;
                    case 8:
                        manageLeave();
                        break;
                    case 9:
                        addBonusDeductions();
                        break;
                    case 10:
                        generateReports();
                        break;
                    case 11:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void addEmployee() {
        try {
            System.out.print("Enter Employee ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Employee Designation: ");
            String designation = scanner.nextLine();
            System.out.print("Enter Employee Salary: ");
            double salary = Double.parseDouble(scanner.nextLine());

            if (id <= 0 || salary <= 0) {
                System.out.println("Invalid ID or Salary. Please enter positive values.");
                return;
            }

            Employee employee = new Employee(id, name, designation, salary);
            employees.add(employee);
            System.out.println("Employee added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter valid numbers.");
        }
    }

    private static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            System.out.println("Employee Details:");
            employees.forEach(System.out::println);
        }
    }

    private static void filterEmployees() {
        System.out.println("Filter Employees by:");
        System.out.println("1. Designation");
        System.out.println("2. Employee ID");
        System.out.println("3. Salary Range");
        System.out.println("4. Name");
        System.out.print("Enter your choice: ");

        try {
            int filterChoice = Integer.parseInt(scanner.nextLine());

            switch (filterChoice) {
                case 1:
                    filterByDesignation();
                    break;
                case 2:
                    filterById();
                    break;
                case 3:
                    filterBySalaryRange();
                    break;
                case 4:
                    filterByName();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter a number.");
        }
    }

    private static void filterByDesignation() {
        System.out.print("Enter designation to filter: ");
        String designation = scanner.nextLine();

        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> e.getDesignation().equalsIgnoreCase(designation))
                .collect(Collectors.toList());

        if (filteredEmployees.isEmpty()) {
            System.out.println("No employees found with the given designation.");
        } else {
            System.out.println("Filtered Employee Details:");
            filteredEmployees.forEach(System.out::println);
        }
    }

    private static void filterById() {
        System.out.print("Enter Employee ID to filter: ");
        int id = Integer.parseInt(scanner.nextLine());

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

    private static void filterBySalaryRange() {
        try {
            System.out.print("Enter minimum salary: ");
            double minSalary = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter maximum salary: ");
            double maxSalary = Double.parseDouble(scanner.nextLine());

            List<Employee> filteredEmployees = employees.stream()
                    .filter(e -> e.getSalary() >= minSalary && e.getSalary() <= maxSalary)
                    .collect(Collectors.toList());

            if (filteredEmployees.isEmpty()) {
                System.out.println("No employees found within the given salary range.");
            } else {
                System.out.println("Filtered Employee Details:");
                filteredEmployees.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter valid numbers.");
        }
    }

    private static void filterByName() {
        System.out.print("Enter employee name to filter: ");
        String name = scanner.nextLine();

        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        if (filteredEmployees.isEmpty()) {
            System.out.println("No employees found with the given name.");
        } else {
            System.out.println("Filtered Employee Details:");
            filteredEmployees.forEach(System.out::println);
        }
    }

    private static void editEmployee() {
        try {
            System.out.print("Enter the ID of the employee to edit: ");
            int id = Integer.parseInt(scanner.nextLine());

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
                if (salary <= 0) {
                    System.out.println("Invalid salary. Must be positive.");
                    return;
                }
                employee.setSalary(salary);
            }

            System.out.println("Employee details updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter valid numbers.");
        }
    }

    private static void deleteEmployee() {
        try {
            System.out.print("Enter the ID of the employee to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            boolean removed = employees.removeIf(e -> e.getId() == id);

            if (removed) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("No employee found with the given ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter a valid number.");
        }
    }

    private static void calculatePayroll() {
        for (Employee employee : employees) {
            try {
                System.out
                        .print("Enter deduction percentage for " + employee.getName() + " (e.g., for 10% enter 10): ");
                double deductionPercentage = Double.parseDouble(scanner.nextLine());

                if (deductionPercentage < 0) {
                    System.out.println("Deduction percentage cannot be negative.");
                    continue;
                }

                double deduction = employee.getSalary() * (deductionPercentage / 100);
                double netSalary = employee.getSalary() - deduction;

                System.out.printf("Employee ID: %d | Name: %s | Net Salary: $%.2f\n",
                        employee.getId(), employee.getName(), netSalary);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input format. Please enter a valid number.");
            }
        }
    }

    private static void generatePayslip() {
        try {
            System.out.print("Enter Employee ID to generate payslip: ");
            int id = Integer.parseInt(scanner.nextLine());

            Employee employee = employees.stream()
                    .filter(e -> e.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (employee == null) {
                System.out.println("No employee found with the given ID.");
                return;
            }

            System.out.print("Enter deduction percentage (e.g., for 10% enter 10): ");
            double deductionPercentage = Double.parseDouble(scanner.nextLine());

            if (deductionPercentage < 0) {
                System.out.println("Deduction percentage cannot be negative.");
                return;
            }

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
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter a valid number.");
        }
    }

    private static void manageLeave() {
        try {
            System.out.print("Enter Employee ID to manage leave: ");
            int id = Integer.parseInt(scanner.nextLine());

            Employee employee = employees.stream()
                    .filter(e -> e.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (employee == null) {
                System.out.println("No employee found with the given ID.");
                return;
            }

            System.out.print("Enter number of leave days taken: ");
            int leaveDays = Integer.parseInt(scanner.nextLine());

            if (leaveDays < 0) {
                System.out.println("Leave days cannot be negative.");
                return;
            }

            employee.setLeaveDays(leaveDays);

            double leaveDeduction = leaveDays * (employee.getSalary() / 30);
            employee.setOtherDeductions(employee.getOtherDeductions() + leaveDeduction);

            System.out.println("Leave updated and deduction applied.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter valid numbers.");
        }
    }

    private static void addBonusDeductions() {
        try {
            System.out.print("Enter Employee ID to add bonus/deductions: ");
            int id = Integer.parseInt(scanner.nextLine());

            Employee employee = employees.stream()
                    .filter(e -> e.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (employee == null) {
                System.out.println("No employee found with the given ID.");
                return;
            }

            System.out.print("Enter bonus amount: ");
            double bonus = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter other deductions amount: ");
            double otherDeductions = Double.parseDouble(scanner.nextLine());

            if (bonus < 0 || otherDeductions < 0) {
                System.out.println("Bonus and deductions cannot be negative.");
                return;
            }

            employee.setBonus(bonus);
            employee.setOtherDeductions(otherDeductions);

            System.out.println("Bonus and deductions updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter valid numbers.");
        }
    }

    private static void generateReports() {
        System.out.println("Payroll Summary Report:");
        for (Employee employee : employees) {
            double netSalary = employee.getSalary() + employee.getBonus() - employee.getOtherDeductions();
            System.out.printf("Employee ID: %d | Name: %s | Net Salary: $%.2f\n",
                    employee.getId(), employee.getName(), netSalary);
        }

        System.out.println("\nEmployee Attendance Report:");
        for (Employee employee : employees) {
            System.out.printf("Employee ID: %d | Name: %s | Leave Days: %d\n",
                    employee.getId(), employee.getName(), employee.getLeaveDays());
        }
    }
}
