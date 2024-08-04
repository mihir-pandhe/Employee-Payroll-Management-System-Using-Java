package src;
public class Employee {
    private int id;
    private String name;
    private String designation;
    private double salary;
    private int leaveDays;
    private double bonus;
    private double otherDeductions;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
        this.leaveDays = 0;
        this.bonus = 0;
        this.otherDeductions = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getOtherDeductions() {
        return otherDeductions;
    }

    public void setOtherDeductions(double otherDeductions) {
        this.otherDeductions = otherDeductions;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d, Name: %s, Designation: %s, Salary: $%.2f, Leave Days: %d, Bonus: $%.2f, Other Deductions: $%.2f",
                id, name, designation, salary, leaveDays, bonus, otherDeductions);
    }
}
