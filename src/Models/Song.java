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
    public double duration;

    public static String tableName = "Songs";

    public static Song SetResultSetValues(ResultSet result) {
    	try {
    		Song song = new Song();
        	
        	song.id = result.getInt("Id");
        	song.title = result.getString("Title");
        	song.artistId = result.getInt("ArtistId");
        	song.album = result.getString("Album");
        	song.genreId = result.getInt("GenreId");
        	song.releaseYear = result.getInt("ReleaseYear");
        	song.duration = result.getInt("Duration");
           
            return song;
    	}
    	catch(Exception ex) {
    		throw new IllegalArgumentException(ex);
    	}
    }

    @Override
    public List<String> GetFields() {
        List<String> fields = new ArrayList<>();
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
        values.add(title);
        values.add(artistId);
        values.add(album);
        values.add(genreId);
        values.add(releaseYear);
        values.add(duration);

        return values;
    }
}