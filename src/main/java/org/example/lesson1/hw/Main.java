package org.example.lesson1.hw;


import org.example.lesson1.Streams;
import org.example.lesson1.hw.generators.GeneratePeople;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        var people = GeneratePeople.getPeople();

        Homework homework = new Homework();

        homework.printNamesOrdered(people);

        System.out.println(homework.findFirstPersons(people));

        System.out.println(homework.printDepartmentOldestPerson(people));

        System.out.println(homework.findTopDepartment(people));
    }
}
