package GSach;

public class Sach extends Tailieu {
	private int NamXB;
	private int GiaB;
	
	public Sach() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Sach(String maS, String tenS) {
		super(maS, tenS);
		// TODO Auto-generated constructor stub
	}
	
	public Sach(String maS, String tenS, int namXB, int giaB) {
		super();
		NamXB = namXB;
		GiaB = giaB;
	}
	
	public int getNamXB() {
		return NamXB;
	}
	
	public void setNamXB(int namXB) {
		NamXB = namXB;
	}
	
	public int getGiaB() {
		return GiaB;
	}
	
	public void setGiaB(int giaB) {
		GiaB = giaB;
	}
}
