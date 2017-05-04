import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

//Write a program that increase salaries of all employees that are in the Engineering,
// Tool Design, Marketing or Information Services department by 12%.
// Then print first name, last name and salary for those employees whose salary was increased.

public class _08_IncreaseSalaries {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //Use Create Query - JPQL - Java Persist Query Language
        //we have a List of Employees returned that are IN certain department name
        Query query = entityManager.createQuery("SELECT e FROM Employee e JOIN e.departmentId p WHERE p.name IN " +
                "('Engineering', 'Marketing', 'Tool Design', 'Information Services')");
        List<Employee> employees = query.getResultList();

        for (Employee employee : employees){
            //using Big Decimal to add and multiply the salary by 0.12
            employee.setSalary(employee.getSalary().add(employee.getSalary().multiply(new BigDecimal(0.12))));
            System.out.println(employee.getFirstName() + " " + employee.getLastName() + " " +employee.getSalary());
        }

        //we commit all objects that we have persisted and then close all connections
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
