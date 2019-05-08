package pure_engine;
import java.sql.*;

/**
 *
 * @author marcelrg
 */
public class DataBaseConnect {
    public static void main(String[] args){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test_db","root","");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from users");
            while(rs.next()){
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(1));
                System.out.println(rs.getString("--------------"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
