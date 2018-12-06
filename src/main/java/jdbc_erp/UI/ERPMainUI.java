package jdbc_erp.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ERPMainUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnEmpoyee;
	private JButton btnDepartment;
	private JButton btnTitle;
	private EmployeeFrame EmployeeFrame;
	private TitleFrame TitleFrame;
	private DepartmentFrame DepartmentFrame;

	

	/**
	 * Create the frame.
	 */
	public ERPMainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnEmpoyee = new JButton("사원관리");
		btnEmpoyee.addActionListener(this);
		contentPane.add(btnEmpoyee);
		
		btnDepartment = new JButton("부서관리");
		btnDepartment.addActionListener(this);
		contentPane.add(btnDepartment);
		
		btnTitle = new JButton("직책관리");
		btnTitle.addActionListener(this);
		contentPane.add(btnTitle);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTitle) {
			do_btnTitle_actionPerformed(e);
		}
		if (e.getSource() == btnDepartment) {
			do_btnDepartment_actionPerformed(e);
		}
		if (e.getSource() == btnEmpoyee) {
			do_btnEmpoyee_actionPerformed(e);
		}
	}
	protected void do_btnEmpoyee_actionPerformed(ActionEvent e) {
		boolean a = false;
		
		EmployeeFrame = new EmployeeFrame();			
		EmployeeFrame.setVisible(true);
		a=true;
		EmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
	}
	protected void do_btnDepartment_actionPerformed(ActionEvent e) {
		DepartmentFrame = new DepartmentFrame();
		DepartmentFrame.setVisible(true);
		DepartmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	protected void do_btnTitle_actionPerformed(ActionEvent e) {
		TitleFrame = new TitleFrame();
		TitleFrame.setVisible(true);
		TitleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
