import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

//Extract all employees from the Research and Development department. Order them by salary (in ascending order),
//then by first name (in descending order). Print only their first name, last name, department name and salary.

public class _05_EmployeesFromSeattle {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //we get just the first name (e.firstName) and the query returns a list of Strings
        Query employeesFromResearchAndDevelopmentDepartment = entityManager.createQuery("SELECT e FROM Employee AS e WHERE e.departmentId = 6");
        List<Employee> employees = employeesFromResearchAndDevelopmentDepartment.getResultList();

        for (Employee employee : employees) {
            //here we change BigDecimal to get the .00 value and put it to a String
            String displayBigDecimal = employee.getSalary().setScale(2).toPlainString();

            //here we go in Employee get the Department Id and get the name from Department.class
            System.out.printf("%s %s from %s - %s\n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartmentId().getName(),
                    displayBigDecimal);
        }


        //we commit all objects that we have persisted and then close all connections
//        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
