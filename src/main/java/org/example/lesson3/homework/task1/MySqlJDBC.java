package org.example.lesson3.homework.task1;

import java.sql.*;
import java.util.*;


public class MySqlJDBC {
    private static Random random = new Random();


    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/junior_java",
                "root",
                "Root1234")) {

            acceptConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void acceptConnection(Connection connection) {
        dropTable(connection);
        createTable(connection);
        insertData(connection);
        deleteRandomRow(connection);
        insertStudent(connection,"John","River",33);
        updateData(connection,"Poul","John");
    }

    private static void createTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    create table students (
                        id int primary key auto_increment not null ,
                        first_name varchar(30) not null,
                        second_name varchar(50) not null,
                        age int not null
                    );
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertData(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    insert into students (first_name, second_name, age)
                    values
                    ('Chuck', 'Norris', 84),
                    ('Leonardo', 'DiCaprio', 49),
                    ('Pamela', 'Andderson', 56)
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteRandomRow(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet results = statement.executeQuery("""
                    select id from students
                    """);
            List<Integer> idList = new ArrayList<>();
            while (results.next()) {
                idList.add(results.getInt("id"));
            }
            results.close();
            int randomId = idList.get(random.nextInt(idList.size()));
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    delete from students where id = ?
                    """);
            preparedStatement.setInt(1, randomId);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void dropTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                     drop table students
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void updateData(Connection connection, String newName, String oldName) {
        try (PreparedStatement statement = connection.prepareStatement("""
                update students set first_name = ? where first_name = ?
                """)) {
            statement.setString(1, newName);
            statement.setString(2, oldName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertStudent(Connection connection,
                                      String name,
                                      String surname,
                                      int age) {
        try (PreparedStatement statement = connection.prepareStatement("""
                insert into students (first_name, second_name, age)
                values (?,?,?)
                """)) {
            statement.setString(1,name);
            statement.setString(2,surname);
            statement.setInt(3,age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


