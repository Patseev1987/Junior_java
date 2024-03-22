package org.example.lesson3.homework;

import org.example.lesson3.homework.annotations.Column;
import org.example.lesson3.homework.annotations.Id;
import org.example.lesson3.homework.annotations.Table;
import org.example.lesson3.homework.tast.CustomClass;
import org.example.lesson3.homework.tast.CustomField;
import org.example.lesson3.homework.tast.Dog;
import org.example.lesson3.homework.tast.SQLRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomSQLMapper {
    private Connection connection;

    public CustomSQLMapper(Connection connection) {
        this.connection = connection;
    }


        public void saveObjectIntoDatabase(Object object){
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    SQLRequest.getSQLRequestForSaveInstance(object)
            );
        } catch (SQLException
                 | InvocationTargetException
                 | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        }

    private  <T> T getObjectFromDatabase(T object) {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(
                    SQLRequest.getSQLRequestForGettingInstance(object)
            );
            var fields = CustomClass.getFieldsInformation(object);
            T newObject = (T) getNewObject(object.getClass());
            var newObjectFields = CustomClass.getFieldsInformation(newObject);
            for (var field : newObjectFields) {
                setNewValueInObject(field, newObject, result);
            }
        return newObject;

        } catch (SQLException
                 | InvocationTargetException
                 | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void setNewValueInObject(CustomField field, Object newObject, ResultSet result) throws SQLException, InvocationTargetException, IllegalAccessException {
        if (field.getType().equals(int.class)) {
            var newValueForField = result.getInt(field.getColumnName());
            field.getSetter().invoke(newObject, newValueForField);
        } else if (field.getType().equals(String.class)) {
            String newValueForField = result.getString(field.getColumnName());
            field.getSetter().invoke(newObject, newValueForField);
        } else if (field.getType().equals(long.class)) {
            long newValueForField = result.getLong(field.getColumnName());
            field.getSetter().invoke(newObject, newValueForField);
        } else if (field.getType().equals(double.class)) {
            double newValueForField = result.getDouble(field.getColumnName());
            field.getSetter().invoke(newObject, newValueForField);
        } else if (field.getType().equals(float.class)) {
            double newValueForField = result.getFloat(field.getColumnName());
            field.getSetter().invoke(newObject, newValueForField);
        } else if (field.getType().equals(short.class)) {
            short newValueForField = result.getShort(field.getColumnName());
            field.getSetter().invoke(newObject, newValueForField);
        } else if (field.getType().equals(byte.class)) {
            byte newValueForField = result.getByte(field.getColumnName());
            field.getSetter().invoke(newObject, newValueForField);
        } else if (field.getType().equals(boolean.class)) {
            boolean newValueForField = result.getBoolean(field.getColumnName());
            field.getSetter().invoke(newObject, newValueForField);
        }
    }


    private <T> T getNewObject(Class<T> target) {
        var constructors = target.getDeclaredConstructors();
        for (var constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                Object o;
                try {
                    o = constructor.newInstance();
                } catch (InstantiationException
                         | IllegalAccessException
                         | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                return (T) o;
            }
        }
        throw new ClassCastException("ClassCastException");
    }


}
