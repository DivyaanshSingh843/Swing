import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class prectical {

    public static void main(String[] args) {
        String URL = "jdbc:sqlserver://DESKTOP-42S24CL\\DIVYAANSH;databaseName=mydatabase;encrypt=false";
        String username = "Divyaansh";
        String password = "12345678";
        String query = "select * from employee";

        try {
            Connection conn = DriverManager.getConnection(URL, username, password);
            System.out.println("connection sucessful");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String job_title = rs.getString("job_title");
                    double salary = rs.getDouble("salary");

                    System.out.println();
                    System.out.println("id " + id);
                    System.out.println("name " + name);
                    System.out.println("job_title " + job_title);
                    System.out.println("salary " + salary);}
                }catch(Exception e){

                }
        
    }

}
