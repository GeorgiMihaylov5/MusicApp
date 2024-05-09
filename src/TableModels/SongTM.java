package TableModels;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Database.DbManager;
import Models.Song;
import Models.Artist;
import Models.Genre;

@SuppressWarnings("serial")
public class SongTM extends AbstractTableModel{
	private final DbManager<Song> songManager;
	private final DbManager<Artist> artistManager;
	private final DbManager<Genre> genreManager;
	
	private final List<String> columnNames = Arrays.asList(
			"Title", "ArtistName", "Album", "GenreName", "ReleaseYear", "Duration"
    );
	private List<Song> songs;
	
	public SongTM(DbManager<Song> songManager, DbManager<Artist> artistManager, DbManager<Genre> genreManager) {
		this.songManager = songManager;
		this.artistManager = artistManager;
		this.genreManager = genreManager;
		
		this.songs = this.songManager.GetAll();
	}
	
	public Song getAtRow(int row){
        return this.songs.get(row);
    }
	
	 public void refresh(List<Song> genres) {
		 if(genres == null) {
			 this.songs = this.songManager.GetAll();
		 }
		 else {
			 this.songs = genres;
		 }
		
	     fireTableDataChanged();
	 }
	 
	
	
	@Override
	public int getRowCount() {
		return this.songs.size();
	}

	@Override
	public int getColumnCount() {
		
		return this.columnNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Song song = this.songs.get(rowIndex);
		
		switch(columnIndex) {
		case 0: return song.title;
		case 1: {
			Artist artist = this.artistManager.GetById(song.artistId);
			if(artist == null) {
				return "";
			}
			
			return artist.artistName;
		}
		case 2: return song.album;
		case 3: {
			Genre genre = this.genreManager.GetById(song.genreId);
			if(genre == null) {
				return "";
			}
			return genre.genreName;
		}
		case 4: return song.releaseYear;
		case 5: return song.duration;
		}
		
		
		return this.songs.get(rowIndex).album;
	}
	
	@Override
    public String getColumnName(int i) {
        return this.columnNames.get(i);
    }
	
	@Override
    public boolean isCellEditable(int i, int i1) {
		if (i1 == 1 || i1 == 3) {
			return false;
		}
		
        return true;
    }
	
	 @Override
	 public void setValueAt(Object o, int row, int col) {
		 Song song = this.songs.get(row);
		 
		 if	(song == null) {
			 return;
		 }
		 
		 switch(col) {
			case 0: 
				song.title = (String)o;
				break;
			case 1: 
				int artistId = (int)o;
				
				if (song.artistId == artistId) {
					return;
				}
				song.artistId = artistId;
				break;
			case 2: 
				song.album = (String)o;
				break;
			case 3: 
				int genreId = (int)o;
				
				if (song.genreId == genreId) {
					return;
				}
				song.genreId = genreId;
				break;
			case 4: 
				song.releaseYear = Integer.parseInt((String)o);
			break;
			case 5: 
				song.duration = Double.parseDouble((String)o);
				break;
			default:
				 return;
			}
		 
		 
		 this.songManager.Update(song.id, song);
		 
		 fireTableDataChanged();
	}
}
