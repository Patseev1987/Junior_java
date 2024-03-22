package org.example.lesson3.homework.task3_1;

import org.example.lesson3.homework.annotations.Column;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/junior_java",
                "root",
                "Root1234")) {

            CustomSQLMapper customSQLMapper = new CustomSQLMapper(connection);

            Student student = new Student();
            student.setAge(33);
            student.setFirstName("Nick");
            student.setSecondName("Rock");

            customSQLMapper.saveObjectIntoDatabase(student);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}