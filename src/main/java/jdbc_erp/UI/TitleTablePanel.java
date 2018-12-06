package jdbc_erp.UI;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import jdbc_erp.dao.DepartmentDao;
import jdbc_erp.dao.DepartmentDaoImpl;
import jdbc_erp.dao.TitleDao;
import jdbc_erp.dao.TitleDaoImpl;
import jdbc_erp.dto.Title;
import jdbc_erp.dto.Title;
import javax.swing.table.DefaultTableModel;

public class TitleTablePanel extends JPanel {
	private JTable table;
	private List<Title> lists;
	private NonEditableModel model;
	private JScrollPane scrollPane;
	private TitleDao Dao;
	private TitleDaoImpl dao;

	/**
	 * Create the panel.
	 */
	public TitleTablePanel() {
		initcoments();
		
	}


	private void initcoments() {
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

	


	private Object[] getColumnNames() {
		return new String[] { "번호", "직책"};
	}

	private Object[][] getDatas() {
		Object[][] datas = new Object[lists.size()][];
		for (int i = 0; i < lists.size(); i++) {
			datas[i] = getTitleArray(lists.get(i));
		}
		return datas;
	}

	private Object[] getTitleArray(Title title) {
		return new Object[] { title.getTno(), title.getTname()};
	}
	
	public void setPopMenu(JPopupMenu popup) {
		scrollPane.setComponentPopupMenu(popup);
		table.setComponentPopupMenu(popup);
	}


	public Title getSelectedTitle() {
		int selectedIndex = table.getSelectedRow();
		String tno = (String) table.getValueAt(selectedIndex, 0);
		String tname = (String) table.getValueAt(selectedIndex, 1);
		return new Title(tno,tname);
	}
	
	
	
	
	public void setLists(List<Title> lists1) {
		this.lists = lists1;
	}

}
