package org.example.lesson3.homework.task3_1;

import org.example.lesson3.homework.annotations.Column;
import org.example.lesson3.homework.annotations.Id;
import org.example.lesson3.homework.annotations.Table;

@Table(name = "dogs")
public class Dog {
    @Id
    @Column(name = "AGE")
    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
