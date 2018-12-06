package jdbc_erp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import jdbc_erp.dto.Department;



public interface DepartmentDao {
	List<Department> selectDepartmentDaoByAll();
	int insertDepartment(Department department) throws SQLException;	
	int deleteDepartment(Department department) throws SQLException;
	int updateDepartment(Department department) throws SQLException;
	Department selectDepartmentBydeptno(Department department) throws SQLException;
	String nextDeptNo();
	ArrayList selectdeptAll();
	
}
