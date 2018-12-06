package jdbc_erp.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


import jdbc_erp.dao.DepartmentDao;
import jdbc_erp.dao.DepartmentDaoImpl;
import jdbc_erp.dto.Department;
import jdbc_erp.jdbc.LogUtil;
import jdbc_erp.service.DeptUIService;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class DepartmentFrame extends JFrame implements ActionListener {

	public static final String OK = "추가";
	public static final String UPDATE = "수정";
	public static final String DEL = "삭제";
	
	
	private JPanel contentPane;
	private JTextField textdeptno;
	private JTextField textdeptname;
	private JTextField textfloor;
	/*private DepartmentDaoImpl dao;*/
	private JButton btnAdd;
	private JButton btncencel;
	private NonEditableModel model;
	private List<Department> lists;
	private DeptUIService service;
	private TablePanel tablePanel;
	private JPopupMenu PopupMenu;

	
	

	/**
	 * Create the frame.
	 */
	public DepartmentFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("부서 관리");
		service = new DeptUIService();
		lists = new ArrayList<>();
		setBounds(100, 100, 629, 450); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lbldeptno = new JLabel("번호");
		lbldeptno.setHorizontalAlignment(SwingConstants.RIGHT);		
		panel.add(lbldeptno);
		
		textdeptno = new JTextField();
		textdeptno.setEditable(false);
		/*dao= new DepartmentDaoImpl();*/
		
		textdeptno.setText(service.nextDeptNo());
		
		
		panel.add(textdeptno);
		textdeptno.setColumns(10);
		
		JLabel lbldeptname = new JLabel("부서명");
		lbldeptname.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lbldeptname);
		
		textdeptname = new JTextField();
		panel.add(textdeptname);
		
		textdeptname.setColumns(10);
		
		JLabel lblfloor = new JLabel("위치");
		lblfloor.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblfloor);
		
		textfloor = new JTextField();
		panel.add(textfloor);
		textfloor.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		panel_1.add(btnAdd);
		
		btncencel = new JButton("취소");
		btncencel.addActionListener(this);
		panel_1.add(btncencel);
		
		tablePanel = new TablePanel();
		lists = new ArrayList<>();
		lists = service.deptSelectAll();
		tablePanel.setLists(lists);
		tablePanel.loadDatas();
		contentPane.add(tablePanel);		
		tablePanel.setPopMenu(getPopupMenu());
		
		
		
	}
	public JPopupMenu getPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();
		
		JMenuItem mntmDel = new JMenuItem(DEL);
		mntmDel.addActionListener(this);
		popupMenu.add(mntmDel);
		
		JMenuItem mntmUpdate = new JMenuItem(UPDATE);
		mntmUpdate.addActionListener(this);
		popupMenu.add(mntmUpdate);
		return popupMenu;
	}
	

	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btncencel) {
			do_btncencel_actionPerformed(e);
		}
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().equals(OK)) {
				do_btnAdd_actionPerformed(e);
			}else {
				do_btnUpdata_actionPerformed(e);
			}
								
		}
		if (e.getActionCommand().equals(UPDATE)) {
			do_mntmUpdate_actionPerformed(e);
		}
		if (e.getActionCommand().equals(DEL)) {
			do_mntmDel_actionPerformed(e);
		}
	}
	private void do_btnUpdata_actionPerformed(ActionEvent e) {
		try {
			Department newDepartment = getDepartment();
			int rowAffected = service.updateDepartment(newDepartment);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			/*dao = null;*/
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		/*dao= new DepartmentDaoImpl();*/
		textdeptno.setText(service.nextDeptNo());
		textdeptname.setText("");
		textfloor.setText("");		
		lists =service.deptSelectAll();
		tablePanel.setLists(lists);
		tablePanel.loadDatas();
		btnAdd.setText(OK);
		
	}

	private void do_mntmDel_actionPerformed(ActionEvent e) {
		try {
			Department selectedStd = tablePanel.getSelectedDepartment();
			service.deleteDepartment(selectedStd);
			lists = service.deptSelectAll();
			tablePanel.setLists(lists);
			tablePanel.loadDatas();
			textdeptno.setText(service.nextDeptNo());
		}catch (Exception e1) {
			if (lists.size()==0) {
				JOptionPane.showMessageDialog(null, "부서정보가 없습니다.");
				return;
			}
			JOptionPane.showMessageDialog(null, "삭제하고자하는 부서를참조중인곳이있습니다.");
		}
		
	}

	private void do_mntmUpdate_actionPerformed(ActionEvent e) {
		try {
			
			
			Department selected = tablePanel.getSelectedDepartment();
			Department searchStd = service.selectDepartmentBydeptno(selected);
			textdeptno.setText(searchStd.getDeptno());
			textdeptname.setText(searchStd.getDeptname());
			textfloor.setText(searchStd.getFloor() + "");
			btnAdd.setText("수정");
			
			/**/
			
			
		}catch (Exception e1) {
			if (lists.size()==0) {
				JOptionPane.showMessageDialog(null, "정보가 없습니다.");
				return;
			}
			
		}
		
	}

	protected void do_btnAdd_actionPerformed(ActionEvent e) {
		try {
			/*dao= new DepartmentDaoImpl();*/
			Department newDepartment = getDepartment();
			int rowAffected = service.insertDepartment(newDepartment);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			/*dao = null;*/
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		/*dao= new DepartmentDaoImpl();*/
		textdeptno.setText(service.nextDeptNo());
		textdeptname.setText("");
		textfloor.setText("");		
		lists = service.deptSelectAll();
		tablePanel.setLists(lists);
		tablePanel.loadDatas();

	}
	private Department getDepartment() {		
		String deptno = textdeptno.getText().trim();
		String deptname = textdeptname.getText().trim();		
		int floor = (int) Double.parseDouble(textfloor.getText().trim());
		return new Department(deptno,deptname, floor);
	}
	protected void do_btncencel_actionPerformed(ActionEvent e) {
		textdeptname.setText("");
		textfloor.setText("");
		btnAdd.setText(OK);
		textdeptno.setText(service.nextDeptNo());
	}
}
