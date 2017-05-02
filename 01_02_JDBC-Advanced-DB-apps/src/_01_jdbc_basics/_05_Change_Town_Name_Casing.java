package _01_jdbc_basics;

//Write a program that change all town names to uppercase for towns for given country.
//Print the number of towns that were changed in the format provided in examples.
//On the next line print those names that were changed separated with coma and space.

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _05_Change_Town_Name_Casing {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static void toUpperTownNamesInCountry(String countryName) throws SQLException {
        String townNamesToUpperSQL = "UPDATE towns AS t\n" +
                "SET t.name = UPPER(t.name)\n" +
                "WHERE t.country = '" + countryName + "'";

        String countOfTownsPerCountry = "SELECT COUNT(*) AS total\n" +
                "FROM towns AS t\n" +
                "WHERE t.country = '" + countryName + "'";

        String townsForCountry = "SELECT t.name\n" +
                "FROM towns AS t\n" +
                "WHERE t.country = '" + countryName + "'";

        //here we set autocommit to false run the query then set it back to true. the connection is still open in the main try part
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            //we set the autocommit to false
            connection.setAutoCommit(false);
            boolean isCountryInTheDatabase = false;

            try (ResultSet resultSet = statement.executeQuery(townsForCountry)){
                while (resultSet.next()) {
                    String townNames = resultSet.getString("name");
                    isCountryInTheDatabase = true;
                }
            }catch (SQLException ex) {
                ex.printStackTrace();
                throw ex;
            }

            if (!isCountryInTheDatabase){
                System.out.println("No town names were affected.");
                return;
            }

            //here we run our update query if it fails we roll back
            try {
                //both methods cause SQL Exception
                statement.execute(townNamesToUpperSQL);
                connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                connection.rollback();
                throw ex;
            }
            //we set autocommit to true for future actions in the main try part
            finally {
                connection.setAutoCommit(true);
            }

            //print the numbers that were changed
            try (ResultSet resultSet = statement.executeQuery(countOfTownsPerCountry)) {
                while (resultSet.next()) {
                    int numberOfTownsChanged = resultSet.getInt("total");
                    System.out.println(String.format("%s town names were affected", numberOfTownsChanged));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw ex;
            }

            List<String> townName = new ArrayList<>();

            //print all towns changed
            try (ResultSet resultSet = statement.executeQuery(townsForCountry)) {
                while (resultSet.next()) {
                    String townNames = resultSet.getString("name");
                    townName.add(townNames);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw ex;
            }

            System.out.println(townName);


        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String countryName = scanner.next();
        toUpperTownNamesInCountry(countryName);
    }
}


