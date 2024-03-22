package org.example.lesson3.homework.task3_1;

import java.lang.reflect.Method;

public class CustomField {

    private boolean idField;
    private String name;
    private String columnName;
    private Method setter;
    private Method getter;
    private String valueInStringType;
    private Class<?> type;

    public boolean getIdField() {
        return idField;
    }

    public void setIdField(boolean idField) {
        this.idField = idField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Method getSetter() {
        return setter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }

    public Method getGetter() {
        return getter;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }

    public String getValueInStringType() {
        return valueInStringType;
    }

    public void setValueInStringType(String valueInStringType) {
        this.valueInStringType = valueInStringType;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CustomField{" +
                "idField=" + idField +
                ", name='" + name + '\'' +
                ", columnName='" + columnName + '\'' +
                ", setter=" + setter +
                ", getter=" + getter +
                ", valueInStringType='" + valueInStringType + '\'' +
                ", type=" + type +
                '}'+'\n';
    }
}
