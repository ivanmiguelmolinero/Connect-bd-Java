import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "sqlpassword";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM nombrebasededatos.tabla_prueba;");
            System.out.println(rs);
            System.out.println("Conexión realizada con éxito");
            while (rs.next())
            {
                System.out.println (rs.getString (1) + " " + rs.getString (2)+ " " + rs.getInt(3));
            }
        } catch (SQLException e) {
            Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}