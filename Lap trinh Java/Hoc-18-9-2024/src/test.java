import java.util.Scanner;

class Airport {
	private String airport_name;
	private String airport_code;
	
	public Airport() {
		
	}
	
	public Airport(String airport_name, String airport_code) {
		this.airport_name = airport_name;
		this.airport_code = airport_code;
	}
	
	public String getairport_name() {
		return airport_name;
	}
	
	public void setairport_name(String airport_name) {
		this.airport_name = airport_name;
	}
	
	String getairport_code() {
		return airport_code;
	}
	
	public void setairport_code(String airport_code) {
		this.airport_code = airport_code;
	}
	
	public void input() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Nhập tên sân bay: ");
		airport_name = sc.nextLine();
		
		System.out.print("Nhập mã sân bay: ");
		airport_code = sc.nextLine();
	}
	
	public void output() {
		System.out.println("Tên sân bay: " + airport_name);
		System.out.println("Mã sân bay: " + airport_code);
	}
}

class Runway extends Airport {
	private int runway_code;
	private float runway_length;
	
	public Runway() {
		
	}	
	
	public Runway(String airport_name, String airport_code, int runway_code, float runway_length) {
		super(airport_name, airport_code);
		this.runway_code = runway_code;
		this.runway_length = runway_length;
	}
	
	public int getRunway_code() {
		return runway_code;
	}
	
	public void setRunway_code(int runway_code) {
		this.runway_code = runway_code;
	}
	
	public float getRunway_length() {
		return runway_length;
	}
	
	public void setRunway_length(float runway_length) {
		this.runway_length = runway_length;
	}
	
	public void input() {
		super.input();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Nhập mã số đường bay: ");
		runway_code = sc.nextInt();
		
		System.out.print("Nhập độ dài đường bay (m): ");
		runway_length = sc.nextFloat();
	}
	
	public void output() {
		super.output();
		
		System.out.println("Mã số đường bay: " + runway_code);
		System.out.println("Độ dài đường bay: " + runway_length + "m\n");
	}
}

class Gate extends Airport {
	private int gate_number;
	private String gate_address;
	
	public Gate() {
		
	}
	
	public Gate(String airport_name, String airport_code, int gate_number, String gate_address) {
		super(airport_name, airport_code);
		this.gate_number = gate_number;
		this.gate_address = gate_address;
	}
	
	public int getGate_number() {
		return gate_number;
	}
	
	public void setGate_number(int gate_number) {
		this.gate_number = gate_number;
	}
	
	public String getGate_address() {
		return gate_address;
	}
	
	public void setGate_address(String gate_address) {
		this.gate_address = gate_address;
	}
	
	public void input() {
		super.input();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Nhập mã số cổng: ");
		gate_number = sc.nextInt();
		
		sc.nextLine();
		System.out.print("Nhập vị trí cổng: ");
		gate_address = sc.nextLine();
	}
	
	public void output() {
		super.output();
		
		System.out.println("Cổng: " + gate_number);
		System.out.println("Vị trí cổng: " + gate_address + "\n");
	}
}

public class test {

	public static void main(String[] args) {
		Runway runway1 = new Runway();
		
		System.out.println("Nhập thông tin đường bay:\n");
		runway1.input();
		
		System.out.println("\n=========================\nThông tin đường bay:\n");
		runway1.output();
		
		Gate gate1 = new Gate();
		
		System.out.println("Nhập thông tin cổng:\n");
		gate1.input();
		
		System.out.println("\n=========================\nThông tin cổng:\n");
		gate1.output();
	}

}
