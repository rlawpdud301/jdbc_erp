package jdbc_erp;

import java.awt.EventQueue;

import jdbc_erp.UI.ERPMainUI;

public class Main {
	

	public static void main(String[] args) {
				
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ERPMainUI frame = new ERPMainUI();
						frame.setTitle("ERP관리프로그램");
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

}
