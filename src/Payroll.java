package src;

public class Payroll {
    private Employee employee;
    private double deductions;
    private double netPay;

    public Payroll(Employee employee) {
        this.employee = employee;
        this.deductions = 0.0;
        this.netPay = employee.getSalary();
    }

    public void calculateNetPay() {
        this.netPay = employee.getSalary() - deductions;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    @Override
    public String toString() {
        return "Payroll [employee=" + employee + ", deductions=" + deductions + ", netPay=" + netPay + "]";
    }
}
