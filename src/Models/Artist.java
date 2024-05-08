package Models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Artist implements IBaseModel {
    public int id;
    public String artistName;
    public String country;

    public static String tableName = "Artists";

    public static Artist SetResultSetValues(ResultSet result) throws Exception {
    	Artist artist = new Artist();
    	
    	artist.id = result.getInt("Id");
    	artist.artistName = result.getString("ArtistName");
    	artist.country = result.getString("Country");
        
        return artist;
    }

    @Override
    public List<String> GetFields() {
        List<String> fields = new ArrayList<>();
        fields.add("ArtistName");
        fields.add("Country");
        
        return fields;
    }

    @Override
    public List<Object> GetFieldsValuesAsList() {
        List<Object> values = new ArrayList<>();
        values.add(artistName);
        values.add(country);
        
        return values;
    }
}