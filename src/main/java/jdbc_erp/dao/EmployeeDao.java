package jdbc_erp.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_erp.dto.Employee;



public interface EmployeeDao {
	List<Employee> selectEmployeedaoByAll();
	int insertEmployee(Employee employee) throws SQLException;	
	int deleteEmployee(Employee employee) throws SQLException;
	int updateEmployee(Employee employee) throws SQLException;
	Employee selectEmployeeByempno(Employee employee) throws SQLException;
	String nextDeptNo();
}
