package org.example.lesson1.hw;

import org.example.lesson1.Streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Homework {

    /**
     * Реалзиовать методы, описанные ниже:
     */


    /**
     * Вывести на консоль отсортированные (по алфавиту) имена персонов
     */
    public void printNamesOrdered(List<Streams.Person> people) {
        people.stream().map(Streams.Person::getName).sorted().forEach(System.out::println);
    }

    /**
     * В каждом департаменте найти самого взрослого сотрудника.
     * Вывести на консоль мапипнг department -> personName
     * Map<Department, Person>
     */
    public Map<Streams.Department, Streams.Person> printDepartmentOldestPerson(List<Streams.Person> persons) {
        return persons.stream().collect(Collectors.toMap(Streams.Person::getDepartment, it -> it, (first, second) -> {
            if (first.getAge() > second.getAge()) {
                return first;
            } else {
                return second;
            }
        }));
    }

    /**
     * Найти 10 первых сотрудников, младше 30 лет, у которых зарплата выше 50_000
     */
    public List<Streams.Person> findFirstPersons(List<Streams.Person> people) {
        return people.stream().filter(person -> person.getAge() < 30).filter(person -> person.getSalary() > 50_000).limit(10).toList();
    }

    /**
     * Найти депаратмент, чья суммарная зарплата всех сотрудников максимальна
     */


    public Optional<Streams.Department> findTopDepartment(List<Streams.Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(Streams.Person::getDepartment
                        , Collectors.averagingDouble(Streams.Person::getSalary))
                ).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}