package org.example.lesson3.homework.tast;

import org.example.lesson3.homework.annotations.Column;
import org.example.lesson3.homework.annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CustomClass {


    public static List<CustomField> getFieldsInformation(Object target) throws IllegalAccessException, InvocationTargetException {
        List<CustomField> result = new ArrayList<>();
        var fields = target.getClass().getDeclaredFields();
        var methods = target.getClass().getDeclaredMethods();
        var getters = getGetters(methods);
        var setters = getSetters(methods);

        for (var field:fields) {
            CustomField customField = new CustomField();
            customField.setName(field.getName());
            customField.setType(field.getType());
            Method getter = findGetterByNameFiled(field.getName(),getters);
            Method setter = findSetterByNameFiled(field.getName(),setters);
            customField.setGetter(getter);
            customField.setSetter(setter);
            customField.setColumnName(getNameColumnName(field));
            customField.setIdField(isIdField(field));
            customField.setValueInStringType(getter.invoke(target).toString());
            result.add(customField);
        }
        return result;
    }


    private static List<Method> getGetters(Method[] methods) {
        List<Method> result = new ArrayList<>();
        for (var method : methods) {
            if (isGetter(method)){
                result.add(method);
            }
        }
        return result;
    }

    private static List<Method> getSetters(Method[] methods) {
        List<Method> result = new ArrayList<>();
        for (var method : methods) {
            if (isSetter(method)){
                result.add(method);
            }
        }
        return result;
    }

    private static boolean isGetter(Method method) {

        if (!method.getName().startsWith("get")) {
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        if (void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }


    private static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) {
            return false;
        }
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        return true;
    }

    private static Method findGetterByNameFiled(String fieldName, List<Method> getters){
        for (var method:getters) {
            if (method.getName().toLowerCase().contains(fieldName.toLowerCase())){
                return method;
            }
        }
        throw new RuntimeException("getter for field '"+fieldName+"' wasn't found!");
    }
    private static Method findSetterByNameFiled(String fieldName,List<Method> setters){
        for (var method:setters) {
            if (method.getName().toLowerCase().contains(fieldName.toLowerCase())){
                return method;
            }
        }
        throw new RuntimeException("setter for field '"+fieldName+"' wasn't found!");
    }


    private static boolean isIdField(Field field){
        if (field.getDeclaredAnnotation(Id.class) != null){
            return true;
        }else{
            return false;
        }
    }

    private static String getNameColumnName(Field field){
        if (field.getDeclaredAnnotation(Column.class) != null){
            return field.getDeclaredAnnotation(Column.class).name();
        }else{
            return field.getName();
        }
    }
}
