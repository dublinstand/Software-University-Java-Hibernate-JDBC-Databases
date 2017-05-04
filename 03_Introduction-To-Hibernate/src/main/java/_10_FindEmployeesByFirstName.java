import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

//Write a program that finds all employees whose first name starts with pattern given as an input from the console.
//Print their first, last name, their job title and salary in the format given in the examples below.

public class _10_FindEmployeesByFirstName {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String startName = scanner.next();

        Query query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE '" + startName + "%'");
        List<Employee> employees = query.getResultList();

        for (Employee employee : employees){
            System.out.printf("Name: %s %s - %s - ($%s)\n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle(), employee.getSalary().setScale(2).toPlainString());
        }
        
        //we commit all objects that we have persisted and then close all connections
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
