package GSach;

public class Tailieu {
	private String MaS;
	private String TenS;
	
	public Tailieu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tailieu(String maS, String tenS) {
		super();
		MaS = maS;
		TenS = tenS;
	}
	public String getMaS() {
		return MaS;
	}
	public void setMaS(String maS) {
		MaS = maS;
	}
	public String getTenS() {
		return TenS;
	}
	public void setTenS(String tenS) {
		TenS = tenS;
	}
	
	public double Thanhtien(int year, double GiaB) {
		if (year < 2015) {
			GiaB = GiaB * 0.85;
		} else {
			GiaB = GiaB * 0.95;
		}
		
		return GiaB;
	}
}
