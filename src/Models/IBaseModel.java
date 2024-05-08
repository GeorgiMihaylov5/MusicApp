package Models;

import java.sql.ResultSet;
import java.util.List;

public interface IBaseModel {
	public String GetTableName();
	public IBaseModel SetResultSetValues(ResultSet result) throws Exception;
	public List<String> GetFields();
	public List<Object> GetFieldsValuesAsList();
	
}
