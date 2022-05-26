import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
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

        ArrayList dbContent = new ArrayList<>();
        ArrayList lsColumnName = new ArrayList<>();
        ArrayList dbToJsonContent = new ArrayList<>();

        int aux = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM nombrebasededatos.tabla_prueba;");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rs);
            System.out.println("Conexión realizada con éxito");
            while (rs.next())
            {
                System.out.println (rs.getString(1) + " " + rs.getString (2)+ " " + rs.getInt(3));
                System.out.println(rsmd.getColumnName(rs.getRow()));
                dbContent.add((rs.getString(1) + " " + rs.getString (2)+ " " + rs.getInt(3)).toString());
                lsColumnName.add(rsmd.getColumnName(rs.getRow()));
            }
            System.out.println(dbContent);
            for (int i = 0; i < dbContent.size(); i++) {
                Hashtable<String, String> myDict = new Hashtable<String, String>();
                String[] dataContent = dbContent.get(i).toString().split(" ");
                for (int j = 0; j < lsColumnName.size(); j++) {
                    System.out.println(lsColumnName.get(j));
                    System.out.println(dataContent[j]);
                    myDict.put(lsColumnName.get(j).toString(), dataContent[j]);
                    if (j == lsColumnName.size() - 1) {
                        System.out.println(myDict);
                        dbToJsonContent.add(myDict);

                        System.out.println(dbToJsonContent);
                    }
                }
            }
            System.out.println(dbToJsonContent);
        } catch (SQLException e) {
            Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}