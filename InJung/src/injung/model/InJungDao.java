package injung.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Data Access Object for InJung a InJung.
 * 
 * @since 2018-07-06
 * @author Jihoon Jeong
 *
 */
public class InJungDao {
	
	public static final int INSERT_DATA_SUCCESS = 1;
	public static final int INSERT_DATA_FAILED  = 0;
	public static final int DELETE_DATA_SUCCESS = 1;
	public static final int DELETE_DATA_FAILED  = 0;
	
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
		String query = "INSERT INTO employee("
				+ " employeeId, employeeName, birthDate, team, employLevel, role, mobile, workPhone, email, location, password, photoPath, lostIdQuestion, lostIdAnswer) "
				+ " VALUES (EMP_SEQ.NEXTVAL,"
				+ " ?," //1. employeeName
				+ " ?," //2. birthDate
				+ " ?," //3. team
				+ " ?," //4. employLevel
				+ " ?," //5. role
				+ " ?," //6. mobile
				+ " ?," //7. workPhone
				+ " ?," //8. email
				+ " ?," //9. location
				+ " ?," //10. password
				+ " ?," //11. photoPath
				+ " ?," //12. lostIdQuestion
				+ " ?)"; //13. lostIdAnswer
		
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
			pstmt.setString(10, dto.getPassword());
			pstmt.setString(11, dto.getPhoto());
			pstmt.setString(12, dto.getLostIdQuestion());
			pstmt.setString(13, dto.getLostIdAnswer());
			results = pstmt.executeUpdate();
			
//			results = INSERT_DATA_SUCCESS; 
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
				dto.setEmployeeId(set.getInt("employeeId")); 	//1
				dto.setName(set.getString("employeeName")); 	//2
				dto.setBirth(set.getString("birthdate"));  		//3
				dto.setTeam(set.getString("team")); 			//4
				dto.setLevel(set.getString("employlevel")); 	//5
				dto.setRole(set.getString("role")); 			//6
				dto.setMobile(set.getString("mobile")); 		//7
				dto.setWorkPhone(set.getString("workphone")); 	//8
				dto.seteMail(set.getString("email"));			//9
				dto.setLocation(set.getString("location"));		//10
				dto.setPassword(set.getString("password"));		//11
				dto.setPhoto(set.getString("photopath"));		//12
				dto.setLostIdQuestion(set.getString("lostIdQuestion")); //13
				dto.setLostIdAnswer(set.getString("lostIdAnswer")); 	//14
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
	 * updateEmployee Info
	 * @param dto
	 * @return result code 1:Success, 0: Fail to update
	 */
	public int updateEmployee(EmployeeDto dto) {
		int results = INSERT_DATA_FAILED; 
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "UPDATE employee SET"
				+ " employeeName = ?," 	//1. employName
				+ " birthDate = ?," 	//2. birthDate
				+ " team = ?," 			//3. team
				+ " employLevel = ?," 	//4. employLevel
				+ " role = ?," 			//5. role
				+ " mobile = ?," 		//6. mobile
				+ " workPhone = ?," 	//7. workPhone
				+ " email = ?," 		//8. email
				+ " location = ?," 		//9. location
				+ " password = ?," 		//10. password
				+ " photoPath = ?," 	//11. photoPath
				+ " lostIdQuestion = ?,"//12. lostIdQuestion
				+ " lostIdAnswer = ?"	//13. lostIdAnswer
				+ " WHERE employeeId = ?"; //14. eployeeId
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBirth());
			pstmt.setString(3, dto.getTeam());
			pstmt.setString(4, dto.getLevel());
			pstmt.setString(5, dto.getRole());
			pstmt.setString(6, dto.getMobile());
			pstmt.setString(7, dto.getWorkPhone());
			pstmt.setString(8, dto.geteMail());
			pstmt.setString(9, dto.getLocation());
			pstmt.setString(10, dto.getPassword());
			pstmt.setString(11, dto.getPhoto());
			pstmt.setString(12, dto.getLostIdQuestion());
			pstmt.setString(13, dto.getLostIdAnswer());
			pstmt.setInt(14, dto.getEmployeeId());
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
		EmployeeDto dto = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, lastEmpId);
			pstmt.setInt(2, idx);
			set = pstmt.executeQuery();
			
			while(set.next()) {
				dto = new EmployeeDto();
				dto.setEmployeeId(set.getInt("employeeId")); 	//1
				dto.setName(set.getString("employeeName")); 	//2
				dto.setBirth(set.getString("birthdate"));  		//3
				dto.setTeam(set.getString("team")); 			//4
				dto.setLevel(set.getString("employlevel")); 	//5
				dto.setRole(set.getString("role")); 			//6
				dto.setMobile(set.getString("mobile")); 		//7
				dto.setWorkPhone(set.getString("workphone")); 	//8
				dto.seteMail(set.getString("email"));			//9
				dto.setLocation(set.getString("location"));		//10
				dto.setPassword(set.getString("password"));		//11
				dto.setPhoto(set.getString("photopath"));		//12
				dto.setLostIdQuestion(set.getString("lostIdQuestion")); //13
				dto.setLostIdAnswer(set.getString("lostIdAnswer")); 	//14
				dtos.add(dto);
			}
			
			// if data is not exist, add dump employee data. 
			for(int i=0;i<idx;i++) {
				if(dtos.size()<=i) {
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
		EmployeeDto dto = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			set = pstmt.executeQuery();
			
			while(set.next()) {
				dto = new EmployeeDto();
				
				dto.setEmployeeId(set.getInt("employeeId")); 	//1
				dto.setName(set.getString("employeeName")); 	//2
				dto.setBirth(set.getString("birthdate"));  		//3
				dto.setTeam(set.getString("team")); 			//4
				dto.setLevel(set.getString("employlevel")); 	//5
				dto.setRole(set.getString("role")); 			//6
				dto.setMobile(set.getString("mobile")); 		//7
				dto.setWorkPhone(set.getString("workphone")); 	//8
				dto.seteMail(set.getString("email"));			//9
				dto.setLocation(set.getString("location"));		//10
				dto.setPassword(set.getString("password"));		//11
				dto.setPhoto(set.getString("photopath"));		//12
				dto.setLostIdQuestion(set.getString("lostIdQuestion")); //13
				dto.setLostIdAnswer(set.getString("lostIdAnswer")); 	//14
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
	
	/**
	 * Get team employees data
	 * @param teamName
	 * @param lastEmpId - previous last employeeNum 
	 * @param idx - the number of employee get from database  
	 * @return
	 */
	public ArrayList<EmployeeDto> getTeamEmployee(String teamName, int lastEmpId, int idx) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from "
				+ "(SELECT * FROM employee "
				+ "WHERE teamName = ? AND employeeId > ? )"
				+ "where rownum <= ?"; 
		ArrayList<EmployeeDto> dtos = new ArrayList<>();
		EmployeeDto dto = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, teamName);
			pstmt.setInt(2, lastEmpId);
			pstmt.setInt(3, idx);
			set = pstmt.executeQuery();
			
			while(set.next()) {
				dto = new EmployeeDto();
				
				dto.setEmployeeId(set.getInt("employeeId")); 	//1
				dto.setName(set.getString("employeeName")); 	//2
				dto.setBirth(set.getString("birthdate"));  		//3
				dto.setTeam(set.getString("team")); 			//4
				dto.setLevel(set.getString("employlevel")); 	//5
				dto.setRole(set.getString("role")); 			//6
				dto.setMobile(set.getString("mobile")); 		//7
				dto.setWorkPhone(set.getString("workphone")); 	//8
				dto.seteMail(set.getString("email"));			//9
				dto.setLocation(set.getString("location"));		//10
				dto.setPassword(set.getString("password"));		//11
				dto.setPhoto(set.getString("photopath"));		//12
				dto.setLostIdQuestion(set.getString("lostIdQuestion")); //13
				dto.setLostIdAnswer(set.getString("lostIdAnswer")); 	//14
				dtos.add(dto);
			}
			
			// if data is not exist, add dump employee data. 
			for(int i=0;i<idx;i++) {
				if(dtos.size()<=i) {
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
	 * get Employee Count
	 * @return total employee count, -1 means error.
	 */
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
	 * get count employee who are in team
	 * @param teamName
	 * @return total employee count in team, -1 means error.
	 */
	public int countEmployee(String teamName) {
		int cnt = -1;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "SELECT COUNT(employeeId) AS c FROM employee WHERE team = ? ";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, teamName);
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
		String query = "INSERT INTO team VALUES (?,?,?)";
		
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
	 * Update Team
	 * @param dto - TeamDto, want to be changed. 
	 * @param privTeamName - previous teamName
	 * @return result code 1: success, 0: failed
	 */
	public int updateTeam(TeamDto dto, String prevTeamName) {
		int results = INSERT_DATA_FAILED;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "update team set teamname=?, teamrole=?, teamleaderId=? where teamname=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query); 
			pstmt.setString(1, dto.getTeamName());
			pstmt.setString(2, dto.getTeamRole());
			pstmt.setString(3, dto.getTeamLeaderId());
			pstmt.setString(4, prevTeamName);
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
	 * Delete team from Database
	 * @param teamName
	 * @return result code 1: success, 0: failed
	 */
	public int deleteTeam(String teamName) {
		int result = DELETE_DATA_FAILED;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "DELETE team WHERE teamname = ?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, teamName);
			pstmt.executeUpdate();
			
			result = DELETE_DATA_SUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
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
		TeamDto dto = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			set = pstmt.executeQuery();
			
			while(set.next()) {
				dto = new TeamDto();
				
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
	
	/**
	 * get employee password
	 * @param empId - employeeId
	 * @return employee id
	 */
	public String getPassword(int empId) {
		String password = null; 
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select password from employee where employeeId=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, empId);
			set = pstmt.executeQuery();
		
			if(set.next()) {
				password = set.getString("password");
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
		return password;
	}
	
	/**
	 * Set New password 
	 * @param empId - employee ID 
	 * @param newPw - new Password
	 * @return results code -1: failed, 1: success 
	 */
	public int setNewPassword (int empId, String newPw) {
		int results = INSERT_DATA_FAILED ; 
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "update employee set password = ? where employeeID=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, newPw);
			pstmt.setInt(2, empId);
			pstmt.executeUpdate();
		
			results = INSERT_DATA_SUCCESS;	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return results;
	}
	
	public ArrayList<EmployeeDto> getQuizData() {
		int tot = countEmployee();
		int[] selectedIndex = new int[3]; 
		Random rand = new Random();
		for(int i=0;i<3;i++) {
			selectedIndex[i]=rand.nextInt(tot+1);
		}
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "SELECT * FROM(" + 
				"Select employeename, photopath, rownum r FROM employee)" + 
				"WHERE r IN (?, ?, ?);"; 
		ArrayList<EmployeeDto> dtos = new ArrayList<>();
		EmployeeDto dto = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			for(int i=1;i<selectedIndex.length+1;i++) {
				pstmt.setInt(i, selectedIndex[i]);
			}
			set = pstmt.executeQuery();
			
			while(set.next()) {
				dto = new EmployeeDto();
				
				dto.setName(set.getString("employeeName"));
				dto.setPhoto(set.getString("photopath"));
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
	
	/**
	 * Get Employee who are on birthday at selected month
	 * @param month 
	 * @return - Employ ArrayList
	 */
	public ArrayList<EmployeeDto> getEmpBirth(int month) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "SELECT * FROM employee" + 
				" WHERE TO_CHAR(TO_DATE(birthdate), 'mm') = ?"; 
		ArrayList<EmployeeDto> dtos = new ArrayList<>();
		EmployeeDto dto = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, month);
			set = pstmt.executeQuery();
			
			while(set.next()) {
				dto = new EmployeeDto();
				
				dto.setEmployeeId(set.getInt("employeeId")); 	//1
				dto.setName(set.getString("employeeName")); 	//2
				dto.setBirth(set.getString("birthdate"));  		//3
				dto.setTeam(set.getString("team")); 			//4
				dto.setLevel(set.getString("employlevel")); 	//5
				dto.setRole(set.getString("role")); 			//6
				dto.setMobile(set.getString("mobile")); 		//7
				dto.setWorkPhone(set.getString("workphone")); 	//8
				dto.seteMail(set.getString("email"));			//9
				dto.setLocation(set.getString("location"));		//10
				dto.setPassword(set.getString("password"));		//11
				dto.setPhoto(set.getString("photopath"));		//12
				dto.setLostIdQuestion(set.getString("lostIdQuestion")); //13
				dto.setLostIdAnswer(set.getString("lostIdAnswer")); 	//14
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
