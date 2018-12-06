package jdbc_erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc_erp.dto.Department;
import jdbc_erp.dto.Employee;
import jdbc_erp.jdbc.ConnectionProvider;
import jdbc_erp.jdbc.LogUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public List<Department> selectDepartmentDaoByAll() {
		List<Department> list = new ArrayList<>();
		String sql = "select * from department";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getDepartment(rs));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	private Department getDepartment(ResultSet rs) throws SQLException {
		String deptno = rs.getString("deptno");
		String deptname = rs.getString("deptname");
		int floor = rs.getInt("floor");
				
		return new Department(deptno,deptname,floor);
	}

	@Override
	public int insertDepartment(Department department) throws SQLException {
		LogUtil.prnLog("insertDepartment()");
		String sql = "insert into department values(?,?,?)"; 
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, department.getDeptno());
			pstmt.setString(2, department.getDeptname());
			pstmt.setInt(3, department.getFloor());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public int deleteDepartment(Department department) throws SQLException {
		LogUtil.prnLog("insertTitle()");
		String sql = "delete from department where deptno=?";
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, department.getDeptno());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public int updateDepartment(Department department) throws SQLException {
		LogUtil.prnLog("updateDepartment()");
		String sql = "update department set deptname=?, floor=? where deptno=?";
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, department.getDeptname());
			pstmt.setInt(2, department.getFloor());
			pstmt.setString(3, department.getDeptno());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public Department selectDepartmentBydeptno(Department department) throws SQLException {
		LogUtil.prnLog("selectDepartmentByCode()");
			
	      
	      String sql = "select deptno,deptname,floor from department where deptno=?;";
	      
	      try (Connection conn = ConnectionProvider.getConnection(); 
	    	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	         pstmt.setString(1, department.getDeptno());
	         LogUtil.prnLog(pstmt);
	         try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	            	department=getDepartment(rs);
	               System.out.println(conn);
	            }
	         }
	      }
	      return department;
	}
	@Override
	public String nextDeptNo() {
		String sql = "select max(deptno) as nextno from department";
		String nextStr = null;
		try (Connection conn = ConnectionProvider.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			if(rs.next()) {
				nextStr = String.format("D%03d", Integer.parseInt(rs.getString("nextno").substring(1)) +1);
				
			}
			
		} catch (NullPointerException e) {
			nextStr = "D001";
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nextStr;
	}
	@Override
	public ArrayList selectdeptAll() {
		ArrayList list = new ArrayList<>();
		String sql = "select deptname,floor from department";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getdeptname(rs));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	private String getdeptname(ResultSet rs) throws SQLException {
		String tname = rs.getString("deptname")+"("+ rs.getString("floor") +"층)";		
		return new String(tname);
	}
	

}
