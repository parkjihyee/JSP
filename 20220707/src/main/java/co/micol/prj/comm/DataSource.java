package co.micol.prj.comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	private static DataSource dataSource = new DataSource(); // 인스턴스 생성
	private Connection conn;
	
	private DataSource() { // 남들이 나를 생성하지못하도록 생성자를 만듬

	} 
	
	public static DataSource getInstance() { // 생성된 인스턴스를 getInstance를 이용해서 리턴
		return dataSource;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "micol", "1234");
			System.out.println("DB 연결 성공");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패");
		}
		return conn;
	}
}
