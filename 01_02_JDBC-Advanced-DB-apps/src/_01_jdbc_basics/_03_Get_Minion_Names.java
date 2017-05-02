package _01_jdbc_basics;

//Write a program that prints on the console all villains’ names and their number of minions of
// those who has more than 3 minions ordered descending by number of minions.


import java.sql.*;

public class _03_Get_Minion_Names {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static void getMinionNames(int villainID) {
        String minionsSQL = "SELECT m.name AS name, m.age AS age\n" +
                "FROM minions_villains AS mv\n" +
                "JOIN minions AS m\n" +
                "ON m.id = mv.minion_id\n" +
                "WHERE mv.villain_id = " + villainID;

        String villainSQL = "SELECT v.name\n" +
                "FROM villains AS v\n" +
                "WHERE v.id = " + villainID;

        //to verify if the villain exist and print the relative message
        boolean villainDoesNotExist = true;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSetVillain = statement.executeQuery(villainSQL);
        ) {
            while (resultSetVillain.next()) {
                villainDoesNotExist = false;
                String villainName = resultSetVillain.getString("name");
                System.out.println("Villain: " + villainName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (villainDoesNotExist) {
            System.out.println(String.format("No villain with ID %d exists in the database.", villainID));
        }

        if (!villainDoesNotExist) {


            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement();
                 ResultSet resultSetMinion = statement.executeQuery(minionsSQL);
            ) {
                //count the number of rows in the while loop
                int count = 0;

                //if the program does not get into the while loop, ResultSet is empty and we print
                boolean isEmpty = true;

                while (resultSetMinion.next()) {

                    String minionName = resultSetMinion.getString("name");
                    int age = resultSetMinion.getInt("age");

                    count = count + 1;

                    isEmpty = false;

                    System.out.println(String.format("%d. %s %d", count, minionName, age));
                }

                //we print text if the ResultSet is empty
                if (isEmpty) {
                    System.out.println("<no minions>");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        getMinionNames(1);
    }
}
