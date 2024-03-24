package org.example.lesson4.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(StudentEntity.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            try (Session session = sessionFactory.getCurrentSession()) {
                Transaction tr1 = session.beginTransaction();
                var student = session.find(StudentEntity.class, 4);
                System.out.println(student);
                tr1.commit();
            }
//            try(Session session = sessionFactory.getCurrentSession()) {
//                Transaction tr1 = session.beginTransaction();
//                var student1 = new StudentEntity("John","Weak",45);
//                var student2 = new StudentEntity("LeBron","James",40);
//                var student3 = new StudentEntity("Piter", "Parker",23);
//                var student4 = new StudentEntity("Otto", "Bismark",18);
//                var student5 = new StudentEntity("Mila", "Tree",19);
//                var student6 = new StudentEntity("Coule", "Lee",20);
//                var student7 = new StudentEntity("Merline", "Monro",18);
//                var student8 = new StudentEntity("Rachel", "Lopes",19);
//                var student9 = new StudentEntity("Daniel", "Red",19);
//
//                session.persist(student1);
//                session.persist(student2);
//                session.persist(student3);
//                session.persist(student4);
//                session.persist(student5);
//                session.persist(student6);
//                session.persist(student7);
//                session.persist(student8);
//                session.persist(student9);
//                tr1.commit();
//
//                System.out.println(student1);
//                System.out.println(student2);
//                System.out.println(student3);
//                System.out.println(student4);
//                System.out.println(student5);
//                System.out.println(student6);
//                System.out.println(student7);
//                System.out.println(student8);
//                System.out.println(student9);
//
//            }



            try (Session session = sessionFactory.getCurrentSession()) {
                Transaction tr1 = session.beginTransaction();
                var query = session.createQuery("select s from StudentEntity s where age < :age", StudentEntity.class);
                query.setParameter("age", 20);
                List<StudentEntity> students = query.getResultList();
                System.out.println(students);
                tr1.commit();
            }
        }
    }
}
