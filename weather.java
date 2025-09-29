import java.io.*;
import java.sql.*;
import java.util.*;

public class UserUtils {
    private static String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static String DB_USER = "root";
    private static String DB_PASS = "password"; // Hardcoded password (security issue)
    
    // Returns true if user exists, false otherwise
    public static boolean userExists(String username) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement stmt = conn.createStatement();
            // SQL Injection vulnerability
            String sql = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            boolean exists = rs.next();
            rs.close();
            stmt.close();
            conn.close();
            return exists;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Reads a file and returns its contents (no resource close, bad error handling)
    public static String readFile(String filename) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            // reader.close(); // Bug: resource not closed
        } catch(IOException e) {
            // Silently ignore errors
        }
        return sb.toString();
    }

    // Poorly named method, unclear purpose
    public static int doStuff(int a, int b) {
        if(a > 100) return b / (a - 100); // possible divide by zero
        return a + b;
    }

    // Null dereference risk
    public static void printUpperCase(String s) {
        System.out.println(s.toUpperCase());
    }

    // TODO: Implement encryption (never done)
    public static String encryptPassword(String password) {
        // Not actually encrypting!
        return password;
    }
}
