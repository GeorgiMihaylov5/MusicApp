package Models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Song implements IBaseModel {
    public int id;
    public String title;
    public int artistId;
    public String album;
    public int genreId;
    public int releaseYear;
    public int duration;

    @Override
    public String GetTableName() {
        return "Songs";
    }

    @Override
    public IBaseModel SetResultSetValues(ResultSet result) throws Exception {
        this.id = result.getInt("Id");
        this.title = result.getString("Title");
        this.artistId = result.getInt("ArtistId");
        this.album = result.getString("Album");
        this.genreId = result.getInt("GenreId");
        this.releaseYear = result.getInt("ReleaseYear");
        this.duration = result.getInt("Duration");
       
        return this;
    }

    @Override
    public List<String> GetFields() {
        List<String> fields = new ArrayList<>();
        fields.add("Id");
        fields.add("Title");
        fields.add("ArtistId");
        fields.add("Album");
        fields.add("GenreId");
        fields.add("ReleaseYear");
        fields.add("Duration");
        
        return fields;
    }

    @Override
    public List<Object> GetFieldsValuesAsList() {
        List<Object> values = new ArrayList<>();
        values.add(id);
        values.add(title);
        values.add(artistId);
        values.add(album);
        values.add(genreId);
        values.add(releaseYear);
        values.add(duration);

        return values;
    }
}