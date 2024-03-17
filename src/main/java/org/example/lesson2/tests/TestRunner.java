package org.example.lesson2.tests;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestRunner {

    public static void run(Class<?> testClass) {
        final Object testObj = initTestObj(testClass);
        Helper helper = new Helper();


        Map<Integer,ArrayList<Method>> methodsWithAnnotationTest =
                helper.getMethodsWithAnnotationTestWithValue(testClass.getDeclaredMethods());
        Method[] methodsWithAnnotationBeforeEach = helper.getMethodsWithAnnotation(testClass.getDeclaredMethods(),
                BeforeEach.class);
        Method[] methodsWithAnnotationBeforeAll = helper.getMethodsWithAnnotation(testClass.getDeclaredMethods(),
                BeforeAll.class);
        Method[] methodsWithAnnotationAfterEach = helper.getMethodsWithAnnotation(testClass.getDeclaredMethods(),
                AfterEach.class);
        Method[] methodsWithAnnotationAfterAll = helper.getMethodsWithAnnotation(testClass.getDeclaredMethods(),
                AfterAll.class);

        try {
            for (Method methodBeforeAll : methodsWithAnnotationBeforeAll) {
                methodBeforeAll.invoke(testObj);
            }

            for (var methodTest : methodsWithAnnotationTest.entrySet()) {
                for (var method :methodTest.getValue()) {
                    for (Method methodBeforeEach : methodsWithAnnotationBeforeEach) {
                        methodBeforeEach.invoke(testObj);
                    }
                    method.invoke(testObj);
                    for (Method methodAfterEach : methodsWithAnnotationAfterEach) {
                        methodAfterEach.invoke(testObj);
                    }
                }
            }
            for (Method methodAfterAll : methodsWithAnnotationAfterAll) {
                methodAfterAll.invoke(testObj);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


    private static class Helper {
        private Method[] getMethodsWithAnnotation(Method[] declaredMethods, Class<? extends Annotation> targetAnnatationClass) {
            List<Method> result = new ArrayList<>();
            for (Method method : declaredMethods) {
                if (method.accessFlags().contains(AccessFlag.PRIVATE)) {
                    continue;
                }
                if (method.getAnnotation(targetAnnatationClass) != null) {
                    result.add(method);
                }
            }
            return result.toArray(Method[]::new);
        }



        private Map<Integer,ArrayList<Method>> getMethodsWithAnnotationTestWithValue(Method[] declaredMethods) {
            Map<Integer,ArrayList<Method>> result = new TreeMap<>();
            for (Method testMethod : declaredMethods) {
                if (testMethod.accessFlags().contains(AccessFlag.PRIVATE)) {
                    continue;
                }
                if (testMethod.getAnnotation(Test.class) != null) {
                    int key = testMethod.getAnnotation(Test.class).order();
                    ArrayList<Method> methodsList;
                    if (result.containsKey(key)) {
                        methodsList = result.get(key);
                    }else {
                        methodsList = new ArrayList<>();
                    }
                    methodsList.add(testMethod);
                    result.put(key,methodsList);
                }
            }
            return result;
        }
    }


    private static Object initTestObj(Class<?> testClass) {
        try {
            Constructor<?> noArgsConstructor = testClass.getConstructor();
            return noArgsConstructor.newInstance();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Нет конструктора по умолчанию");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось создать объект тест класса");
        }
    }

}
