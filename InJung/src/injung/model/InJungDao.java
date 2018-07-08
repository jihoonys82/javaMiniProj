package injung.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object for InJung a InJung.
 * 
 * @since 2018-07-06
 * @author Jihoon Jeong
 *
 */
public class InJungDao {
	
	public static final int INSERT_DATA_SUCCESS = 0;
	public static final int INSERT_DATA_FAILED  = 1;
	
	private static InJungDao instance = new InJungDao();
	
	private InJungDao() {
	}
	
	/**
	 * Get InJungDao instance
	 * Because InJungDao constructor is declared to private, Can't use new InJungDao(). <br>
	 * If you want to use InJungDao, call getInstance() method.<br>
	 * Further information, refer to <b>Singleton Pattern</b> 
	 * @return
	 */
	public static InJungDao getInstance() {
		return instance;
	}
	
	/**
	 * Insert Employee data to Database
	 * @param dto
	 * @return  result code 1:Success, 0: Fail to insert  
	 */
	public int insertEmployee(EmployeeDto dto) {
		int results = INSERT_DATA_FAILED; 
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into employee value (EMP_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
//			pstmt.setInt(1, dto.getEmployeeId()); // ID is auto-increment col. 
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBirth());
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
			
			results = INSERT_DATA_SUCCESS; 
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
	
	/**
	 * Get one employee data
	 * @param id - employeeId
	 * @return - EmployeeDto
	 */
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
				dto.setBirth(set.getString("birthdate"));
				dto.setTeam(set.getString("team"));
				dto.setLevel(set.getString("employlevel"));
				dto.setRole(set.getString("role"));
				dto.setMobile(set.getString("mobile"));
				dto.setWorkPhone(set.getString("workphone"));
				dto.seteMail(set.getString("email"));
				dto.setLocation(set.getString("location"));
				dto.setPassword(set.getString("password"));
				dto.setPhoto(set.getString("photopath"));
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
	
	/**
	 * Get multiple employees 
	 * get employees from the next of lastEmpId
	 * @param lastEmpId - previous last employeeNum 
	 * @param idx - the number of employee get from database  
	 * @return
	 */
	public ArrayList<EmployeeDto> getEmployees(int lastEmpId, int idx) {		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from "
				+ "(SELECT * FROM employee "
				+ "WHERE employeeId > ? )"
				+ "where rownum <= ?"; // Sub Query
		ArrayList<EmployeeDto> dtos = new ArrayList<>();
		EmployeeDto dto = new EmployeeDto();
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, lastEmpId);
			pstmt.setInt(2, idx);
			set = pstmt.executeQuery();
			
			while(set.next()) {
				dto.setEmployeeId(set.getInt("employeeId"));
				dto.setName(set.getString("employeeName"));
				dto.setBirth(set.getString("birthdate"));
				dto.setTeam(set.getString("team"));
				dto.setLevel(set.getString("employlevel"));
				dto.setRole(set.getString("role"));
				dto.setMobile(set.getString("mobile"));
				dto.setWorkPhone(set.getString("workphone"));
				dto.seteMail(set.getString("email"));
				dto.setLocation(set.getString("location"));
				dto.setPassword(set.getString("password"));
				dto.setPhoto(set.getString("photopath"));
				dto.setLostIdQuestion(set.getString("lostIdQuestion"));
				dto.setLostIdAnswer(set.getString("lostIdAnswer"));
				dtos.add(dto);
			}
			
			// if data is not exist, add dump employee data. 
			for(int i=0;i<idx;i++) {
				if(dtos.size()<i) {
					dtos.add(new EmployeeDto());
				}
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
		
		return dtos;
	}
	
	/**
	 * Get all employee data.
	 * @return - All employee Data as ArrayList
	 */
	public ArrayList<EmployeeDto> getAllEmployee() {		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from employee";
		ArrayList<EmployeeDto> dtos = new ArrayList<>();
		EmployeeDto dto = new EmployeeDto();
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			set = pstmt.executeQuery();
			
			while(set.next()) {
				dto.setEmployeeId(set.getInt("employeeId"));
				dto.setName(set.getString("employeeName"));
				dto.setBirth(set.getString("birthdate"));
				dto.setTeam(set.getString("team"));
				dto.setLevel(set.getString("employlevel"));
				dto.setRole(set.getString("role"));
				dto.setMobile(set.getString("mobile"));
				dto.setWorkPhone(set.getString("workphone"));
				dto.seteMail(set.getString("email"));
				dto.setLocation(set.getString("location"));
				dto.setPassword(set.getString("password"));
				dto.setPhoto(set.getString("photopath"));
				dto.setLostIdQuestion(set.getString("lostIdQuestion"));
				dto.setLostIdAnswer(set.getString("lostIdAnswer"));
				dtos.add(dto);
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
		
		return dtos;
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
				cnt = set.getInt("c");
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
	
	/**
	 * Insert Team Data in Database
	 * @param dto
	 * @return result code 1:Success, 0: Fail to insert
	 */
	public int insertTeam(TeamDto dto) {
		int results = INSERT_DATA_FAILED;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into team value (?,?,?)";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query); 
			pstmt.setString(1, dto.getTeamName());
			pstmt.setString(2, dto.getTeamRole());
			pstmt.setString(3, dto.getTeamLeaderId());
			pstmt.executeUpdate();
			
			results = INSERT_DATA_SUCCESS; 
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
	
	/**
	 * Get all team list with team leader name.
	 * @return ArrayList
	 */
	public ArrayList<TeamDto> getAllTeam(){
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select teamname, teamrole, teamleaderId, employeeName from team T, employee E "
				+ "where T.teamleaderId = E.employeeId"; // EQUI JOIN 
		ArrayList<TeamDto> dtos = new ArrayList<>();		
		TeamDto dto = new TeamDto();
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			set = pstmt.executeQuery();
			
			while(set.next()) {
				dto.setTeamName(set.getString("teamName"));
				dto.setTeamRole(set.getString("teamRole"));
				dto.setTeamLeaderId(set.getString("teamLeaderId"));
				dto.setTeamLeaderName(set.getString("employeeName"));

				dtos.add(dto);
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
		return dtos;
	}
	
	
	private Connection getConnection() {
		Connection connection = null;
		
		// JDBC connection information.
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "ji";
		String upw = "1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, uid, upw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;	
	}
}
