package org.example.lesson3.homework;

import org.example.lesson3.homework.annotations.Column;
import org.example.lesson3.homework.annotations.Id;
import org.example.lesson3.homework.annotations.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomSQLMapper {






    private class GetterAnnotationHelper {

        private Class<?> target;

        public GetterAnnotationHelper(Class<?> target) {
            this.target = target;
        }

        private List<Field> getFieldsWithAnnotation(Field[] declaredFields, Class<? extends Annotation> targetAnnatationClass) {
            List<Field> result = new ArrayList<>();
            for (Field field : declaredFields) {
                if (field.accessFlags().contains(AccessFlag.PRIVATE)) {
                    continue;
                }
                if (field.getAnnotation(targetAnnatationClass) != null) {
                    result.add(field);
                }
            }
            return result;
        }


        private Field getFieldWithId(Class <?> target) {
            if (getFieldsWithAnnotation(target.getDeclaredFields(), Id.class).size() > 1) {
                throw new RuntimeException("Field with id should be only one!");
            } else {
                return getFieldsWithAnnotation(target.getDeclaredFields(), Id.class).get(0);
            }
        }

        private List<Field> getFieldsWithColumn(Class <?> target) {
            return getFieldsWithAnnotation(target.getDeclaredFields(), Column.class);
        }

        private String getTableName() {
            String tableName;
            if (target.getAnnotation(Table.class) != null) {
                tableName = target.getAnnotation(Table.class).name();
            } else {
                tableName = target.getSimpleName();
            }
            return tableName;
        }

        private void getFieldsFromObject(Object object){



        }


//        public boolean saveObject(Object object){
//            String tableName = getTableName();
//            Map<String,Map<>>
//        }

    }
}
