package com.craft;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

    // Class-wide factory: created once. The name must match persistence.xml.
    public static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpadb1PU");

    public static void main(String[] args) {
        try {
            // Run ONE step at a time. Uncomment the step you want (Ctrl+/),
            // comment out the one you just ran, save, then run App.
            // Expected table before step 2.3: (1, John Snow, Java), (2, Mohammed ali, Python)

            // ---- Phase 1: persist test (inserts John Snow, id 1) -- DONE ----
            // Student student = new Student();
            // student.setName("John Snow");
            // student.setTech("Java");
            // createStudent(student);

            // ---- 2.1 createStudent (inserts Mohammed ali, id 2) -- DONE ----
            // Student student1 = new Student();
            // student1.setName("Mohammed ali");
            // student1.setTech("Python");
            // createStudent(student1);

            // ---- 2.2 getStudentById -- DONE ----
            // System.out.println(getStudentById(2));

            // ---- 2.3 updateStudentName ----
            updateStudentName("Alemayehu", 2);
            System.out.println(getStudentById(2));

            // ---- 2.4 updateStudentNameAndTech ----
            updateStudentNameAndTech("Alemayehu Kebede", "Java", 2);
            System.out.println(getStudentById(2));

            // ---- 2.5 deleteStudentById ----
            deleteStudentById(2);
            System.out.println(getStudentById(2)); // prints null: the row is gone

        } finally {
            emf.close();
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Create
    public static void createStudent(Student student) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            System.out.println("Created: " + student);
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    // Read: returns null if no student has this id
    public static Student getStudentById(int id) {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.find(Student.class, id);
        } finally {
            entityManager.close();
        }
    }

    // Update one field
    public static void updateStudentName(String newName, int id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, id);
            if (student == null) {
                System.out.println("No student with id " + id);
                entityManager.getTransaction().rollback();
                return;
            }
            // student is managed, so no merge() is needed:
            // Hibernate sends the UPDATE at commit (dirty checking)
            student.setName(newName);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    // Update two fields
    public static void updateStudentNameAndTech(String newName, String newTech, int id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, id);
            if (student == null) {
                System.out.println("No student with id " + id);
                entityManager.getTransaction().rollback();
                return;
            }
            student.setName(newName);
            student.setTech(newTech); // PDF bug: it calls setName(newTech) here
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    // Delete
    public static void deleteStudentById(int id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, id);
            if (student == null) {
                System.out.println("No student with id " + id);
                entityManager.getTransaction().rollback();
                return;
            }

            // contains(): true if the object is managed by this EntityManager
            System.out.println("Before remove, contains = " + entityManager.contains(student)); // true
            entityManager.remove(student);
            System.out.println("After remove, contains = " + entityManager.contains(student));  // false

            entityManager.getTransaction().commit();
            System.out.println("Deleted student with id " + id);
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
