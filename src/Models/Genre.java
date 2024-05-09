package Models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Genre implements IBaseModel {
	public int id;
    public String genreName;

    public static String tableName = "Genres";
    
    public static Genre SetResultSetValues(ResultSet result) {
    	try {
    		Genre genre = new Genre();
        	
        	genre.id = result.getInt("Id");
        	genre.genreName = result.getString("Name");
            
            return genre;
    	}
    	catch(Exception ex) {
    		throw new IllegalArgumentException(ex);
    	}
    }

    @Override
    public List<String> GetFields() {
        List<String> fields = new ArrayList<>();
        fields.add("Name");
        
        return fields;
    }

    @Override
    public List<Object> GetFieldsValuesAsList() {
        List<Object> values = new ArrayList<>();
        values.add(genreName);
        
        return values;
    }
}
