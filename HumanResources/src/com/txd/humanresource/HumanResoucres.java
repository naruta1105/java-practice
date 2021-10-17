package com.txd.humanresource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Main of Program
 *
 * @version 1.0 18 Oct 2021
 * @author Tran Xuan Dien
 */
public class HumanResoucres implements TextDecoration, RandomStaff {
    private static final String BORDER_TOP = "********************************************************************";
    private static final int CONTENT_WIDTH = BORDER_TOP.length()-4;
    private static final int TABLE_WIDTH = 126;
    private static final String BORDER_CONTENT = "* %-"+CONTENT_WIDTH+"s *";


    private static final String[] DEPARTMENTS = {"Kinh Doanh","Kỹ Thuật","Kế Toán","Hành Chính","Marketing"};
    private static final String[] ID_DEPARTMENTS = {"KDh","KTt","KTn","HCh","MKg"};

    private static final String[] OPTIONS = {"Hiển thị danh sách nhân viên hiện có trong công ty.",
                                                "Hiển thị các bộ phận trong công ty.",
                                                "Hiển thị các nhân viên theo từng bộ phận.",
                                                "Thêm nhân viên mới vào công ty.",
                                                "Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên.",
                                                "Hiển thị bảng lương của nhân viên toàn công ty.",
                                                "Hiển thị bảng lương của nhân viên theo thứ tự tăng hoặc giảm." };

    private static final Scanner console = new Scanner(System.in);

    private static ArrayList<Department> listDepartment = new ArrayList<>();
    private static ArrayList<Staff> listStaff = new ArrayList<>();

    public static void main(String[] args) {
        String answer;
        boolean isContinued;

        // Inintial Department
        createDepartment();

        // Inintial 30 staff
        for (int ii=0; ii<30; ii++) {
            int random = (int)Math.floor(Math.random()*30);
            if (random<=20) {
                createEmployee();
            } else {
                createManager();
            }
        }

        // Flow Control
        do {
            isContinued = false;
            int option = getOption();
            executeOption(option);
            System.out.print("Bạn có muốn tiếp tục?(Yes/No): ");
            answer = console.nextLine().toLowerCase();
            if (answer.equals("yes") || answer.equals("y") || answer.equals("ye")) {
                isContinued = true;
                System.out.println();
            }
        } while (isContinued);
    }

    // Choose Function want to use
    private static int getOption() {
        // Draw a border
        printHeader("MENU");

        for (int ii=0; ii<7; ii++) {
            printOption((ii+1)+". "+OPTIONS[ii]);
        }
        System.out.println(BORDER_TOP);

        return getValidInputInt("Bạn chọn số tương ứng với chức năng muốn xem (1-7): ",1,7);
    }

    // Run this Options
    private static void executeOption(int option) {
        System.out.println();
        printHeader(OPTIONS[option-1]);
        switch (option) {
            case 1 :
                printListOfCurrentEmployee();
                break;
            case 2 :
                printListOfDepartment();
                break;
            case 3 :
                printEmployeeOfDepartment();
                break;
            case 4 :
                addNewStaff();
                break;
            case 5 :
                findEmployee();
                break;
            case 6 :
                printEmployeeSalary();
                break;
            case 7 :
                String content = "Sắp xếp theo thứ tự của lương (0: tăng dần, 1: giảm dần): ";
                int order = getValidInputInt(content,0,1);
                printEmployeeSalary(order);
                break;
            default:
                break;
        }
    }

    // Hiển thị danh sách nhân viên hiện có trong công ty
    private static void printListOfCurrentEmployee() {
        printTable(listStaff);
    }

    // Hiển thị các bộ phận trong công ty
    private static void printListOfDepartment() {
        for (Department dep : listDepartment) {
            System.out.println(dep.toString());
        }
    }

    // Hiển thị các nhân viên theo từng bộ phận
    private static void printEmployeeOfDepartment() {
        ArrayList<Staff> list = new ArrayList<>();

        for (int ii=0; ii < listDepartment.size(); ii++) {
            list.addAll(printEmployeeOfDepartment(ii));
        }

        printTable(list);
    }

    private static ArrayList<Staff> printEmployeeOfDepartment(int id) {
        ArrayList<Staff> list = new ArrayList<>();

        for (Staff staff : listStaff) {
            if (staff.getDepartment().getId().equals(listDepartment.get(id).getId())) {
                list.add(staff);
            }
        }

        return list;
    }

    // Thêm nhân viên mới vào công ty
    private static void addNewStaff() {
        /*
         * Bao gồm 2 loại:
         * - Thêm nhân viên thông thường
         * - Thêm nhân viên là cấp quản lý (có thêm chức vụ)
         */
        printOption("Bao gồm 2 loại: ");
        printOption("1. Thêm nhân viên thông thường");
        printOption("2. Thêm nhân viên là cấp quản lý (có thêm chức vụ)");
        System.out.println(BORDER_TOP);

        int addChoose = getValidInputInt("Bạn chọn loại nào?(1-2): ", 1, 2);

        if (addChoose == 1) {
            addNewEmployee();
        } else if (addChoose == 2) {
            addNewManager();
        }
    }

    // Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên
    private static void findEmployee() {
        /*
         * - Tìm theo mã nhân viên
         * - Tìm theo tên nhân viên
         */
        printOption("1. Tìm theo mã nhân viên");
        printOption("2. Tìm theo tên nhân viên");
        System.out.println(BORDER_TOP);

        int addChoose = getValidInputInt("Bạn chọn loại nào?(1-2): ", 1, 2);
        ArrayList<Staff> list = new ArrayList<>();

        if (addChoose == 1) {
            list = findStaffByID();
        } else if (addChoose == 2) {
            list = findStaffByName();
        }

        if (list.size()>0) {
            printTable(list);
        } else {
            printHeader("KHÔNG TÌM THẤY KẾT QUẢ");
        }
    }

    // Hiển thị bảng lương của nhân viên toàn công ty
    private static void printEmployeeSalary() {
        printSalaryTable(listStaff);
    }

    // Hiển thị bảng lương của nhân viên toàn công ty theo thứ tự
    private static void printEmployeeSalary(int order) {
        /*
         * Option: 1 cho tăng dần, 0 cho giảm dần
         */
        ArrayList<Staff> tempList = new ArrayList<>(listStaff);

        Collections.sort(tempList, Comparator.comparing(Staff::calculateSalary));
        if (order == 1) {
            Collections.reverse(tempList);
        }

        printSalaryTable(tempList);
    }

    /* Initialize */
    // Tạo list các Deparment
    private static void createDepartment() {
        for (int ii=0; ii < DEPARTMENTS.length; ii++) {
            listDepartment.add(new Department(ID_DEPARTMENTS[ii], DEPARTMENTS[ii]));
        }
    }

    // Tạo các Manager ngẫu nhiên để test
    private static void createManager() {
        Manager manager = RandomStaff.randomManager(createdRandomDepartment());
        listStaff.add(manager);
    }

    // Tạo các Employee ngẫu nhiên để test
    private static void createEmployee() {
        Employee employee = RandomStaff.randomEmployee(createdRandomDepartment());
        listStaff.add(employee);
    }

    // Tajo Department random cho random lisst Stafff for test
    private static Department createdRandomDepartment() {
        int min = 0;
        int max = listDepartment.size()-1;
        int randomNumber = (int)Math.floor(Math.random()*(max-min+1)+min);
        Department dept = listDepartment.get(randomNumber);

        // Tăng nhân sự lên 1
        dept.setNumberEmployee(dept.getNumberEmployee()+1);

        return dept;
    }

    /* Add New Method */
    private static void addNewEmployee() {
        printHeader("1. Thêm nhân viên thông thường");

        String name = getInputString("Tên nhân viên: ");
        int age = getValidInputInt("Tuổi nhân viên: ",18,100);
        LocalDate dayBegin = getValidInputDate("Ngày bắt đầu làm(dd/mm/yyyy): ");
        Department department = getInputDeparment();

        Employee newEmployee = new Employee(name, age, dayBegin, department);

        listStaff.add(newEmployee);
    }

    private static void addNewManager() {
        printHeader("2. Thêm nhân viên là cấp quản lý (có thêm chức vụ)");

        String name = getInputString("Tên quản lý: ");
        int age = getValidInputInt("Tuổi quản lý: ",18,100);
        LocalDate dayBegin = getValidInputDate("Ngày bắt đầu làm(dd/mm/yyyy): ");
        Department department = getInputDeparment();

        System.out.println("Chức vụ làm việc: ");
        System.out.println("1. Business Leader");
        System.out.println("2. Project Leader");
        System.out.println("3. Technical Leader");
        int title = getValidInputInt("Bạn chọn số mấy(1-3)? ",1,3)-1 ;

        Manager newManager = new Manager(name, age, dayBegin, department, title);

        listStaff.add(newManager);
    }

    /* Find Method */
    // Find with ID
    private static ArrayList<Staff> findStaffByID() {
        ArrayList<Staff> list = new ArrayList<>();

        printHeader("1. Tìm theo Mã Nhân viên");

        String id = getInputString("Nhập mã nhân viên cần tìm: ").trim().toLowerCase();

        for (Staff staff :  listStaff) {
            String staffID = staff.getID().toLowerCase();
            if (staffID.contains(id)) {
                list.add(staff);
            }
        }

        return list;
    }

    // Find with Name
    private static ArrayList<Staff> findStaffByName() {
        ArrayList<Staff> list = new ArrayList<>();

        printHeader("2. Tìm theo tên nhân viên");

        String name = getInputString("Nhập tên nhân viên cần tìm: ");
        name = name.trim().toLowerCase().replace("  "," ");
        String[] words = name.split("\\s");

        for (Staff staff :  listStaff) {
            boolean isMatch = true;
            String staffName = staff.getName().toLowerCase();
            // Kiểm tra xem staffName có chứa 1 trong các từ trog input
            for (String word : words) {
                if (!staffName.contains(word)) {
                    isMatch = false;
                    break; // Nếu tìm được thì thoát khỏi vòng lặp.
                }
            }
            // if StaffName have all word from input then add to result
            if (isMatch) {
                list.add(staff);
            }
        }

        return list;
    }

