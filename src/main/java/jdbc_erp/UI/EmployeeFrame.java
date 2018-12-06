package jdbc_erp.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.core.parser.ParseException;

import jdbc_erp.dao.DepartmentDaoImpl;
import jdbc_erp.dao.EmployeeDaoImpl;
import jdbc_erp.dao.TitleDaoImpl;
import jdbc_erp.dto.Department;
import jdbc_erp.dto.Employee;
import jdbc_erp.dto.Title;
import jdbc_erp.jdbc.LogUtil;
import jdbc_erp.service.DeptUIService;
import javax.swing.ButtonGroup;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class EmployeeFrame extends JFrame implements ActionListener {

	public static final String OK = "추가";
	public static final String UPDATE = "수정";
	public static final String DEL = "삭제";
	
	private JPanel contentPane;
	private JTextField textno;
	private JTextField textname;
	private JTextField textentrydaty;
					
	
	private JButton btnAdd;
	private JButton btncencel;
	private NonEditableModel model;
	private List<Employee> lists;
	private DeptUIService service;
	private EmployeeTable tablePanel;
	private JPopupMenu PopupMenu;
	private final ButtonGroup buttonGroup = new ButtonGroup();	
	private JButton btnCancel;
	private JComboBox<Title> comboBoxtitle;
	private JSpinner spinnersalar;
	private JRadioButton rdbtnMALE;
	private JComboBox<Department> comboBoxdepartment;
	private JRadioButton rdbtnFEMALE;
	private JPanel sexpanel_3;
	private Calendar day;
	private String date;

	
 
	/**
	 * Create the frame.
	 */
	public EmployeeFrame() {
		setTitle("사원 관리");
		setType(Type.POPUP);
		service = new DeptUIService();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblno = new JLabel("번호");
		lblno.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblno);
		
		textno = new JTextField();
		textno.setEditable(false);
		panel.add(textno);
		
		textno.setColumns(10);
		
		textno.setText(service.nextEmpNo());
		
		JLabel lblname = new JLabel("사원명");
		lblname.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblname);
		
		textname = new JTextField();
		panel.add(textname);
		textname.setColumns(10); 
		
		JLabel lbltitle = new JLabel("직책");
		lbltitle.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lbltitle);
								
				
		DefaultComboBoxModel<Title> model = new DefaultComboBoxModel<>(new Vector<>(service.selectTitledaoByAll()));
		
		comboBoxtitle = new JComboBox<>(model);
		panel.add(comboBoxtitle);
		
		JLabel lblsalar = new JLabel("급여");
		lblsalar.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblsalar);
		
		spinnersalar = new JSpinner();
		spinnersalar.setModel(new SpinnerNumberModel(1500000, 1000000, 5000000, 100000));
		panel.add(spinnersalar);
		
		
		JLabel lblsex = new JLabel("성별");
		lblsex.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblsex);
		
		sexpanel_3 = new JPanel();
		panel.add(sexpanel_3);
		sexpanel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		rdbtnMALE = new JRadioButton("남");
		rdbtnMALE.setSelected(true);
		buttonGroup.add(rdbtnMALE);
		sexpanel_3.add(rdbtnMALE);
		
		rdbtnFEMALE = new JRadioButton("여");
		buttonGroup.add(rdbtnFEMALE);
		sexpanel_3.add(rdbtnFEMALE);
		
		JLabel lbldepartment = new JLabel("부서");
		lbldepartment.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lbldepartment);
		
		DefaultComboBoxModel<Department> model2 = new DefaultComboBoxModel<>(new Vector<>(service.deptSelectAll()));
		comboBoxdepartment = new JComboBox<>(model2);
		
		panel.add(comboBoxdepartment);
		
		JLabel lblentrydaty = new JLabel("입사일");
		lblentrydaty.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblentrydaty);
		
		textentrydaty = new JTextField();
		panel.add(textentrydaty);
		textentrydaty.setColumns(10);
		
		day = Calendar.getInstance();
		date = day.get(Calendar.YEAR)+"-"+day.get(Calendar.MONTH)+"-"+day.get(Calendar.DATE);
		textentrydaty.setText(day.get(Calendar.YEAR)+"-"+day.get(Calendar.MONTH)+"-"+day.get(Calendar.DATE));
		
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		panel_2.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panel_2.add(btnCancel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		tablePanel = new EmployeeTable();
		lists = new ArrayList<>();
		
		lists = service.empSelectAll();
		
		tablePanel.setLists(lists);
		tablePanel.loadDatas();
		contentPane.add(tablePanel);		
		tablePanel.setPopMenu(getPopupMenu());
		
	}

	private JPopupMenu getPopupMenu() {
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
		if (e.getSource() == btnCancel) {
			do_btnCancel_actionPerformed(e);
		}
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().equals(OK)) {
				do_btnOK_actionPerformed(e);
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
	private void do_mntmDel_actionPerformed(ActionEvent e) {
		try {
			Employee selectedStd = tablePanel.getSelectedEmployee();
			service.deleteEmployee(selectedStd);
			lists = service.empSelectAll();
			tablePanel.setLists(lists);
			tablePanel.loadDatas();
			textno.setText(service.nextEmpNo());
		}catch (Exception e1) {
			if (lists.size()==0) {
				JOptionPane.showMessageDialog(null, "부서정보가 없습니다.");
				return;
			}
			JOptionPane.showMessageDialog(null, "삭제하고자하는 부서를 선택하세요.");
		}
		
	}

	private void do_mntmUpdate_actionPerformed(ActionEvent e) {
		
		try {									
			Employee selected = tablePanel.getSelectedEmployee();
			Employee searchStd = service.selectEmployeeByempno(selected);
			textno.setText(searchStd.getEmpno());
			textname.setText(searchStd.getEmpname());
			comboBoxtitle.setSelectedItem(searchStd.getTitle().getTname());
			spinnersalar.setValue(searchStd.getSalary());
			System.out.println(searchStd.isGender());
			if(searchStd.isGender()==false) {
				rdbtnMALE.setSelected(true);
			}else {
				rdbtnFEMALE.setSelected(true);
			}
			
			String setSelectedDno = searchStd.getDno().getDeptname()+"("+ searchStd.getDno().getFloor() +"층)";
			comboBoxdepartment.setSelectedItem(setSelectedDno);
			Date day = searchStd.getEntrydaty();
			textentrydaty.setText(String.format("%tF", day));
			btnAdd.setText("수정");									
		}catch (Exception e1) {
			e1.printStackTrace();
			if (lists.size()==0) {
				JOptionPane.showMessageDialog(null, "정보가 없습니다.");
				return;
			}
			
		}
		
	}

	private void do_btnUpdata_actionPerformed(ActionEvent e) {
		try {
			Employee newEmployee = getEmployee();
			int rowAffected = service.updateEmployee(newEmployee);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));			
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
		textno.setText(service.nextEmpNo());
		textname.setText("");
		spinnersalar.setValue(1500000);
		rdbtnMALE.setSelected(true);
		lists = service.empSelectAll();
		tablePanel.setLists(lists);
		tablePanel.loadDatas();
		btnAdd.setText("추가");
		
	}

	protected void do_btnOK_actionPerformed(ActionEvent e) {
		try {			
			Employee newDepartment = getEmployee();
			int rowAffected = service.insertEmployee(newDepartment);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));			
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
		textname.setText("");
		textentrydaty.setText(date);
		spinnersalar.setValue(1500000);	
		textno.setText(service.nextEmpNo());
		lists = service.empSelectAll();
		tablePanel.setLists(lists);
		tablePanel.loadDatas();
	}
	protected void do_btnCancel_actionPerformed(ActionEvent e) {
		textname.setText("");
		textentrydaty.setText(date);
		spinnersalar.setValue(1500000);
		textno.setText(service.nextEmpNo());
		btnAdd.setText("추가");
		
	}
	private Employee getEmployee() {		
		String deptno = textno.getText().trim();
		String deptname = textname.getText().trim();
		Title title = (Title) comboBoxtitle.getSelectedItem();
		int salary = (int) spinnersalar.getValue();	
		boolean gender;
		if(rdbtnMALE.isSelected()) {
			gender = false;
		}else {
			gender = true;
		}		
		Department dno = (Department) comboBoxdepartment.getSelectedItem();	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      Date date = null;
	      try {
	         date = sdf.parse(textentrydaty.getText().trim());
	      } catch (java.text.ParseException e) {
	         e.printStackTrace();
	      }
		return new Employee(deptno,deptname, title,salary,gender,dno,date);
	}
}
