import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

//Write a program to find the max salary for each department.
//Filter those which have max salaries not in the range 30000 and 70000.

public class _11_EmployeesMaximumSalaries {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery("SELECT d.name, MAX(e.salary) AS max_salary\n" +
                "FROM employees AS e\n" +
                "JOIN departments AS d\n" +
                "\tON e.department_id = d.department_id\n" +
                "GROUP BY e.department_id\n" +
                "HAVING MAX(e.salary) NOT BETWEEN 30000 and 70000\n" +
                "ORDER BY max_salary");
        List<Object[]> departments = query.getResultList();

        for (Object[] department : departments){
            String name = (String) department[0];
            BigDecimal salary = (BigDecimal) department[1];

            System.out.printf("%s - %s\n", name, salary.setScale(2).toPlainString());
        }
        
        //we commit all objects that we have persisted and then close all connections
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
