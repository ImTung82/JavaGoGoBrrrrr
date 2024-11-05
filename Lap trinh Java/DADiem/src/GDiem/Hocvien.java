package GDiem;

public class Hocvien {
	private String MaHV;
	private String Hoten;
	private String Lop;
	private String GT;
	private float Diem;
	
	public Hocvien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hocvien(String maHV, String hoten, String lop, String gT, float diem) {
		super();
		MaHV = maHV;
		Hoten = hoten;
		Lop = lop;
		GT = gT;
		Diem = diem;
	}

	public String getMaHV() {
		return MaHV;
	}

	public void setMaHV(String maHV) {
		MaHV = maHV;
	}

	public String getHoten() {
		return Hoten;
	}

	public void setHoten(String hoten) {
		Hoten = hoten;
	}

	public String getLop() {
		return Lop;
	}

	public void setLop(String lop) {
		Lop = lop;
	}

	public String getGT() {
		return GT;
	}

	public void setGT(String gT) {
		GT = gT;
	}

	public float getDiem() {
		return Diem;
	}

	public void setDiem(float diem) {
		Diem = diem;
	}
	
	public String Ketqua(float Diem) {
		if (Diem >= 20) {
			return "Đỗ";
		} else {
			return "Trượt";
		}
	}
}
