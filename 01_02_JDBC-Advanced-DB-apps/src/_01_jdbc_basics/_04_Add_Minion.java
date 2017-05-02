package _01_jdbc_basics;

//Write a program that reads information about minion and its villain and add it to the database.
//In case the town of the minion is not in the database insert it as well.
//In case the villain is not present in the database add him too with default evilness factor of “evil”.
//Finally set the new minion to be servant of the villain and villain. Print appropriate messages after each operation.
//*Bonus task: Make sure all operations are executed successfully. In case of an error do not change the database.

import java.util.Scanner;
import java.sql.*;

public class _04_Add_Minion {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static void createTownIfDoesNotExist(String townName) {
        boolean townExists = false;

        String verifyTownExistsSQL = "SELECT *\n" +
                "FROM towns\n" +
                "WHERE name = '" + townName + "'";

        String createNewTownSQL = "INSERT INTO towns(name, country)" +
                "VALUES ('" + townName + "', 'Unknown')";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(verifyTownExistsSQL);) {
            while (resultSet.next()) {
                townExists = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!townExists) {
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement();) {

                statement.execute(createNewTownSQL);
                System.out.println(String.format("Town %s was added to the database.", townName));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


    private static void createVillainIfDoesNotExist(String villainName) {
        boolean villainExists = false;

        String verifyVillainExistSQL = "SELECT *\n" +
                "FROM villains\n" +
                "WHERE name = '" + villainName + "'";

        String createNewVillainSQL = "INSERT INTO villains(name, age, evilness_factor)" +
                "VALUES ('" + villainName + "', 22, 'evil')";


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(verifyVillainExistSQL)) {

            while (resultSet.next()) {
                villainExists = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!villainExists) {
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement();) {

                statement.execute(createNewVillainSQL);
                System.out.println(String.format("Villain %s was added to the database.", villainName));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createNewMinion(String name, int age, String town) {

        //get the townID
        int townID = 0;
        String townIDSQL = "SELECT id\n" +
                "FROM towns\n" +
                "WHERE name = '" + town + "'";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(townIDSQL)) {

            while (resultSet.next()) {
                townID = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String createNewMinionSQL = "INSERT INTO minions(name, age, town_id)" +
                "VALUES ('" + name + "', " + age + ", " + townID + ")";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {

            statement.execute(createNewMinionSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void addMinionToVillain(String minionName, String villainName) {
        int minionID = 0;
        int villainID = 0;

        String minionIdSQL = "SELECT *\n" +
                "FROM minions\n" +
                "WHERE name = '" + minionName +"'";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(minionIdSQL)) {

            while (resultSet.next()) {
                minionID = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String villainIdSql = "SELECT *\n" +
                "FROM villains\n" +
                "WHERE name = '" + villainName +"'";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(villainIdSql)) {

            while (resultSet.next()) {
                villainID = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String createMinionVillainSQL = "INSERT INTO minions_villains(villain_id, minion_id)" +
                "VALUES (" + villainID + ", " + minionID + ")";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {

            statement.execute(createMinionVillainSQL);
            System.out.println(String.format("Successfully added %s to be minion of %s",minionName, villainName));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] minionsData = scanner.nextLine().split(" ");

        String minionName = minionsData[1];
        int minionAge = Integer.parseInt(minionsData[2]);
        String minionTown = minionsData[3];

        String[] villainData = scanner.nextLine().split(" ");
        String villainName = villainData[1];

        createTownIfDoesNotExist(minionTown);
        createVillainIfDoesNotExist(villainName);
        createNewMinion(minionName, minionAge, minionTown);
        addMinionToVillain(minionName, villainName);

    }
}
