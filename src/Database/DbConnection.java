package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			String url = "jdbc:h2:~/music-app";
			
			conn = DriverManager.getConnection(url, "sa", "123123");
			
			if(conn != null) {
				System.out.println("Successfully connected!");
			}
		} catch (Exception e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		}
		
		return conn;
	}
}
