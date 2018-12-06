package jdbc_erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import jdbc_erp.dto.Title;
import jdbc_erp.jdbc.ConnectionProvider;
import jdbc_erp.jdbc.LogUtil;

public class TitleDaoImpl implements TitleDao {

	@Override
	public List<Title> selectTitledaoByAll() {
		List<Title> list = new ArrayList<>();
		String sql = "select tno, tname from title";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getTitle(rs));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	private Title getTitle(ResultSet rs) throws SQLException {
		String tno = rs.getString("tno");
		String tname = rs.getString("tname");
		
		return new Title(tno,tname);
	}

	@Override
	public int insertTitle(Title title) throws SQLException {
		LogUtil.prnLog("insertTitle()");
		String sql = "insert into title values(?,?)";
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, title.getTno());
			pstmt.setString(2, title.getTname());			
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public int deleteTitle(Title title) throws SQLException {
		LogUtil.prnLog("deleteTitle()");
		String sql = "delete from title where tno=?";
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, title.getTno());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public int updateTitle(Title title) throws SQLException {
		LogUtil.prnLog("updatetitle()");
		String sql = "update title set tname=? where tno=?";
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, title.getTname());			
			pstmt.setString(2, title.getTno());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public Title selectTitleBytno(Title title) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String nextDeptNo() {
		String sql = "select max(tno) as nextno from title";
		String nextStr = null;
		try (Connection conn = ConnectionProvider.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			if(rs.next()) {
				nextStr = String.format("T%03d", Integer.parseInt(rs.getString("nextno").substring(1)) +1);
				
			}
			
		} catch (NullPointerException e) {
			nextStr = "T001";
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nextStr;
	}
	public ArrayList selectTitleAll() {
		ArrayList list = new ArrayList<>();
		String sql = "select tno, tname from title";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getTitleo(rs));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	private String getTitleo(ResultSet rs) throws SQLException {
		String tname = rs.getString("tname");		
		return new String(tname);
	}
	

}
