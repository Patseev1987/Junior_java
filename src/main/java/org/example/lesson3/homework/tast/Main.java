package org.example.lesson3.homework.tast;

import org.example.lesson3.homework.annotations.Column;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Dog dog = new Dog();
        dog.setAge(4);
        dog.setName("Juzz");


        var fields = dog.getClass().getDeclaredFields();

        for (var field:fields) {
            System.out.println(field.getName());
            if (field.getDeclaredAnnotation(Column.class) != null){
                System.out.println(field.getDeclaredAnnotation(Column.class).name());
            }
        }


        var lll = CustomClass.getFieldsInformation(dog);
        System.out.println(lll);
    }
}