    /* Input Method */
    // Get valid input. No mismatch in type (required Int but input string).
    private static int getValidInputInt(String str, int min, int max) {
        int input = 0;
        boolean isOptionValid;

        do {
            isOptionValid = true;
            try {
                input = Integer.parseInt(getInputString(str));
                if ((min > input) || (input > max)) {   // input must be in range [min,max]
                    isOptionValid = false;
                }
            } catch (NumberFormatException | NullPointerException e) {
                isOptionValid = false;
            }
        } while (!isOptionValid);

        return input;
    }

    // Dùng để cài hệ số lương hoặc OT nhưng hiện tại không dùng.
    private static double getValidInputDouble(String str, double min, double max) {
        double input = 0;
        boolean isOptionValid;

        do {
            isOptionValid = true;
            try {
                input = Double.parseDouble(getInputString(str));
                if ((min > input) || (input > max)) {   // input must be in range [min,max]
                    isOptionValid = false;
                }
            } catch (NumberFormatException | NullPointerException e) {
                isOptionValid = false;
                console.next(); // This prevent infinite loop;
            }
        } while (!isOptionValid);

        return input;
    }

    private static String getInputString(String str) {
        System.out.print(str);            // print content
        String input = console.nextLine();

        return input;
    }

    private static LocalDate getValidInputDate(String str) {
        DateTimeFormatter formatter = Staff.DATE_FORMAT;
        LocalDate currentDate = LocalDate.now(); // Get current Day
        LocalDate parsedDate = null;
        boolean isOptionValid;

        do {
            isOptionValid = true;
            try {
                parsedDate = LocalDate.parse(getInputString(str), formatter);
                if (parsedDate.isAfter(currentDate)) {
                    isOptionValid = false;
                }
            } catch (DateTimeParseException e) {
                isOptionValid = false;
            }
        } while (!isOptionValid);

        return parsedDate;
    }

    private static Department getInputDeparment() {
        int nDep = DEPARTMENTS.length;
        System.out.println("Bộ phận làm việc: ");
        for (int ii=0; ii < nDep; ii++) {
            System.out.println(ii+1+". "+DEPARTMENTS[ii]);
        }
        int choose = getValidInputInt("Bạn chọn bộ phận tương ứng(1-"+nDep+"): ", 1,nDep);

        Department dept = listDepartment.get(choose-1);
        dept.setNumberEmployee(dept.getNumberEmployee()+1); // increase number in this Dept

        return dept;
    }

    /* Print Method */
    // Print header of Big Table
    private static void printHeaderTable() {
        String line = String.format(TextDecoration.PRINT_FORMAT,centerText("ID",6),
                centerText("Họ và tên",25), "Tuổi", "Hệ số lương", "Ngày bắt đầu",
                centerText("Bộ phận",15), "Ngày phép", centerText("OT/Chức vụ",18));
        printLine(TABLE_WIDTH);
        System.out.print(line);
        printLine(TABLE_WIDTH);
    }

    // Print content Big Table
    private static void printTable(ArrayList<Staff> list) {
        printHeaderTable();
        for (Staff staff : list) {
            staff.displayInformation();
        }
        printLine(TABLE_WIDTH);
    }

    // Print Line of Big Table
    private static void printLine(int len) {
        for (int ii=1; ii < len; ii++) {
            System.out.print("-");
        }
        System.out.println();
    }

    // Print header for option
    private static void printHeader(String content) {
        System.out.println(BORDER_TOP);
        System.out.printf(BORDER_CONTENT+"\n",centerText(content,CONTENT_WIDTH));
        System.out.println(BORDER_TOP);
    }

    // Print * [content] *
    private static void printOption(String content) {
        System.out.printf(BORDER_CONTENT+"\n",content);
    }

    // Make Text Center for better visual
    private static String centerText(String text, int maxLength) {
        return TextDecoration.centerText(text, maxLength);
    }

    // Print Salary Table
    private static void printSalaryTable(ArrayList<Staff> list) {
        // Print Header Of Salary Table
        String line = String.format(TextDecoration.PRINT_FORMAT_SALARY,
                centerText("ID",TextDecoration.COLUMN_WIDTH_SALARY[0]),
                centerText("Họ và tên",TextDecoration.COLUMN_WIDTH_SALARY[1]), "Hệ số lương",
                centerText("OT/Chức vụ",TextDecoration.COLUMN_WIDTH_SALARY[3]),
                centerText("Tổng Lương",TextDecoration.COLUMN_WIDTH_SALARY[4]));
        printLine(line.length());
        System.out.print(line);
        printLine(line.length());

        // Print Header Of Salary Table
        for (Staff staff : list) {
            staff.displayTotalSalary();
        }

        printLine(line.length());
    }


}


