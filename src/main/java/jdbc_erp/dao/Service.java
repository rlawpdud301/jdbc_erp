package jdbc_erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc_erp.jdbc.ConnectionProvider;
import jdbc_erp.jdbc.LogUtil;

public class Service {
	public String nextDeptNo(String a) {
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
}
