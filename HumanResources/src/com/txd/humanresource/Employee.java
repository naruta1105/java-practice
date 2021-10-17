package com.txd.humanresource;

import java.time.LocalDate;

/**
 * Class Employee
 *
 * @version 1.0 18 Oct 2021
 * @author Tran Xuan Dien
 */
public class Employee extends Staff implements ICalculator,TextDecoration {
    /* CONSTANT */
    private static final double BASE_SALARY = 3000000.0;
    private static final double OT_SALARY = 200000.0;
    private static final String ID_EMPLOYEE = "NV"; // 0000NV -> 9999NV;

    // Variable
    private double overtime = 0.0;

    /* Construction */
    public Employee(String name, int age, LocalDate dayBegin, Department department) {
        this(name, age, 1.0, dayBegin, department, 0);
    }

    public Employee(Employee employee) {
        this(employee.name, employee.age, employee.coefSalary, employee.dayBegin,
                employee.department, employee.dayOff);
        this.overtime = employee.overtime;
    }

    public Employee(String name, int age, double coefSalary,
                    LocalDate dayBegin, Department department, int dayOff) {
        super(ID_EMPLOYEE, name, age, coefSalary, dayBegin, department, dayOff);
    }

    public Employee(String name, int age, double coefSalary,
                    LocalDate dayBegin, Department department, int dayOff, double overtime) {
        this(name, age, coefSalary, dayBegin, department, dayOff);
        this.overtime = overtime;
    }

    /* Public Method */
    // Calculate Total Salary
    @Override
    public double calculateSalary() {
        return BASE_SALARY*coefSalary + OT_SALARY*overtime ;
    }

    // Display Information 1 Employee
    @Override
    public void displayInformation() {
        // Convert to String
        String name = this.name;
        String age = String.format("%3d",this.age); // 0-> 100
        String coefSalary = String.format("%3.1f",this.coefSalary); //0.0 -> 99.9 (3 digit in total, 1 after decimal)
        String dayBegin = String.format("%10s",this.DATE_FORMAT.format(this.dayBegin));
        String department = this.department.getName();
        String dayOff = String.format("%2d",this.dayOff); // 0-> 99
        String overtime = String.format("%4.1f",this.overtime); // 0-> 99

        // Center Text
        String id = TextDecoration.centerText(this.id,TextDecoration.COLUMN_WIDTH[0]);
        age = TextDecoration.centerText(age,TextDecoration.COLUMN_WIDTH[2]);
        coefSalary = TextDecoration.centerText(coefSalary,TextDecoration.COLUMN_WIDTH[3]);
        dayBegin = TextDecoration.centerText(dayBegin,TextDecoration.COLUMN_WIDTH[4]);
        dayOff = TextDecoration.centerText(dayOff,TextDecoration.COLUMN_WIDTH[6]);
        overtime = TextDecoration.centerText(overtime,TextDecoration.COLUMN_WIDTH[7]);

        System.out.printf(TextDecoration.PRINT_FORMAT, id, name, age, coefSalary, dayBegin,
                department, dayOff, overtime);
    }

    // Display Salary Information 1 Employee
    @Override
    public void displayTotalSalary() {
        // Convert to String
        String name = this.name;
        String coefSalary = String.format("%3.1f",this.coefSalary); //0.0 -> 99.9 (3 digit in total, 1 after decimal)
        String overtime = String.format("%4.1f",this.overtime); // 0-> 99
        String salary = String.format("%,.1f",this.calculateSalary());

        // Center Text
        String id = TextDecoration.centerText(this.id,TextDecoration.COLUMN_WIDTH_SALARY[0]);
        coefSalary = TextDecoration.centerText(coefSalary,TextDecoration.COLUMN_WIDTH_SALARY[2]);
        overtime = TextDecoration.centerText(overtime,TextDecoration.COLUMN_WIDTH_SALARY[3]);
        salary = TextDecoration.centerText(salary,TextDecoration.COLUMN_WIDTH_SALARY[4]);

        System.out.printf(TextDecoration.PRINT_FORMAT_SALARY, id, name, coefSalary, overtime, salary);
    }

    // Getter Overtime
    public double getOvertime() {
        return overtime;
    }

    // Setter Overtime
    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }
}
