import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;


//Use soft_uni database. Persist all towns from the database. Detach those whose name length is more than 5 symbols.
//Then transform the name of all attached towns to lowercase and save them to the database.

public class _02_RemoveObjects {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

//        //Native Query
//        //create a Native Query that is using the SQL syntax
//        Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM towns AS t WHERE LENGTH(t.name) > 5");
//        //get the result of the native query as a List and return it as an Object array
//        //because the query can not link it to our Town.class entity
//        List<Object[]> towns = nativeQuery.getResultList();
//
//        //it is done this way because we use Native Query
//        for (Object[] object : towns){
//            //we cast the first row of the object which is the Primary Key to Integer
//            int primaryKey = (Integer) object[0];
//            //we find the town with the id in the object returned
//            Town town = entityManager.find(Town.class, primaryKey);
//            //use detach to remove it from the database
//            entityManager.detach(town);
//            System.out.println(town.getName());
//        }


        //Use Create Query - JPQL - Java Persist Query Language
        //Here we have Town as our POCO object Town.class and we can declare it as List<Town> town
        //instead of Object[] and work with its properties
        Query jpqlQuery = entityManager.createQuery("SELECT t FROM Town AS t WHERE LENGTH(t.name) > 11");

        //here we have the first 5 lines returned
        //We return t from Town and we return a List<Town>
        List<Town> towns2 = jpqlQuery.setMaxResults(5).getResultList();
        for (Town town : towns2){
            town.setName(town.getName().toLowerCase());

            //we persist the updated object, set its name to Lower case and save(persist) it
            //after we finish all persisting we do a commit
            entityManager.persist(town);
            System.out.println(town.getName());
        }

        //we commit all objects that we have persisted and then close all connections
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
