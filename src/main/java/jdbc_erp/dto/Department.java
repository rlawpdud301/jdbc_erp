package jdbc_erp.dto;

public class Department {
	private String deptno;
	private String deptname;
	private int floor;
	public Department() {
		// TODO Auto-generated constructor stub
	}
	public Department(String deptno, String deptname, int floor) {
		this.deptno = deptno;
		this.deptname = deptname;
		this.floor = floor;
	}
	public Department(String deptno) {
		this.deptno = deptno;
	}
	public String getDeptno() {
		return deptno;
	}
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	@Override
	public String toString() {
		return String.format("%s(%s층)",  deptname, floor);
	}
	
	
	
	
	
}
