package jdbc_erp.UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

import jdbc_erp.dao.DepartmentDao;
import jdbc_erp.dao.DepartmentDaoImpl;
import jdbc_erp.dto.Department;








public class TablePanel extends JPanel {
	private JTable table;	
	private List<Department> lists;
	private NonEditableModel model;
	private JScrollPane scrollPane;
	private DepartmentDao Dao;
	private DepartmentDaoImpl dao;
	
	/**
	 * Create the panel.
	 */
	public TablePanel() {
		initComponents();
	}
	
	
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		/*lists = new ArrayList<>();
		Dao = new DepartmentDaoImpl();
		lists = Dao.selectDepartmentDaoByAll();*/
		table = new JTable();
		
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 한 row만 선택가능
		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		scrollPane.setViewportView(table);
		
	}


	public void loadDatas() {
		model = new NonEditableModel(getDatas(), getColumnNames());
		table.setModel(model);
	}

	public void setLists(List<Department> lists1) {
		this.lists = lists1;
	}


	private Object[] getColumnNames() {
		return new String[] { "번호", "이름", "위치"};
	}

	private Object[][] getDatas() {
		Object[][] datas = new Object[lists.size()][];
		for (int i = 0; i < lists.size(); i++) {
			datas[i] = getDepartmentArray(lists.get(i));
		}
		return datas;
	}

	private Object[] getDepartmentArray(Department department) {
		return new Object[] { department.getDeptno(), department.getDeptname(), department.getFloor()};
	}
	public void setPopMenu(JPopupMenu popup) {
		scrollPane.setComponentPopupMenu(popup);
		table.setComponentPopupMenu(popup);
	}


	public Department getSelectedDepartment() {
		int selectedIndex = table.getSelectedRow();
		String deptno = (String) table.getValueAt(selectedIndex, 0);
		return new Department(deptno);
	}

}
