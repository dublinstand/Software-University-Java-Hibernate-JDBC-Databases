import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

//Use the soft_uni database. Create new Department Training. Create new Town Burgas.
//Create several new addresses that would be in Burgas. Create 5 employees and assign them in the Training department and make their addresses
//to be some of the addresses in Burgas. Make sure they are all persisted in the database.

public class _01_CreateObjects {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Department trainingDepartment = new Department();
        //we set the name of the object
        trainingDepartment.setName("Department Training");

        //create an Employee object and get from Employee.class, with id = 5
        //to add to our trainingDepartment
        Employee employeeManager = entityManager.find(Employee.class, 5);
        trainingDepartment.setManagerId(employeeManager);


        //Create new town with name Burgas
        Town town = new Town();
        town.setName("Burgas");


        //create new address and add the created town to the address
        Address newAddress = new Address();
        newAddress.setAddressText("Vasil Levski 15");
        newAddress.setTown(town);


        //create new Project to be used for the employee's project
        Project project = new Project();
        project.setName("Thincats project");
        project.setDescription("Automation of a loan application");
        project.setStartDate(new Date());
        project.setEndDate(new Date());



        //create new employee with BigDecimal,
        Employee employee = new Employee();
        employee.setFirstName("Johny");
        employee.setMiddleName("J.");
        employee.setLastName("Bravo");
        employee.setJobTitle("Master Trainer");
        //this way we set Big Decmimal
        employee.setSalary(new BigDecimal("10000"));

        //we use the address we created, otherwise you can use .find(Address.class, 2 (id))
        employee.setAddressId(newAddress);
        employee.setHireDate(new Date());

        //we use the department we have created
        employee.setDepartmentId(trainingDepartment);

        //we get the manager Id and set it as manager for John
        Employee employeeManagerJohn = entityManager.find(Employee.class, 11);
        employee.setManager(employeeManagerJohn);

        //add a new project but first initialise the list
        employee.setProjects(new ArrayList<Project>());
        employee.getProjects().add(project);

        //before we commit we need to persist all tables(objects) we have created
        //we must persist not to violate foreign keys - first the main table, then the one that references it
        entityManager.persist(trainingDepartment);
        entityManager.persist(town);
        entityManager.persist(newAddress);
        entityManager.persist(project);
        entityManager.persist(employee);


        //we commit all objects that we have persisted and then close all connections
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
