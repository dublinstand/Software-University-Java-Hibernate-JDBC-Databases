import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

//Write a program that deletes town which name is given as an input.
//Also delete all addresses that are in those towns. Print on the console the number addresses that were deleted.

public class _09_RemoveTowns {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String townName = scanner.next();

        //Note that in the soft_uni database there is a constraint in employees table that connects address_id with the addresses table
        //You need to set the constraint in the database in employees table ON DELETE to be Cascade

        //get the town object based on the name with JPQL query
        Query townSql = entityManager.createQuery("SELECT t FROM Town t WHERE t.name = '" + townName + "'");
        Town town = (Town) townSql.getSingleResult();
        int townId = town.getTownId();
        System.out.println("Town ID: " + townId);

        //after we know the town ID get all addresses
        Query addressesSql = entityManager.createQuery("SELECT a FROM Address a WHERE a.town = '" + townId + "'");
        List<Address> addresses = addressesSql.getResultList();

        //get the count of all addresses
        int count = addresses.size();

        for (Address address : addresses) {
            //delete first the addresses
            entityManager.remove(address);
            System.out.println(address.getAddressText() + " was removed");
        }

        //after there are no more addresses left attached to the town, you can delete the town
        entityManager.remove(town);

        //print
        if (count > 1) {
            System.out.println(count + " addresses in " + townName + " were deleted");
        } else {
            System.out.println(count + " address in " + townName + " was deleted");

        }

        //we commit all objects that we have persisted and then close all connections
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
