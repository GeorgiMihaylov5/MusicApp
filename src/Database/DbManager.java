package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import Models.IBaseModel;

public class DbManager<T extends IBaseModel> {
	private final DbConnection dbConnection;
	private final String tableName;
	private final Function<ResultSet, T> ConvertToItem;
	
	public DbManager(DbConnection dbConnection,
			String tableName,
			Function<ResultSet, T> ConvertToItem) {
		this.dbConnection = dbConnection;
		this.tableName = tableName;
		this.ConvertToItem = ConvertToItem;
	}
	
	public List<T> GetAll() {
		List<T> items = new ArrayList<T>();
		
		try {
			String query = "SELECT * FROM " + this.tableName;
			
			Connection con = dbConnection.getConnection();
			PreparedStatement state = con.prepareStatement(query);
			ResultSet result = state.executeQuery();
			
			while(result.next()) {
				items.add(this.ConvertToItem.apply(result));
			}
			
			con.close();
			state.close();
			result.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return items;
	}
	
	public T GetById(int id) {
		T item = null;
		
		try {
			String query = "SELECT * FROM " + this.tableName + " WHERE id=?";
			 
			Connection con = dbConnection.getConnection();
			PreparedStatement state = con.prepareStatement(query);
			
			state.setInt(1, id);
			ResultSet result = state.executeQuery();
			if(result.next()) {
				item = this.ConvertToItem.apply(result);
			}
			else {
				throw new Exception("The entry wasn't found!");
			}
			
			con.close();
			state.close();
			result.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return item;
	}
	
	public static void Execute(String query) {
		try {
			DbConnection dbConnection = new DbConnection();
			Connection con = dbConnection.getConnection();
			PreparedStatement state = con.prepareStatement(query);
						
			int result = state.executeUpdate();
			
			System.out.println(result);
	
			
			con.close();
			state.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public List<T> Search(Map<String, String> map) {
		List<T> items = new ArrayList<T>();
		
		try {
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT * FROM ")
				.append(this.tableName)
				.append(" WHERE ");				
			
			map.forEach((String key, String value) -> {
				if(value != null && !value.equals("")) {
					 sb.append(key).append(" LIKE '%")
					    .append(value)
					    .append("%' AND ");
				}		   
			});
			int sbLength = sb.length();
			sb.delete(sbLength - 4, sbLength);	
				 
			Connection con = dbConnection.getConnection();
			PreparedStatement state = con.prepareStatement(sb.toString());		
			
			ResultSet result = state.executeQuery();
			
			while(result.next()) {
				items.add(this.ConvertToItem.apply(result));
			}
			
			con.close();
			state.close();
			result.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return items;
	}
	
	public boolean Add(T item) {
		try {
			 String[] fieldsValues = item.GetFieldsValuesAsList().stream()
                     .map(obj -> obj != null ? obj.toString() : null) 
                     .toArray(String[]::new);

			String query = "INSERT INTO " + this.tableName + " (" + String.join(", ", item.GetFields()) +")" +" VALUES (" + String.join(", ", fieldsValues) + ")";
			
			Connection con = dbConnection.getConnection();
			PreparedStatement state = con.prepareStatement(query);
			int affected = state.executeUpdate();
			
			if (affected == 0) {
				return false;
			}
			
			con.close();
			state.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			
			return false;
		}
		
		return true;
	}
	
	public boolean Update(int id, T item) {
		try {
			 StringBuilder sb = new StringBuilder();
			 
			 List<String> columns = item.GetFields();
			 List<Object> values = item.GetFieldsValuesAsList();
			 
			 
			 for (int i = 0; i < columns.size(); i++) {
				 Object value = values.get(i);
				 
				 if (value instanceof String) {
					 sb.append(columns.get(i)).append(" = '").append(value).append("'");
				 }
				 else {
					 sb.append(columns.get(i)).append(" = ").append(value);
				 }
							 
				 if (i < columns.size() - 1) {
					 sb.append(", ");
				 }
			 }

			
			String query = "UPDATE " + this.tableName + " SET " + sb + " WHERE id=?";
			
			Connection con = dbConnection.getConnection();
			PreparedStatement state = con.prepareStatement(query);
			state.setInt(1, id);
			int affected = state.executeUpdate();
			
			if (affected == 0) {
				return false;
			}
			
			con.close();
			state.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			
			return false;
		}
		
		return true;
	}
	
	public boolean Delete(int id) {
		try {			
			String query = "DELETE FROM " + tableName + " WHERE id=?";
			
			Connection con = dbConnection.getConnection();
			PreparedStatement state = con.prepareStatement(query);
			state.setInt(1, id);
			int affected = state.executeUpdate();
			
			if (affected == 0) {
				return false;
			}
			
			con.close();
			state.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			
			return false;
		}
		
		return true;
	}
}
