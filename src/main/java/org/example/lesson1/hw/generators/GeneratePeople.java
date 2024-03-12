package org.example.lesson1.hw.generators;

import org.example.lesson1.Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneratePeople {

    private static List<Streams.Person> people;

    static {

        List<Streams.Department> departments = GenerateDepartments.getDepartments();
        List<String> names = Names.getNames();
        people = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            people.add(new Streams.Person(
                    names.get(i),
                    ThreadLocalRandom.current().nextInt(20, 61),
                    ThreadLocalRandom.current().nextInt(20_000, 100_000) * 1.0,
                    departments.get(ThreadLocalRandom.current().nextInt(departments.size()))
            ));
        }
    }

    public static List<Streams.Person> getPeople() {
        return people;
    }
}
