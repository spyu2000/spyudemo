package spyudemo.mode.template;

import java.sql.ResultSet;

public interface RsHandler<T> {

	public T getResult(ResultSet rs);
	
}
