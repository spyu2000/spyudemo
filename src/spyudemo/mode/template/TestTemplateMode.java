package spyudemo.mode.template;

import java.sql.ResultSet;

public class TestTemplateMode {

	public static <T> void main(String[] args){
		
		String result= JdbcTemplate.query("select * from test", new RsHandler<String>() {
			@Override
			public String getResult(ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
