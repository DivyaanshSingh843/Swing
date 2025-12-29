import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conn {
    public static void main(String[] args) {
        String URL = "jdbc:sqlserver://DESKTOP-42S24CL\\DIVYAANSH;databaseName=mydatabase;encrypt=false";
        String username = "Divyaansh";
        String password = "12345678";
        String insert = 
        "insert int employee(id, name, job_title, salary) values (2, 'Rohit', 'Developer', 40000)";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver loaded");
            
            try {
                Connection con = DriverManager.getConnection(URL, username, password);
                System.out.println("Connection established successfully");
                Statement stmt = con.createStatement();
                int r = stmt.executeUpdate(insert);
                if(r > 0){
                    System.out.println("insertion sucessfull " + r + "rows effects");
                }else{
                    System.out.println("insertion field");
                }
                // Statement stmt = con.createStatement();
                // String query = "select * from employee";
                // ResultSet rs = stmt.executeQuery(query);

                // while (rs.next()) {
                //     int id = rs.getInt("id");
                //     String name = rs.getString("name");
                //     String job_title = rs.getString("job_title");
                //     double salary = rs.getDouble("salary");

                //     System.out.println();
                //     System.out.println("id " + id);
                //     System.out.println("name " + name);
                //     System.out.println("job_title " + job_title);
                //     System.out.println("salary " + salary);}
                }catch(Exception e){

                }
            }catch (Exception e) {
            System.err.println("JDBC driver not found: " + e.getMessage());
        }
    }
}