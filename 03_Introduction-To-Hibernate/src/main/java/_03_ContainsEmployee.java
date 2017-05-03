import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

//Use soft_uni database. Write a program that check if given employee name as an input is contained in the database.
//Input	            Output
//Svetlin Nakov	    Yes
//John Doe	        No

public class _03_ContainsEmployee {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        String firstName = input[0];
        String lastName = input[1];
        Boolean isInDatabase = false;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query getAllTowns = entityManager.createQuery("SELECT e FROM Employee AS e");
        List<Employee> employees = getAllTowns.getResultList();



        //we commit all objects that we have persisted and then close all connections
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
