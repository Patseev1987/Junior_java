package org.example.lesson2.tests;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void run(Class<?> testClass) {
        final Object testObj = initTestObj(testClass);
        Helper helper = new Helper();


        Method[] methodsWithAnnotationTest = helper.getMethodsWithAnnotation(testClass.getDeclaredMethods(),
                Test.class);
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
            for (Method methodTest : methodsWithAnnotationTest) {
                for (Method methodBeforeEach : methodsWithAnnotationBeforeEach) {
                    methodBeforeEach.invoke(testObj);
                }

                methodTest.invoke(testObj);

                for (Method methodAfterEach : methodsWithAnnotationAfterEach) {
                    methodAfterEach.invoke(testObj);
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
            for (Method testMethod : declaredMethods) {
                if (testMethod.accessFlags().contains(AccessFlag.PRIVATE)) {
                    continue;
                }
                if (testMethod.getAnnotation(targetAnnatationClass) != null) {
                    result.add(testMethod);
                }
            }
            return result.toArray(Method[]::new);
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
