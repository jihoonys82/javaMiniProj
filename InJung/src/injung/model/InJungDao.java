package injung.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InJungDao {
	
	private static InJungDao instance = new InJungDao();
	
	private InJungDao() {
	}
	
	public static InJungDao getInstance() {
		return instance;
	}
	
	public int insertEmployee(EmployeeDto dto) {
		int results = 0; 
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into employee value (EMP_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
//			pstmt.setInt(1, dto.getEmployeeId()); // ID is auto-increment col. 
			pstmt.setString(1, dto.getName());
			pstmt.setDate(2, dto.getBirth());
			pstmt.setString(3, dto.getTeam());
			pstmt.setString(4, dto.getLevel());
			pstmt.setString(5, dto.getRole());
			pstmt.setString(6, dto.getMobile());
			pstmt.setString(7, dto.getWorkPhone());
			pstmt.setString(8, dto.geteMail());
			pstmt.setString(9, dto.getLocation());
			pstmt.setString(10, dto.getLostIdQuestion());
			pstmt.setString(11, dto.getLostIdAnswer());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)		 pstmt.close();
				if(connection!=null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return results;
	}
	
	public EmployeeDto getEmployee(int id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from employee where employeeId=?";
		EmployeeDto dto = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dto = new EmployeeDto();
				dto.setEmployeeId(set.getInt("employeeId"));
				dto.setName(set.getString("employeeName"));
				dto.setBirth(set.getDate("birth"));
				dto.setTeam(set.getString("team"));
				dto.setLevel(set.getString("level"));
				dto.setRole(set.getString("role"));
				dto.setMobile(set.getString("mobile"));
				dto.setWorkPhone(set.getString("workphone"));
				dto.seteMail(set.getString("email"));
				dto.setLocation(set.getString("location"));
				dto.setPassword(set.getString("password"));
				dto.setPhoto(set.getString("photo"));
				dto.setLostIdQuestion(set.getString("lostIdQuestion"));
				dto.setLostIdAnswer(set.getString("lostIdAnswer"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set!=null) set.close();
				if(pstmt!=null) pstmt.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
		
	}
	
	public int countEmployee() {
		int cnt = -1;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select COUNT(employeeId) as c from employee";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				cnt = set.getInt(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set!=null) set.close();
				if(pstmt!=null) pstmt.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return  cnt;
	}
	
	
	
	private Connection getConnection() {
//		Context context = null;
//		DataSource dataSource = null;
		Connection connection = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "ji";
		String upw = "1";
		
		try {
//			context = new InitialContext();
//			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
//			connection = dataSource.getConnection();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, uid, upw);
			
//		} catch (NamingException e) {
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
		
	}
	
}
