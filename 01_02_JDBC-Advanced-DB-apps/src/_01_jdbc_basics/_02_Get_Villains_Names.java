package _01_jdbc_basics;

//Write a program that prints on the console all villains’ names and their number of minions of
// those who has more than 3 minions ordered descending by number of minions.


import java.sql.*;

public class _02_Get_Villains_Names {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";

    private static final String USER = "root";

    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        String sql = "SELECT v.name AS name, COUNT(mv.minion_id) AS minions_count\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv\n" +
                "ON v.id = mv.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING COUNT(mv.minion_id) > 3\n" +
                "ORDER BY COUNT(mv.minion_id) DESC";

        //here we are going to use Try with resources. All resources are entered before the {} part so that they will
        //be closed automatically
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {

            while (resultSet.next()){
                String name = resultSet.getString("name");
                int num = resultSet.getInt("minions_count");
                System.out.println(String.format("%s %d", name, num));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
