package jdbc_erp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc_erp.dto.Employee;
import jdbc_erp.dto.Title;

public interface TitleDao {
	List<Title> selectTitledaoByAll();
	int insertTitle(Title title) throws SQLException;	
	int deleteTitle(Title title) throws SQLException;
	int updateTitle(Title title) throws SQLException;
	Title selectTitleBytno(Title title) throws SQLException;
	String nextDeptNo();
	ArrayList selectTitleAll();
}
