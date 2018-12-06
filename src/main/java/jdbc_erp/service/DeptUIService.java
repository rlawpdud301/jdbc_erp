package jdbc_erp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc_erp.dao.DepartmentDao;
import jdbc_erp.dao.DepartmentDaoImpl;
import jdbc_erp.dao.EmployeeDao;
import jdbc_erp.dao.EmployeeDaoImpl;
import jdbc_erp.dao.TitleDao;
import jdbc_erp.dao.TitleDaoImpl;
import jdbc_erp.dto.Department;
import jdbc_erp.dto.Employee;
import jdbc_erp.dto.Title;

public class DeptUIService {
	private DepartmentDao deptDao;
	private TitleDao titleDao;
	private EmployeeDao employeeDao;

	public DeptUIService() {
		deptDao= new DepartmentDaoImpl(); 
		titleDao= new TitleDaoImpl();
		employeeDao = new EmployeeDaoImpl();
	}
	
	//emp///////////////////////////////////////////////////////
	public List<Employee> empSelectAll(){
		return employeeDao.selectEmployeedaoByAll();
	}
	public int deleteEmployee(Employee employee) throws SQLException {
		return employeeDao.deleteEmployee(employee);
	}
	
	public Employee selectEmployeeByempno(Employee employee) throws SQLException {
		return employeeDao.selectEmployeeByempno(employee);
	}
	
	public int updateEmployee(Employee employee) throws SQLException{
		return employeeDao.updateEmployee(employee);
	}
	public int insertEmployee(Employee employee) throws SQLException {
		return employeeDao.insertEmployee(employee);
	}
	public String nextEmpNo() {
		return employeeDao.nextDeptNo();
	}

	//dept//////////////////////////////////////////////////////
	public List<Department> deptSelectAll(){
		return deptDao.selectDepartmentDaoByAll();
	}
	public int deleteDepartment(Department department) throws SQLException {
		return deptDao.deleteDepartment(department);
	}
	
	public Department selectDepartmentBydeptno(Department department) throws SQLException {
		return deptDao.selectDepartmentBydeptno(department);
	}
	
	public int updateDepartment(Department department) throws SQLException{
		return deptDao.updateDepartment(department);
	}
	public int insertDepartment(Department department) throws SQLException {
		return deptDao.insertDepartment(department);
	}
	
	public ArrayList selectdeptAll() {
		return deptDao.selectdeptAll();
	}
	public String nextDeptNo() {
		return deptDao.nextDeptNo();
	}

	//title///////////////////////////////////////////////////////////
	public List<Title> selectTitledaoByAll(){
		return titleDao.selectTitledaoByAll();
	}
	
	public int deleteTitle(Title title) throws SQLException {
		return titleDao.deleteTitle(title);
	}
	
	public Title selectTitleBytno(Title title) throws SQLException {
		return titleDao.selectTitleBytno(title);
	}
	
	public int updateTitle(Title title) throws SQLException{
		return titleDao.updateTitle(title);
	}
	public int insertTitle(Title title) throws SQLException {
		return titleDao.insertTitle(title);
	}
		
	public ArrayList selectTitleAll() {		
		return titleDao.selectTitleAll();
	}
	public String nextTitleNo() {
		return titleDao.nextDeptNo();
	}

	
	
	
	
	
	
	
	
	
}
