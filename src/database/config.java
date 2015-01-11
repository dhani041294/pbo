package database;
import java.sql.*;
public class config {
	private static Connection con;
	
	public static Connection getCon() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(con==null){
			try{
				String username = "dhani";
				String password = "123";
				Class.forName("com.mckoi.JDBCDriver").newInstance();
				String url = "jdbc:mckoi:";
				con = java.sql.DriverManager.getConnection(url,username, password);
			}
			catch (SQLException e){
				System.out.println(
						"Unable to make a connection to the database.\n" +
						"The reason : " + e.getMessage());
				}
		}return con;
	}
}
