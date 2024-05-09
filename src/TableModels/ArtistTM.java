package TableModels;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Database.DbManager;
import Models.Artist;

@SuppressWarnings("serial")
public class ArtistTM extends AbstractTableModel{
	private final DbManager<Artist> dbManager;
	private final List<String> columnNames = Arrays.asList(
			"Name", "Country"
    );
	private List<Artist> artists;
	
	public ArtistTM(DbManager<Artist> dbManager) {
		this.dbManager = dbManager;
	
		this.artists = this.dbManager.GetAll();
	}
	
	public Artist getAtRow(int row){
        return this.artists.get(row);
    }
	
	 public void refresh(List<Artist> genres) {
		 if(genres == null) {
			 this.artists = this.dbManager.GetAll();
		 }
		 else {
			 this.artists = genres;
		 }
		
	     fireTableDataChanged();
	    }
	
	@Override
	public int getRowCount() {
		return this.artists.size();
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return this.artists.get(rowIndex).artistName;
		}
		
		return this.artists.get(rowIndex).country;
	}
	
	@Override
    public String getColumnName(int i) {
        return this.columnNames.get(i);
    }
	
	@Override
    public boolean isCellEditable(int i, int i1) {
        return true;
    }
	
	 @Override
	 public void setValueAt(Object o, int row, int col) {
		 Artist artist = this.artists.get(row);
		 
		 if	(artist == null) {
			 return;
		 }
		 
		 if (col == 0) {
			 artist.artistName = (String)o;
		 }
		 else {
			 artist.country = (String)o;
		 }
		 
		 this.dbManager.Update(artist.id, artist);
		 
		 fireTableDataChanged();
	}
}
