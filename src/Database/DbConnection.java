package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			String url = "jdbc:h2:tcp://localhost/~/test";
			
			conn = DriverManager.getConnection(url, "sa", "");
			
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
