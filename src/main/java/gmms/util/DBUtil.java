package gmms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBUtil {
	
	private static  String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static  String url = "jdbc:sqlserver://localhost:1433;databaseName=VYearFee";
	private static  String user = "xmrbi";
	private static  String password = "xmrbi3967968";
	
	public static int update(String sql, Object[] params){
		
		Connection conn = null; 
		PreparedStatement ps = null;
		int row=0;
		
		conn = getConn();
		if(conn == null){
			System.out.println("数据库连接错误");
			return 0;
		}
		try {
			// insert into book values(?,?,?)
			ps = conn.prepareStatement(sql);			
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}			
			row=ps.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			close(conn, ps, null);
		}	
		return row;
	}	
	
	@SuppressWarnings("rawtypes")
	public static List query(String sql, Object[] params){
		
		Connection conn = null; 
		PreparedStatement ps = null;
		List<Map> results = new ArrayList<Map>();
		
		conn = getConn();
		if(conn == null){
			System.out.println("数据库连接错误");
			return null;
		}
		
		try {	
			ps = conn.prepareStatement(sql);
			// 设置sql参数
			if(params != null){
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
	
			ResultSet rs = ps.executeQuery();
			// 数据库行循环
			while(rs.next()){
				
				ResultSetMetaData meta = rs.getMetaData();
				
				Map<String, Object> columns =new HashMap<String, Object>(meta.getColumnCount());
										
				// 当前行的列循环
				for(int i=1; i<=meta.getColumnCount(); i++){
					columns.put(meta.getColumnName(i),
										rs.getObject(i));					
				}
				
				results.add(columns);				
			}		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally{
			close(conn, ps, null);
		}		
		
		return results;
	}
	
	public static Connection getConn(String url){
		DBUtil.url = url;
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = 
				  DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;		 
	}
	
	private static Connection getConn(){
		
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = 
				  DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;		 
	}

	private static void close(Connection conn, 
			Statement st, ResultSet rs){
		
		if(rs != null){
			
			// alt+shift+z
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st != null){
					
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		List list = DBUtil.query(" select * from RBIRegisterInfo..bas_PersonData where PersonId=?",new Object[] {123301});
		System.out.print(list.get(0));
//		 DBUtil.update("Insert into [ProjMon_GD2014]..Vdo_ControlLog201404(VidiconId, UserID, UserName, LogSource, Remark, OperateTime)" +
//		 		"VALUES(?,?,?,?,?,?)",new Object[]{666, "33", "图片定时抓拍软件", 3, "WEB版操作摄像机", "2014-04-29 12:12:12"});
	}

}





