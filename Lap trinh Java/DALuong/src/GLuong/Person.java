package GLuong;

public class Person {
	private String MaVN;
	private String Hoten;
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String maVN, String hoten) {
		super();
		MaVN = maVN;
		Hoten = hoten;
	}

	public String getMaVN() {
		return MaVN;
	}

	public void setMaVN(String maVN) {
		MaVN = maVN;
	}

	public String getHoten() {
		return Hoten;
	}

	public void setHoten(String hoten) {
		Hoten = hoten;
	}
}
