import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class update {

    public static void main(String[] args) {
       String URL = "jdbc:sqlserver://DESKTOP-42S24CL\\DIVYAANSH;databaseName=mydatabase;encrypt=false";
        String username = "Divyaansh";
        String password = "12345678";
        String query = "update employee set name = 'Divyansh' where id = 1";

        try {
            Connection conn = DriverManager.getConnection(URL, username, password);
            System.out.println("connection sucessful");
            Statement stmt = conn.createStatement();
            int r = stmt.executeUpdate(query);
            if(r > 0){
                System.out.println(r + "rows effects");
            }else{
                System.out.println("updation feild");
            }



        }catch(Exception e){
            e.printStackTrace();

        }
    }
    
}
