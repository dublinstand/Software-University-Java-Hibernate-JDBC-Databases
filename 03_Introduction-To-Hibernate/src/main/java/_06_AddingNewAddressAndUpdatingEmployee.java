import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Create a new address with text "Vitoshka 15". Set that address to the employee with last name “Nakov”

public class _06_AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Address address = new Address();
        address.setAddressText("Vitoshka 15");
        Town town = entityManager.find(Town.class, 33);
        address.setTown(town);

        //We getSingleResult as an object and cast it to Employee
        Employee employee = (Employee) entityManager.createQuery("SELECT e FROM Employee AS e WHERE e.lastName = 'Nakov'").getSingleResult();
        employee.setAddressId(address);

        //persist first the address and then employee
        entityManager.persist(address);
        entityManager.persist(employee);

        //we commit all objects that we have persisted and then close all connections
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
