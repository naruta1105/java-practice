package com.txd.humanresource;

import java.time.LocalDate;

/**
 * Class Manager
 *
 * @version 1.0 18 Oct 2021
 * @author Tran Xuan Dien
 */
public class Manager extends Staff implements ICalculator,TextDecoration {
    /* CONSTANT */
    private static final double BASE_SALARY = 5000000.0;
    private static final double[] RESPONSIBLE_SALARY = {8000000, 6000000, 5000000};
    private static final String[] TITLES = {"Business Leader", "Project Leader", "Technical Leader"};
    private static final String ID_MANAGER = "MN"; // 0000MN -> 9999MN;

    private int title;

    /* Contructors */
    public Manager(String name, int age, LocalDate dayBegin, Department department, int title) {
        this(name, age, 1.0, dayBegin, department, 0);
        this.title = title;
    }

    public Manager(Manager manager) {
        this(manager.name, manager.age, manager.coefSalary, manager.dayBegin, manager.department, manager.dayOff);
        this.title = manager.title;
    }

    public Manager(String name, int age, double coefSalary,
                   LocalDate dayBegin, Department department, int dayOff) {
        super(ID_MANAGER, name, age, coefSalary, dayBegin, department, dayOff);
    }

    public Manager(String name, int age, double coefSalary,
                   LocalDate dayBegin, Department department, int dayOff, int title) {
        this(name, age, coefSalary, dayBegin, department, dayOff);
        this.title = title;
    }

    /* Public Method */
    // Calculate Total Salary
    @Override
    public double calculateSalary() {
        return BASE_SALARY*coefSalary + RESPONSIBLE_SALARY[title] ;
    }

    // Display Information of 1 Manager
    @Override
    public void displayInformation() {
        // Convert to String
        String name = this.name;
        String age = String.format("%3d",this.age); // 0-> 100
        String coefSalary = String.format("%3.1f",this.coefSalary); //0.0 -> 99.9 (3 digit in total, 1 after decimal)
        String dayBegin = String.format("%10s",this.DATE_FORMAT.format(this.dayBegin));
        String department = this.department.getName();
        String dayOff = String.format("%2d",this.dayOff); // 0-> 99

        // Center Text
        String id = TextDecoration.centerText(this.id,TextDecoration.COLUMN_WIDTH[0]);
        age = TextDecoration.centerText(age,TextDecoration.COLUMN_WIDTH[2]);
        coefSalary = TextDecoration.centerText(coefSalary,TextDecoration.COLUMN_WIDTH[3]);
        dayBegin = TextDecoration.centerText(dayBegin,TextDecoration.COLUMN_WIDTH[4]);
        dayOff = TextDecoration.centerText(dayOff,TextDecoration.COLUMN_WIDTH[6]);
        String title = TextDecoration.centerText(TITLES[this.title],TextDecoration.COLUMN_WIDTH[7]);

        System.out.printf(TextDecoration.PRINT_FORMAT, id, name, age, coefSalary, dayBegin,
                department, dayOff, title);
    }

    // Display Salary Information 1 Manager
    @Override
    public void displayTotalSalary() {
        // Convert to String
        String name = this.name;
        String coefSalary = String.format("%3.1f",this.coefSalary); //0.0 -> 99.9 (3 digit in total, 1 after decimal)
        String salary = String.format("%,.1f",this.calculateSalary());

        // Center Text
        String id = TextDecoration.centerText(this.id,TextDecoration.COLUMN_WIDTH_SALARY[0]);
        coefSalary = TextDecoration.centerText(coefSalary,TextDecoration.COLUMN_WIDTH_SALARY[2]);
        String title = TextDecoration.centerText(TITLES[this.title],TextDecoration.COLUMN_WIDTH_SALARY[3]);
        salary = TextDecoration.centerText(salary,TextDecoration.COLUMN_WIDTH_SALARY[4]);

        System.out.printf(TextDecoration.PRINT_FORMAT_SALARY, id, name, coefSalary, title, salary);
    }

    /* Getter and Setter */
    public int getTitle() {
        return title;
    }

    public String getTitleString() {
        return TITLES[title-1];
    }

    public void setTitle(int title) {
        this.title = title;
    }
}
