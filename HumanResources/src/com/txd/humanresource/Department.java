package com.txd.humanresource;

/**
 * Deparment Class
 *
 * @version 1.0 18 Oct 2021
 * @author Tran Xuan Dien
 */
public class Department {
    private String id;
    private String name;
    private int numberEmployee = 0;

    /* Contructor */
    public Department(String id, String name, int numberEmployee) {
        this.id = id;
        this.name = name;
        this.numberEmployee = numberEmployee;
    }

    public Department(String id, String name) {
        this(id, name, 0);
    }

    public Department() {
        this("", "", 0);
    }

    // Use for Print
    public String toString() {
        return String.format("|| ID = %s || Tên bộ phận = %10s || Nhân viên = %4d người ||",
                this.id, this.name, this.numberEmployee);
    }

    /* Getter */
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberEmployee() {
        return numberEmployee;
    }

    /* Setter */
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberEmployee(int numberEmployee) {
        this.numberEmployee = numberEmployee;
    }
}
