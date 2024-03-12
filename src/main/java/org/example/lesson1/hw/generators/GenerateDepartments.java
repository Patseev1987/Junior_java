package org.example.lesson1.hw.generators;

import org.example.lesson1.Streams;

import java.util.ArrayList;
import java.util.List;

public class GenerateDepartments {

    private static List<Streams.Department> departments;

    static {
        departments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            departments.add(new Streams.Department("Department #" + i));
        }
    }


    public static List<Streams.Department> getDepartments() {
        return departments;
    }
}
