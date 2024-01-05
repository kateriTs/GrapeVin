import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import gf.Questionnaire;
import gf.TestAPI;
import gf.SQLConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {  
        System.out.println("Español");     
        Questionnaire questionnaire = new Questionnaire();
        List<String> userResponses = questionnaire.collectUserResponses();
        String request1 = "Find 5 wines that are: " + userResponses.get(1) + "and " + userResponses.get(3)
            + ".Please display them ONLY like this: name, color, flavor.";

        // Print user responses
        System.out.println("User Responses: " + userResponses);

        // Get a database connection
        Connection conn = SQLConnection.getConnection();

        // Second request useing the wines in the DB
        String winetable = getStringTable(userResponses.get(3), conn);
        String request2 = "Please select 3 of the wines below that go best with " + userResponses.get(0)
                + ", are ideal " + userResponses.get(2)
                + " and are best for " + userResponses.get(4) + "experienses."
                + "Please diplay them ONLY by their name. Here are the wines: " + winetable;

        try {
            // Use GPT-3.5 to analyze responses and get wine recommendations
            ArrayList<String> gptResponse1 = TestAPI.getWineRecommendations(request1);
            // Display the recommended wines to the user
            System.out.println("Recommended Wines:");
            System.out.println(gptResponse1);

            // Save the wines in DB
           try {
                saveWinesInDB(gptResponse1, conn);
                System.out.println("All good");
            } catch (SQLException e) {
                System.err.println("SQL error: " + e.getMessage());
            } finally {
                // Close the connection when done
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        System.err.println("Error closing connection: " + e.getMessage());
                    }
                }
            }

            // Call GPT again to select the final wines //!!ΕΔΩ ΠΕΤΑΕΙ ERROR!!
            ArrayList<String> gptResponse2 = TestAPI.getWineRecommendations(request2);
            System.out.println(gptResponse2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        // Put the wines of the gpt response in the ArrayLists
        public static void saveWinesInDB(ArrayList<String> gptWineList, Connection conn) throws SQLException {
            PreparedStatement a = null;
            for (String wine : gptWineList) {
               String[] owine = wine.split(",");
               if (owine[1].toLowerCase().contains("white".toLowerCase())){
                    putWineInTable("WhiteWines", owine, conn, a);
               } else if (owine[1].toLowerCase().contains("red".toLowerCase())){
                    putWineInTable("RedWines", owine, conn, a);
               } else if (owine[1].toLowerCase().contains("ros".toLowerCase())){
                    putWineInTable("RoseWines", owine, conn, a);
               } else {
                   System.out.println("Unknown Wine Category");
               }
            }
        }

        // Get INSERT PreparedStatement in SQL
        public static PreparedStatement makeSQLStatementInsert (String tablename, String wname, 
                            String wcolor, String wflavor, Connection conn) throws SQLException {
            String sql = " insert into " + tablename + "(w_name, w_color, w_flavor)"
            + " values (?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            //preparedStmt.setString (1, key);
            preparedStmt.setString (1, wname);
            preparedStmt.setString (2, wcolor);
            preparedStmt.setString (3, wflavor);
            return preparedStmt; 
        }

        // Get the String table from DB table
        public static String getStringTable(String userResponse, Connection conn) throws SQLException {
            if (userResponse == "White") {
                return makeSQLStatementSelect("WhiteWines", conn);
            } else if (userResponse == "Red") {
                return makeSQLStatementSelect("RedWines", conn);
            } else if (userResponse == "Rose") {
                return makeSQLStatementSelect("RoseWines", conn);
            } else {
                return null;
            }
        }
        
        // Get a String table using SELECT 
        public static String makeSQLStatementSelect(String table,  Connection conn) throws SQLException {
            String sql = "select w_name from  " + table;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
           
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set and store values in a StringBuilder
            StringBuilder resultString = new StringBuilder();
            while (resultSet.next()) {
                String columnValue = resultSet.getString("w_name");
                resultString.append(columnValue).append(", ");
            }

            // Remove the trailing comma and space
            if (resultString.length() > 0) {
                resultString.setLength(resultString.length() - 2);
            }

            return resultString.toString();
        }

        // Check if value exists in DB
        public static boolean valueExists(Connection conn, String tableName, String value) throws SQLException {
            String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE w_name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
            return false;
        }

        // Check if wine exists and then put in DB
        public static void putWineInTable(String tablename, String[] owine, Connection conn, PreparedStatement a) throws SQLException {
            boolean yn = valueExists(conn, tablename, owine[0]);
            if (yn == false){
                a = makeSQLStatementInsert(tablename, owine[0], owine[1], owine[2], conn);
                a.executeUpdate();
            } else if (yn == true){
                System.out.println("Wine " + owine[1] + " allready exists");
            }
        }
    }