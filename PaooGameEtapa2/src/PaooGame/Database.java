package PaooGame;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    //private static String path =  ConnectToDatabase.class.getResource("/database/").getPath() + "database.db";

    public static Connection connect(){
        Connection conn = null;

        String url = "jdbc:sqlite:database.db";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

        }   catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
    public static void createNewTable() {
        Connection conn = connect();
        Statement statement;

        String sql = "CREATE TABLE IF NOT EXISTS players" +
                "(ID INTEGER PRIMARY KEY, " +
                "USERNAME   TEXT    NOT NULL, " +
                "TIME   STRING," +
                "SCORE INTEGER); "
                + "";

        try {
            statement = conn.createStatement();
            statement.execute(sql);
            statement.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void InsertData(String username, String time, int score){

        //String sql = "INSERT INTO players(USERNAME, TIME, SCORE) VALUES (?, ?, ?)";
        //String sql = "INSERT INTO players(USERNAME, TIME, SCORE) VALUES ('awdasd', 'awdas', 4)";

        try {
            Connection conn = connect();
            String sql = "INSERT INTO players(USERNAME, TIME, SCORE) VALUES ('" + username + "', '" + time + "', " + score + ")";

            createNewTable();

            /*PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, refLink.username);
            pstmt.setString(2, refLink.GetHero().minutes + ":" + refLink.GetHero().seconds);
            pstmt.setInt(3, refLink.GetHero().SCORE);

            pstmt.executeUpdate();*/

            Statement stmt = conn.createStatement();

            stmt.execute(sql);


        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<String> selectAll(String table) {
        String sql = "SELECT * FROM " + table + " ORDER BY TIME ASC, SCORE DESC;";

        Connection conn = connect();
        ArrayList<String> inputs = new ArrayList<>();
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            while(rs.next()){
                int ID = rs.getInt("ID");
                String username = rs.getString("USERNAME");
                String time = rs.getString("TIME");
                int score = rs.getInt("SCORE");
                /*String dbInput = rs.getInt("ID") + "\t" +
                        rs.getString("USERNAME") + "\t" +
                        rs.getString("TIME") + "\t" +
                        rs.getInt("SCORE"); */

                //String all = ID + " \t\t\t " + username + " \t\t\t " + time + " \t\t\t " + score;
                String all = ID + "          " + username + "          " + time + "          " + score;
                inputs.add(all);
            }
        }
        catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return inputs;
    }


}
