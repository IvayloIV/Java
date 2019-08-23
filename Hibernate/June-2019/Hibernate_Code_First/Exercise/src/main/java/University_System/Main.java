package University_System;

import University_System.entities.Course;
import University_System.entities.Student;
import University_System.entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("university_system");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Teacher teacher = new Teacher("Nina3", "Ivanova", "234234", "nina@abv.bg", 23.2);
        em.persist(teacher);

        Course course = new Course("java3", "cool", Date.valueOf("2032-2-3"), Date.valueOf("2035-2-3"), 3, teacher);
        em.persist(course);
        Student student1 = new Student("Pesho3", "Peshev", "232", 4.3, 2);
        student1.getCourses().add(course);

        em.persist(student1);

        em.getTransaction().commit();

    }
}
