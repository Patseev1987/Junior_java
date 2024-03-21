package org.example.lesson3.homework;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.lesson3.homework.annotations.Column;
import org.example.lesson3.homework.annotations.Id;
import org.example.lesson3.homework.annotations.Table;

@Data
@NoArgsConstructor
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
}
