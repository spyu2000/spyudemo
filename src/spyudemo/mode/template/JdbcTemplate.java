package spyudemo.mode.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {
	
	static{
		try {
			//װ����������
			Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e) {
			System.out.println("װ�������������쳣!�������");
			e.printStackTrace();
			}
	}	
	public static <T>T query(String sql,RsHandler<T> handler){
		Connection conn=null;		
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
			Statement stmt=conn.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
			return handler.getResult(rs);			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{			
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
