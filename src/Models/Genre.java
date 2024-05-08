package Models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Genre implements IBaseModel {
    private int id;
    private String genreName;

    @Override
    public String GetTableName() {
        return "Genres";
    }

    @Override
    public IBaseModel SetResultSetValues(ResultSet result) throws Exception {
        this.id = result.getInt("Id");
        this.genreName = result.getString("GenreName");
        
        return this;
    }

    @Override
    public List<String> GetFields() {
        List<String> fields = new ArrayList<>();
        fields.add("Id");
        fields.add("GenreName");
        
        return fields;
    }

    @Override
    public List<Object> GetFieldsValuesAsList() {
        List<Object> values = new ArrayList<>();
        values.add(id);
        values.add(genreName);
        
        return values;
    }
}
