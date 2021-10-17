package com.txd.humanresource;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ThreadLocalRandom;

/**
 * RandomStaff for making random data of employee and Manager
 * Used for testing
 *
 * @version 1.0 18 Oct 2021
 * @author Tran Xuan Dien
 */
public interface RandomStaff {
     String[] NAME = {"Hoàng Đức Anh", "Hoàng Văn Bảo", "Lưu Trang Anh", "Lưu Thanh Tuấn",
            "Phạm Hoàng Anh", "Hoàng Thị Thanh Mai", "Phạm Thị Hiền Anh", "Nguyễn Quỳnh Hoa", "Phạm Khắc Việt Anh",
            "Đỗ Hoàng Gia Bảo", "Phạm Thị Thu Hương","Tăng Phương Chi", "Nguyễn Thái Dương", "Nguyễn Thị Trà My",
            "Mạc Trung Đức", "Trần Trọng Dũng", "Mạc Trung Đức", "Bùi Thị Thu Hương", "Phạm Xuân Hòa", "Nguyễn Văn Đạm",
            "Nguyễn Hữu Hòa", "Nguyễn Mạnh Hùng", "Nguyễn Vân Long","Nguyễn Hữu Hiệp Hoàng","Nguyễn Thị Dương"};

    static Employee randomEmployee(Department dept) {
        int min = 0;
        int max = NAME.length - 1;
        int random = (int)Math.floor(Math.random()*(max-min+1)+min);
        String name = NAME[random];

        min = 1;
        max = 100;
        int age = (int)Math.floor(Math.random()*(max-min+1)+min);

        min = 4;
        max = 10;
        random = (int)Math.floor(Math.random()*(max-min+1)+min);
        double coefSalary =(int)(10.0/random*10)/10.0;

        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.now();
        LocalDate dayBegin = between(start, end);

        min = 0;
        max = 5;
        int dayOff = (int)Math.floor(Math.random()*(max-min+1)+min);

        min = 1;
        max = 50;
        random = (int)Math.floor(Math.random()*(max-min+1)+min);
        int random2 = (int)Math.floor(Math.random()*(2));
        double overtime = (70.0/random)*random2;


        Employee newEmployee = new Employee(name, age, coefSalary, dayBegin, dept,dayOff,overtime);

        return newEmployee;
    }

    static Manager randomManager(Department dept) {
        int min = 0;
        int max = NAME.length - 1;
        int random = (int)Math.floor(Math.random()*(max-min+1)+min);
        String name = NAME[random];

        min = 1;
        max = 100;
        int age = (int)Math.floor(Math.random()*(max-min+1)+min);

        min = 4;
        max = 10;
        random = (int)Math.floor(Math.random()*(max-min+1)+min);
        double coefSalary =(int)(10.0/random*10)/10.0;

        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.now();
        LocalDate dayBegin = between(start, end);

        min = 0;
        max = 5;
        int dayOff = (int)Math.floor(Math.random()*(max-min+1)+min);


        min = 0;
        max = 2;
        int title = (int)Math.floor(Math.random()*(max-min+1)+min);

        Manager newManager = new Manager(name, age, coefSalary, dayBegin, dept, dayOff, title);

        return newManager;
    }

    static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }
}