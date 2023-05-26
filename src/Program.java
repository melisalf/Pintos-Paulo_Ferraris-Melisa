
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Program {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }


}