package TableModels;

import Models.Genre;
import Database.DbConnection;
import Database.DbManager;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class GenreTM extends AbstractTableModel {
	private final DbManager<Genre> dbManager;
	private final List<String> columnNames = Arrays.asList(
			"Name"
    );
	private List<Genre> genres;
	
	public GenreTM(DbManager<Genre> dbManager) {
		this.dbManager = new DbManager<Genre>(new DbConnection(), Genre.tableName, Genre::SetResultSetValues);	
	
		this.genres = this.dbManager.GetAll();
	}
	
	public Genre getAtRow(int row){
        return this.genres.get(row);
    }
	
	 public void refresh(List<Genre> genres) {
		 if(genres == null) {
			 this.genres = this.dbManager.GetAll();
		 }
		 else {
			 this.genres = genres;
		 }
		
	     fireTableDataChanged();
	    }
	
	@Override
	public int getRowCount() {
		return this.genres.size();
	}

	@Override
	public int getColumnCount() {
		
		return this.columnNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.genres.get(rowIndex).genreName;
	}
	
	@Override
    public String getColumnName(int i) {
        return "da";
    }
	
	@Override
    public boolean isCellEditable(int i, int i1) {
        return true;
    }
	
	 @Override
	 public void setValueAt(Object o, int row, int col) {
		 Genre genre = this.genres.get(row);
		 
		 if	(genre == null) {
			 return;
		 }
		 
		 genre.genreName = (String)o;
		 
		 this.dbManager.Update(genre.id, genre);
		 
		 fireTableDataChanged();
	}
}
