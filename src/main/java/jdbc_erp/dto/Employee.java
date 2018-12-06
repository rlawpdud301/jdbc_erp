package jdbc_erp.dto;

import java.util.Date;

public class Employee {
	private String empno;
	private String empname;
	private Title title;	
	private int salary;
	private boolean gender;	
	private Department dno;
	private Date entrydaty;
	
	
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String empno, String empname, Title title, int salary, boolean gender, Department dno, Date joindate) {
		this.empno = empno;
		this.empname = empname;
		this.title = title;
		this.salary = salary;
		this.gender = gender;
		this.dno = dno;
		this.entrydaty = joindate;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getEntrydaty() {
		return entrydaty;
	}

	public void setEntrydaty(Date entrydaty) {
		this.entrydaty = entrydaty;
	}

	public Department getDno() {
		return dno;
	}

	public void setDno(Department dno) {
		this.dno = dno;
	}

	

	public Employee(String empno) {
		this.empno = empno;
	}

	@Override
	public String toString() {
		return String.format("Employee [empno=%s, empname=%s, title=%s, salary=%s, gender=%s, dno=%s, entrydaty=%s]",
				empno, empname, title, salary, gender, dno, entrydaty);
	}

	
	
	
}
