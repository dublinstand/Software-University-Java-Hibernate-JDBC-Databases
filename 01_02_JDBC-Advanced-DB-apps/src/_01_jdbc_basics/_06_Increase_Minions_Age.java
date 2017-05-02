package _01_jdbc_basics;

//Write a program that receives ID of a villain, delete him from the database and releases his minions from serving to him.
//As an output print the name of the villain and the number of minions were released.
//Make sure all operations go as planned otherwise do not make any changes in the database.

import java.sql.*;
import java.util.Scanner;

public class _06_Increase_Minions_Age {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static void add1yearAddFirstLetterCapitalForMinionId(int minionId) throws SQLException {

        String setFirstLetterCapitalSQL = "UPDATE minions AS m\n" +
                "SET m.name = CONCAT(UCASE(LEFT(m.name, 1)), \n" +
                "                             SUBSTRING(m.name, 2))\n" +
                "WHERE m.id = " + minionId + "";

        String addOneYearToMinionSQL = "UPDATE minions AS m\n" +
                "SET m.age = m.age + 1\n" +
                "WHERE m.id = 1";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {

            connection.setAutoCommit(false);

            try {
                statement.execute(setFirstLetterCapitalSQL);
                connection.commit();
                statement.execute(addOneYearToMinionSQL);
                connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                connection.rollback();
                throw ex;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static void printMinionsNameAndAge() {
        String minionsNameAndAgeSQL = "SELECT m.name, m.age\n" +
                "FROM minions AS m";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(minionsNameAndAgeSQL)) {

            while(resultSet.next()){
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println(name + " " + age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String[] consoleInput = scanner.nextLine().split(" ");

        for (String aConsoleInput : consoleInput) {
            add1yearAddFirstLetterCapitalForMinionId(Integer.parseInt(aConsoleInput));
        }

        printMinionsNameAndAge();
    }
}


