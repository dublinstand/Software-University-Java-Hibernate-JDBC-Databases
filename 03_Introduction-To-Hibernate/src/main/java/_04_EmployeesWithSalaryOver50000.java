import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

//Your first task is to extract all employees with salary over 50000. Make sure the query returns only the first names of those employees.

public class _04_EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //we get just the first name (e.firstName) and the query returns a list of Strings
        Query getEmployeesOver50000Salary = entityManager.createQuery("SELECT e.firstName FROM Employee AS e WHERE e.salary > 50000");
        List<String> employeeNames = getEmployeesOver50000Salary.getResultList();

        for (String employeeName : employeeNames ){
            System.out.println(employeeName);
        }


        //we commit all objects that we have persisted and then close all connections
//        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
