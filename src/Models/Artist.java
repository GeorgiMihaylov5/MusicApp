package Models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Artist implements IBaseModel {
    public int id;
    public String artistName;
    public String country;

    @Override
    public String GetTableName() {
        return "Artists";
    }

    @Override
    public IBaseModel SetResultSetValues(ResultSet result) throws Exception {
        this.id = result.getInt("Id");
        this.artistName = result.getString("ArtistName");
        this.country = result.getString("Country");
        
        return this;
    }

    @Override
    public List<String> GetFields() {
        List<String> fields = new ArrayList<>();
        fields.add("Id");
        fields.add("ArtistName");
        fields.add("Country");
        
        return fields;
    }

    @Override
    public List<Object> GetFieldsValuesAsList() {
        List<Object> values = new ArrayList<>();
        values.add(id);
        values.add(artistName);
        values.add(country);
        
        return values;
    }
}