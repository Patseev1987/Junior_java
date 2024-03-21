package org.example.lesson3.homework;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.lesson3.homework.annotations.Column;
import org.example.lesson3.homework.annotations.Id;
import org.example.lesson3.homework.annotations.Table;


@Table (name ="students")
public class Student {
    @Id
    @Column (name = "id")
    int id;
    @Column(name = "first_name")
    String firstName;
    @Column( name = "second_name")
    String secondName;
    @Column( name = "age")
    int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
