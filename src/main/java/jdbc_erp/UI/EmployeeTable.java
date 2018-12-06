package jdbc_erp.UI;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import jdbc_erp.dao.DepartmentDao;
import jdbc_erp.dao.DepartmentDaoImpl;
import jdbc_erp.dao.EmployeeDao;
import jdbc_erp.dao.EmployeeDaoImpl;
import jdbc_erp.dto.Department;
import jdbc_erp.dto.Employee;

public class EmployeeTable extends JPanel {
	private JTable table;	
	private List<Employee> lists;
	private NonEditableModel model;
	private JScrollPane scrollPane;
	private EmployeeDao Dao;
	private EmployeeDaoImpl dao;

	/**
	 * Create the panel.
	 */
	public EmployeeTable() {
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		scrollPane.setViewportView(table);

	}
	
	
	public void loadDatas() {
		model = new NonEditableModel(getDatas(), getColumnNames());
		table.setModel(model);
	}

	public void setLists(List<Employee> lists1) {
		this.lists = lists1;
	}


	private Object[] getColumnNames() {
		return new String[] { "번호", "사원명", "직책","급여","성별","부서","입시일"};
	}

	private Object[][] getDatas() {
		Object[][] datas = new Object[lists.size()][];
		for (int i = 0; i < lists.size(); i++) {
			datas[i] = getEmployeeArray(lists.get(i));
		}
		return datas;
	}

	private Object[] getEmployeeArray(Employee employee) {
		String title = employee.getTitle().getTname();
		boolean sex = employee.isGender();
		String gende;
		if(sex==false) {
			gende="남자";
		}else {
			gende="여자";
		}
		String dept = (employee.getDno()).getDeptname()+"("+(employee.getDno()).getFloor()+"층)";
		return new Object[] { employee.getEmpno(), employee.getEmpname(),title,employee.getSalary(),gende,dept,employee.getEntrydaty()};
	}
	public void setPopMenu(JPopupMenu popup) {
		scrollPane.setComponentPopupMenu(popup);
		table.setComponentPopupMenu(popup);
	}


	public Employee getSelectedEmployee() {
		int selectedIndex = table.getSelectedRow();
		String empno = (String) table.getValueAt(selectedIndex, 0);
		return new Employee(empno);
	}

}
