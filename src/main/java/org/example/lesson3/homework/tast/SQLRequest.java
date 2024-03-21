package org.example.lesson3.homework.tast;

import java.lang.reflect.InvocationTargetException;

public class SQLRequest {
    static String getSQLRequestForSaveInstance(Object object) throws InvocationTargetException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append(CustomClass.getTableName(object));
        sb.append("(");
        var customFields = CustomClass.getFieldsInformation(object);
        for (int i = 0; i < customFields.size(); i++) {
            if (!customFields.get(i).getIdField()) {
                sb.append(customFields.get(i).getColumnName());
            }else{
                continue;
            }
                if (i < CustomClass.getFieldsInformation(object).size()-1) {
                    sb.append(", ");
                } else {
                    sb.append(") values (");
                }

        }
        for (int i = 0; i < customFields.size(); i++) {
            if (!customFields.get(i).getIdField()) {
                sb.append(customFields.get(i).getValueInStringType());
            }else{
                continue;
            }

            if (i < CustomClass.getFieldsInformation(object).size()-1) {
                sb.append(", ");
            } else {
                sb.append(")");
            }
        }
        return sb.toString();
    }
}
