import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class delete {

    public static void main(String[] args) {
        String URL = "jdbc:sqlserver://DESKTOP-42S24CL\\DIVYAANSH;databaseName=mydatabase;encrypt=false";
        String username = "Divyaansh";
        String password = "12345678";
        String query = "delete from employee where id = 2";

        try {
            Connection conn = DriverManager.getConnection(URL, username, password);
            System.out.println("connection sucessful");
            Statement stmt = conn.createStatement();
            int r = stmt.executeUpdate(query);
            if(r > 0){
                System.out.println(r + "rows effects");
            }else{
                System.out.println("deletion feild");
            }



        }catch(Exception e){
            e.printStackTrace();

        }
        

    }
    
}
