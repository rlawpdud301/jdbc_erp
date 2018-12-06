package jdbc_erp.dto;

public class Title {
	public Title(String tno, String tname) {
		this.tno = tno;
		this.tname = tname;
	}
	private String tno;
	private String tname;
	@Override
	public String toString() {
		return String.format(tname);
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Title(String tno) {
		this.tno = tno;
	}
	
	
	public Title() {
		// TODO Auto-generated constructor stub
	}
}
