package jdbc_erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jdbc_erp.dto.Department;
import jdbc_erp.dto.Employee;
import jdbc_erp.dto.Title;
import jdbc_erp.jdbc.ConnectionProvider;
import jdbc_erp.jdbc.LogUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public List<Employee> selectEmployeedaoByAll() {
		List<Employee> list = new ArrayList<>();
		String sql = "select empno, empname, tno, tname, salary, sex,deptno, deptname, floor, entrydaty from employee e join department d on e.dno=d.deptno join title t on e.title=t.tno";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getEmployee(rs));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	private Employee getEmployee(ResultSet rs) throws SQLException {
		String empno = rs.getString("empno");
		String empname = rs.getString("empname");
		Title title = new Title(rs.getString("tno"),rs.getString("tname"));
		int salary = rs.getInt("salary");
		boolean gender = rs.getBoolean("sex");
		Department deptNo = new Department(rs.getString("deptno"),rs.getString("deptname"),rs.getInt("floor"));
		Date joindate = rs.getDate("entrydaty");
		
		return new Employee(empno,empname,title,salary,gender,deptNo,joindate);
	}

	@Override
	public int insertEmployee(Employee employee) throws SQLException {
		LogUtil.prnLog("insertEmployee()");
		String sql = "insert into employee values(?,?,?,?,?,?,?)";
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, employee.getEmpno());
			pstmt.setString(2, employee.getEmpname());
			pstmt.setString(3, employee.getTitle().getTno());
			pstmt.setInt(4, employee.getSalary());
			boolean sex ;
			if(employee.isGender()) {
				sex = true;
			}else {
				sex = false;
			}
			pstmt.setBoolean(5, sex);
			pstmt.setString(6, employee.getDno().getDeptno());
			pstmt.setString(7, String.format("%tF",employee.getEntrydaty()));
						
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public int deleteEmployee(Employee employee) throws SQLException {
		LogUtil.prnLog("insertEmployee()");
		String sql = "delete from employee where empno=?";
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, employee.getEmpno());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public int updateEmployee(Employee employee) throws SQLException {
		LogUtil.prnLog("updateEmployee()");
		String sql = "update employee set empname=?, title=?, salary=?, sex=?, dno=?, entrydaty=? where empno=?";
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setString(1, employee.getEmpname());
			pstmt.setString(2, employee.getTitle().getTno());
			pstmt.setInt(3, employee.getSalary());
			pstmt.setBoolean(4, employee.isGender());
			pstmt.setString(5, employee.getDno().getDeptno());
			pstmt.setString(6, String.format("%tF",employee.getEntrydaty()));
			pstmt.setString(7, employee.getEmpno());
			
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public Employee selectEmployeeByempno(Employee employee) throws SQLException {
		LogUtil.prnLog("selectEmployeeByempno()");
		
	      
	      String sql = "select empno, empname, tno, tname, salary, sex,deptno, deptname, floor, entrydaty from employee e join department d on e.dno=d.deptno join title t on e.title=t.tno where e.empno =?";
	      
	      try (Connection conn = ConnectionProvider.getConnection(); 
	    	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	         pstmt.setString(1, employee.getEmpno());
	         LogUtil.prnLog(pstmt);
	         try (ResultSet rs = pstmt.executeQuery()) {
	        	 if (rs.next()) {
	        		 employee = getEmployee(rs);
	 			}
	         }
	      }
	     
	      return employee;
	}
	@Override
	public String nextDeptNo() {
		String sql = "select max(empno) as nextno from employee";
		Calendar day = Calendar.getInstance();
		String year = (day.get(Calendar.YEAR)+"").substring(1);//018
		String nextStr = null;
		try (Connection conn = ConnectionProvider.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			if(rs.next()) {
				nextStr = String.format("E%3s%03d", year, Integer.parseInt(rs.getString("nextno").substring(4)) +1);
				
			}
			
		} catch (NullPointerException e) {
						
			nextStr = String.format("E%3s", year);
			nextStr = nextStr + "001";
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nextStr;
	}
	

}
