import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;                  //Importing sql abilities
import java.util.Scanner;           //Importing scan abilities

/**
 *
 * @author sqlitetutorial.net
 */
public class Connect {
    /**
     * Connect to a sample database
     */
    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/db/NbaTeams.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn = null;
        //Note: everything is within this single try, so if the code break the program will stop
        try {
            String url = "jdbc:sqlite:C:/db/NbaTeams.db";
            System.out.println(url);
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            Statement s = null;
            s = conn.createStatement();
            String sql = "SELECT * FROM Team";
            ResultSet rs = s.executeQuery(sql);
            String TeamId = rs.getString("TeamId");
            String TeamName = rs.getString("TeamName");
            String Venue = rs.getString("Venue");
            int Wins = rs.getInt("Wins");
            int Losses = rs.getInt("Losses");
            System.out.println("TeamId: " + TeamId + ", TeamName: " + TeamName + ", Venue: " + Venue + ", Wins: " + Wins + ", Losses: " + Losses);
            //System.out.println("TeamId: " + rs.getString(1)+ ", TeamName: " +rs.getString(2)+ ", Venue: " +rs.getString(3)+ ", Wins: " +rs.getInt(4)+ ", Losses: " +rs.getInt(5));
            //Note: Both the above print the same thing
            System.out.println("Welcome to the Nba Teams database application:\n");

            System.out.print("1) Find all teams with wins greter than 50 and losses less than 32\n");
            System.out.print("2) Return Teams best record to worst\n");
            System.out.print("3) Find all players that do not have a position of PG, SG, and SF\n");
            System.out.print("4) Check if a player is in my Player table\n");
            System.out.print("5) Return all players with the same position as Devin Booker\n");
            System.out.print("6) Find Coaches with more than 1 year and less than 7 years of experience of coaching\n");
            System.out.print("7) Find which players have a hometown of Ohio and plays point guard\n");
            System.out.print("8) Find all teams with losses greater than the average losses amongst the teams\n");
            System.out.print("9) Update an individual  teams record\n");
            System.out.print("10) Find the Max Losses in the Team table\n");
            System.out.print("11) Return all the players who hold a position of PG and have played more than 80 games\n");
            System.out.print("12) Return Team Table\n");
            System.out.print("13) Return Player Table\n");
            System.out.print("14) Return Coach Table\n");
            System.out.print("15) Return Manager Table\n");
            System.out.print("15) Return Statistics Table\n");

                int triforce = 1;
                while (triforce != 0) {
                //START SCANNER
                Scanner scan = new Scanner(System.in);//Create an object for input from keyboard
                System.out.println("Enter the number(0 to exit): ");
                triforce = scan.nextInt();//Read and store the integer entered
                //END SCANNER
                //Note: This creates a restriction on inputs to integers only)
                ResultSet resultSet;
                switch (triforce) {
                    case 0:
                        break;
                    case 1:
                        resultSet = s.executeQuery("SELECT * FROM Team WHERE Wins > 50 AND Losses < 32");
                        ResultSetMetaData rsmd = resultSet.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 2:
                        resultSet = s.executeQuery("SELECT * FROM Team ORDER BY Losses");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 3:
                        resultSet = s.executeQuery("SELECT * FROM Player WHERE Position <> 'PG' AND Position <> 'SG' AND Position <> 'SF'");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 4:
                        resultSet = s.executeQuery("SELECT * FROM Player WHERE PlayerName = 'Andre Drummond'");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 5:
                        resultSet = s.executeQuery("SELECT Player_Other.PlayerName FROM Player AS Player_DevinBooker, Player AS Player_Other WHERE Player_DevinBooker.PlayerName = 'Devin Booker' AND Player_DevinBooker.Position = Player_Other.Position");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 6:
                        resultSet = s.executeQuery("SELECT * FROM Manager WHERE Experience >= 2 AND Experience <= 6");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 7:
                        resultSet = s.executeQuery("SELECT * FROM Player WHERE Hometown = 'Ohio' AND Position = 'PG'");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 8:
                        resultSet = s.executeQuery("SELECT * FROM Team WHERE Losses > (SELECT AVG(Losses) FROM Team)");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 9:
                        resultSet = s.executeQuery("UPDATE Team SET Wins = 33,Losses = 49 WHERE TeamName = 'Dallas Mavericks'");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 10:
                        resultSet = s.executeQuery("SELECT TeamName FROM Team WHERE Losses = (SELECT MAX(Losses) FROM Team)");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 11:
                        resultSet = s.executeQuery("SELECT * FROM Player WHERE Position = 'PG' AND GamesPlayed > 80");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 12:
                        resultSet = s.executeQuery("SELECT * FROM Team");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 13:
                        resultSet = s.executeQuery("SELECT * FROM Player");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 14:
                        resultSet = s.executeQuery("SELECT * FROM Coach");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 15:resultSet = s.executeQuery("SELECT * FROM Manager");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    case 16: resultSet = s.executeQuery("SELECT * FROM Statistics");
                        rsmd = resultSet.getMetaData();
                        columnsNumber = rsmd.getColumnCount();
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = resultSet.getString(i);
                                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            }
                            System.out.println("");
                        }
                        break;
                    default:
                        System.out.print("Mission failed we'll get 'em next time");
                }//end Case/Break
            }//end while loop
            s.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }//end finally
    }//End Main
}//Close class