package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Group {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("未找到驱动程序");
		}
		String url = "jdbc:mysql://localhost:3306/group?useUnicode=true&characterEncoding=utf8&useSSL=true"; 
		String user="root";
		String password="7758521";
		try {
			return DriverManager.getConnection(url,user,password);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	
	
	
	
	
public static void listadd(String port,String name) throws SQLException {
	Connection conn=getConnection();
	String sql ="Select * from groups where port='"+port+"'";
	Statement st = conn.createStatement();
	ResultSet res = st.executeQuery(sql);
	
	if(res.next()) {
		PreparedStatement stmt1;
		PreparedStatement psql;
        psql = conn.prepareStatement("update groups set list = ? where port = ?");
        psql.setString(2, port);
        psql.setString(1, res.getString(2)+","+name);
        psql.executeUpdate();
        psql.close();	
		}
	conn.close();
}	

public static void listdelect(String port,String name) throws SQLException {
	Connection conn=getConnection();
	String sql ="Select * from groups where port='"+port+"'";
	Statement st = conn.createStatement();
	ResultSet res = st.executeQuery(sql);
	if(res.next()) {
		PreparedStatement stmt1;
		PreparedStatement psql;
        psql = conn.prepareStatement("update groups set list = ? where port = ?");
        psql.setString(2, port);
        String delect=","+name;
        String list=res.getString(2).replace(delect,"");
        psql.setString(1, list);
        psql.executeUpdate();
        psql.close();	
		}
	conn.close();
}
public static String getlist(String port) throws SQLException {
	String i="无";
	Connection conn=getConnection();
	String sql ="Select * from groups where port='"+port+"'";
	Statement st = conn.createStatement();
	ResultSet res = st.executeQuery(sql);
	while(res.next())
	i= res.getString(2);
	return i;
}


public static void main(String[]args) throws SQLException {
	Group a=new Group();
	System.out.print(a.getlist("8878"));
	
}	
	
}
