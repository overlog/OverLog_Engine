import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.plaf.nimbus.State;
import java.sql.*;


public class DatabaseController {

    Connection db_connextion;
    final String db_name = "overlog";
    final String db_url = "jdbc:postgresql://localhost:5432/" + db_name;
    final String db_username = "postgres";
    final String db_passwd = "******";



    DatabaseController(){
        try {
            db_connextion = DriverManager.getConnection(db_url, db_username, db_passwd);

        }
        catch (SQLException e){
            System.out.println(e);
        }


    }

    public void insert_log(String type, String text){

        try {
            Statement statement = db_connextion.createStatement();

            String values = "'" + type + "'" + ", " + "'" + text + "'";
            String query = "INSERT INTO log(type, text) VALUES(" + values  + ")";
            System.out.println(query);
            statement.executeUpdate(query);

        }

        catch (Exception e){
            e.printStackTrace();

        }


    }



}
