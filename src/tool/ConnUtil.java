package tool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("δ�ҵ���������");
		}
		String url = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=true"; 
		String user="root";
		String password="13559411101lu";
		try {
			return DriverManager.getConnection(url,user,password);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	//public static void main(String[] args)  {
		//Connection con = ConnUtil.getConnection();
		//try {
		//	System.out.println("���ݿ������Ƿ�رգ�"+con.isClosed());
		//}catch(SQLException e) {
		//	e.printStackTrace();
		//}
		//finally {
	//		if(con!=null) {
		//		try {
		//			con.close();
		//		}catch (SQLException e) {
		//			e.printStackTrace();
		//		}
		//	}
		//}
	//}

}
