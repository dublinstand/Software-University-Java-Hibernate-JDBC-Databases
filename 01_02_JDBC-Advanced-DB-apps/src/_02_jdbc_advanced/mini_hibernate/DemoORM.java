package _02_jdbc_advanced.mini_hibernate;


import _02_jdbc_advanced.mini_hibernate.connection.DatabaseConnection;
import _02_jdbc_advanced.mini_hibernate.models.User;
import _02_jdbc_advanced.mini_hibernate.orm.EntityManager;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DemoORM {
    public static void main(String[] args) throws InstantiationException {
        try {
            EntityManager em = new EntityManager(DatabaseConnection.getConnection());
            User user = new User("Ivan2", 23, new Date());
            user.setId(1);
            em.persist(user);
            User userFind = em.findFirst(User.class);
            System.out.println(userFind.toString());

            List<User> users = em.find(User.class);
            for (User userFromList : users) {
                System.out.println(userFromList.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
