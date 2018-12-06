 package jdbc_erp.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import jdbc_erp.dao.DepartmentDaoImpl;
import jdbc_erp.dao.Service;
import jdbc_erp.dao.TitleDaoImpl;
import jdbc_erp.dto.Department;
import jdbc_erp.dto.Title;
import jdbc_erp.jdbc.LogUtil;
import jdbc_erp.service.DeptUIService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class TitleFrame extends JFrame implements ActionListener {
	
	public static final String OK = "추가";
	public static final String UPDATE = "수정";
	public static final String DEL = "삭제";

	private JPanel contentPane;
	private JTextField texttno;
	private JTextField textname;
	private TitleTablePanel tablePanel;
	private List<Title> lists;
	/*private TitleDaoImpl dao;*/
	private JButton btnAdd;
	private JButton btncancel;
	private DeptUIService service;
	

	
	/**
	 * Create the frame.
	 */
	public TitleFrame() {
		service = new DeptUIService(); 
		setType(Type.UTILITY);
		setTitle("직책관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lbltno = new JLabel("번호");
		lbltno.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lbltno);
		
		texttno = new JTextField();
		texttno.setEditable(false);
		panel_1.add(texttno);
		texttno.setColumns(10);
		
		/*dao = new TitleDaoImpl();*/
		texttno.setText(service.nextTitleNo());
		
		JLabel lbltname = new JLabel("직책명");
		lbltname.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lbltname);
		
		textname = new JTextField();
		panel_1.add(textname);
		textname.setColumns(10);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		panel_1.add(btnAdd);
		
		btncancel = new JButton("취소");
		btncancel.addActionListener(this);
		panel_1.add(btncancel);
		
		
	/*	dao = new TitleDaoImpl();*/
		tablePanel = new TitleTablePanel();
		lists = new ArrayList<>();
		lists = service.selectTitledaoByAll();
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
		if (e.getSource() == btncancel) {
			do_btncancel_actionPerformed(e);
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
	private void do_mntmDel_actionPerformed(ActionEvent e) {
		try {
			Title selectedStd = tablePanel.getSelectedTitle();
			service.deleteTitle(selectedStd);
			lists = service.selectTitledaoByAll();
			tablePanel.setLists(lists);
			tablePanel.loadDatas();
			texttno.setText(service.nextTitleNo());
			btnAdd.setText(OK);
		}catch (Exception e1) {
			if (lists.size()==0) {
				JOptionPane.showMessageDialog(null, "정보가 없습니다.");
				return;
			}
			JOptionPane.showMessageDialog(null, "삭제하고자하는 직책이 직원관리에서 참조중입니다");
		}
		
	}

	private void do_mntmUpdate_actionPerformed(ActionEvent e) {
		
		Title selected = tablePanel.getSelectedTitle();
		texttno.setText(selected.getTno());
		textname.setText(selected.getTname());			
		btnAdd.setText("수정");		
	}
	private void do_btnUpdata_actionPerformed(ActionEvent e) {
		
		try {			
			Title newTitle = gettitle();
			
			int rowAffected = service.updateTitle(newTitle);
			JOptionPane.showMessageDialog(null,"UPDATE");
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			/*dao = null;*/
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		/*dao= new TitleDaoImpl();*/
		texttno.setText(service.nextTitleNo());
		textname.setText("");			
		lists = service.selectTitledaoByAll();
		tablePanel.setLists(lists);
		tablePanel.loadDatas();
		btnAdd.setText(OK);
		
	}

	protected void do_btnAdd_actionPerformed(ActionEvent e) {
		try {
			Title newTitle = gettitle();
			int rowAffected = service.insertTitle(newTitle);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			/*dao = null;*/
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		/*dao= new TitleDaoImpl();*/
		texttno.setText(service.nextTitleNo());
		textname.setText("");			
		lists = service.selectTitledaoByAll();
		tablePanel.setLists(lists);
		tablePanel.loadDatas();
		
	}
	private Title gettitle() {		
		String tno = texttno.getText().trim();
		String tname = textname.getText().trim();		
		return new Title(tno,tname);
	}
	protected void do_btncancel_actionPerformed(ActionEvent e) {
		texttno.setText(service.nextTitleNo());
		textname.setText("");
		btnAdd.setText(OK);
	}
}
