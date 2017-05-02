package _02_jdbc_advanced;

//Write a program that connects to your localhost server.
//Create new database called MinionsDB where we will keep information about our minions and villains.
//For each minion we should keep information about its name, age and town.
//Each town has information about in which country is located.
//Villains have name and evilness factor (good, bad, evil, super evil).
//Each minion can serve to several villains and each villain can have several minions to serve him.
//Fill all tables with at least 5 records each.


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class _01_Initial {

    private static final String URL = "jdbc:mysql://localhost:3306?useSSL=false";

    private static final String USER = "root";

    private static final String PASSWORD = "root";

    public static void main(String[] args) {


        //here we are going to use Try with resources. All resources are entered before the {} part so that they will
        //be closed automatically
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String dropQuery = "DROP DATABASE IF EXISTS minions_db";
            statement.execute(dropQuery);

            String sql = "CREATE DATABASE minions_db";
            statement.execute(sql);

            sql = "USE minions_db";
            statement.execute(sql);

            sql = "CREATE TABLE towns(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "country VARCHAR(50)" +
                    ")";
            statement.execute(sql);

            sql = "CREATE TABLE minions(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "age INT," +
                    "town_id INT," +
                    "CONSTRAINT FK_minions_towns FOREIGN KEY (town_id) REFERENCES towns(id) " +
                    ")";
            statement.execute(sql);

            sql = "CREATE TABLE villains(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "age INT," +
                    "evilness_factor ENUM ('good', 'bad', 'evil', 'super evil')" +
                    ")";
            statement.execute(sql);

            sql = "CREATE TABLE minions_villains(" +
                    "villain_id INT," +
                    "minion_id INT," +
                    "PRIMARY KEY pk_villain_minion (villain_id, minion_id)," +
                    "CONSTRAINT FK_minions_villains_to_minions FOREIGN KEY (minion_id) REFERENCES minions(id)," +
                    "CONSTRAINT FK_minions_villains_to_villains FOREIGN KEY (villain_ID) REFERENCES villains(id) " +
                    ")";
            statement.execute(sql);

            sql = "INSERT INTO towns(name, country)" +
                    "VALUES ('Sofia', 'Bulgaria'), ('Haskovo', 'Bulgaria'), ('Belfast', 'UK')," +
                    "('London', 'UK'), ('Dublin', 'Ireland'), ('Copenhagen', 'Denmark'), ('Zurich', 'Switzerland')";
            statement.execute(sql);

            sql = "INSERT INTO minions(name, age, town_id)" +
                    "VALUES ('Joseph', 22, 1), ('Scott', 33, 2), ('Mark', 44, 2)," +
                    "('Catherine', 55, 4), ('Josephine', 66, 4)";
            statement.execute(sql);

            sql = "INSERT INTO villains(name, age, evilness_factor)" +
                    "VALUES ('Ivan', 22, 'super evil'), ('George', 33, 'evil'), ('Robert', 44, 'good')," +
                    "('Catherine', 55, 'super evil'), ('Josephine', 66, 'super evil')";
            statement.execute(sql);

            sql = "INSERT INTO minions_villains(villain_id, minion_id)" +
                    "VALUES (1, 2), (1, 3), (2, 2)," +
                    "(3, 1), (1, 4), (2, 1), (2, 4), (1, 5), (2, 5), (1, 1)";
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
