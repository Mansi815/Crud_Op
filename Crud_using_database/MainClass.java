import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class MainClass {

    public static void main(String[] args) {
        try {
            Connection conn = ConnectionClass.getConnection();
            System.out.println("Connection successful!");
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
