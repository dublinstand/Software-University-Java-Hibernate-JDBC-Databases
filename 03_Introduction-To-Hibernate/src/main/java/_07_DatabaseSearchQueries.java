import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

//Write the following queries:
//1. Find all employees who have projects started in the time period 2001 - 2003 (inclusive).
//Print each employee's first name, last name and manager name and each of their projects' name, start date, end date.

//2. Find all addresses, ordered by the number of employees who live there (descending), then by town name (ascending).
//Take only the first 10 addresses and print their address text, town name and employee count.

public class _07_DatabaseSearchQueries {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //Join the tables with SQL query and return all records from the employees table and the project_id from employees_projects table
        //to use to get our objects
        Query query = entityManager.createNativeQuery("SELECT e.*, ep.project_id " + "FROM employees AS e " + "INNER JOIN employees_projects AS ep" +
                "\tON e.employee_id = ep.employee_id\n" +
                "INNER JOIN projects AS p\n" +
                "\tON ep.project_id = p.project_id\n" +
                "WHERE p.start_date > '2001-01-01'");

        List<Object[]> objects = query.getResultList();

        for (Object[] object : objects){
            //get all primary keys - for our employee, for our manager and for the project
            int primaryKey = (Integer) object[0];
            int managerId = (Integer) object[6];
            int projectId = (Integer) object[10];

            System.out.println(primaryKey + " " + managerId + " " + projectId);

            //create our objects from the entities based on the class and the primary key
            Employee employee = entityManager.find(Employee.class, primaryKey);
            Employee manager = entityManager.find(Employee.class, managerId);
            Project project = entityManager.find(Project.class, projectId);

            //now we can print their properties
            System.out.println(employee.getFirstName() + " " + employee.getLastName() + " " + manager.getFirstName() + " " +
            manager.getLastName() + " " + project.getName() + " " + project.getStartDate() + " " + project.getEndDate());
        }

        //Write the query to get address text, name and count of all employees in that address
        Query query2 = entityManager.createNativeQuery("SELECT a.address_text, t.name, COUNT(e.address_id) AS counts\n" +
                "FROM addresses AS a\n" +
                "JOIN towns AS t\n" +
                "\tON a.town_id = t.town_id\n" +
                "JOIN employees AS e\n" +
                "\tON a.address_id = e.address_id\n" +
                "GROUP BY e.address_id\n" +
                "ORDER BY counts DESC\n" +
                "LIMIT 10");
        List<Object[]> object2 = query2.getResultList();

        for (Object[] object : object2 ){
            String address = (String) object[0];
            String townName = (String) object[1];
            BigInteger count = (BigInteger) object[2];

            System.out.printf("Address: %s, Town: %s, Number of employees: %d\n", address, townName, count);
        }

        //we commit all objects that we have persisted and then close all connections
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
