package GSach;

public class Sach {
	private String MaS;
	private String TenS;
	private String NhaXB;
	private int NamXB;
	private int GiaB;
	
	public Sach() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sach(String maS, String tenS, String nhaXB, int namXB, int giaB) {
		super();
		MaS = maS;
		TenS = tenS;
		NhaXB = nhaXB;
		NamXB = namXB;
		GiaB = giaB;
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

	public String getNhaXB() {
		return NhaXB;
	}

	public void setNhaXB(String nhaXB) {
		NhaXB = nhaXB;
	}

	public int getNamXB() {
		return NamXB;
	}

	public void setNamXB(int namXB) {
		NamXB = namXB;
	}

	public double getGiaB() {
		return GiaB;
	}

	public void setGiaB(int giaB) {
		GiaB = giaB;
	}
	
	public double Khuyenmai(int year, double giaB) {
		if (year < 2019) {
			giaB = giaB * 0.25;
		} else giaB = 0;
		return giaB;
	}
}
