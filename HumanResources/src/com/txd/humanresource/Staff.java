package com.txd.humanresource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class Staff that parent of Employee and Manager
 *
 * @version 1.0 18 Oct 2021
 * @author Tran Xuan Dien
 */
public abstract class Staff implements ICalculator {
    protected static final String ID_FORMAT = "%04d"; // 0000 -> 9999; pad with 0, Max length = 4.
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");

    protected static int idNumber = 1;

    protected String id;
    protected String name;
    protected int age;
    protected double coefSalary;
    protected LocalDate dayBegin;
    protected Department department;
    protected int dayOff;

    /* Constructor */
    protected Staff() {
        this.id = "";
        this.name = "";
        this.age = 0;
        this.coefSalary = 0;
        this.dayBegin = LocalDate.now();
        this.department = new Department();
        this.dayOff = 0;
        idNumber++;
    }
    protected Staff(String id_job, String name, int age, double coefSalary,
                    LocalDate dayBegin, Department department, int dayOff) {
        this.id = String.format(ID_FORMAT+id_job, idNumber);
        this.name = name;
        this.age = age;
        this.coefSalary = coefSalary;
        this.dayBegin = dayBegin;
        this.department = department;
        this.dayOff = dayOff;
        idNumber++;
    }

    protected abstract void displayInformation();

    protected abstract void displayTotalSalary();


    /* 
     * Accessors 
     */
    protected String getID() {
        return this.id;
    }

    protected String getName() {
        return this.name;
    }

    protected int getAge() {
        return this.age;
    }

    protected double getCoefSalary() {
        return this.coefSalary;
    }

    protected LocalDate getDayBegin() {
        return this.dayBegin;
    }

    protected Department getDepartment() {
        return this.department;
    }

    protected int getDayOff() {
        return this.dayOff;
    }

    /* 
     *  Mutators 
     */
    protected void setID(String id) {
        this.id = id;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setAge(int age) {
        this.age = age;
    }

    protected void setCoefSalary(double coefSalary) {
        this.coefSalary = coefSalary;
    }

    protected void setDayBegin(LocalDate dayBegin) {
        this.dayBegin = dayBegin;
    }

    protected void setDepartment(Department department) {
        this.department = department;
    }

    protected void setDayOff(int dayOff) {
        this.dayOff = dayOff;
    }

}
