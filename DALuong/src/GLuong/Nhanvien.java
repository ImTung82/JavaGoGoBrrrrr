package GLuong;

public class Nhanvien extends Person {
	private String Diachi;
	private int Luong;
	
	public Nhanvien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Nhanvien(String maVN, String hoten) {
		super(maVN, hoten);
		// TODO Auto-generated constructor stub
	}
	public String getDiachi() {
		return Diachi;
	}
	public void setDiachi(String diachi) {
		Diachi = diachi;
	}
	public int getLuong() {
		return Luong;
	}
	public void setLuong(int luong) {
		Luong = luong;
	}
}
