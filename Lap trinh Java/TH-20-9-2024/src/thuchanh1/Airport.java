package thuchanh1;

import java.io.Serializable;
import java.util.Scanner;

public class Airport implements Serializable {
	private String airportName;
	private String airportCode;
	private String airportAddress;
	
	public Airport() {
		
	}
	
	public Airport(String airportName, String airportCode, String airportAddress) {
		this.airportName = airportName;
		this.airportCode = airportCode;
		this.airportAddress = airportAddress;
	}
	
	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) throws IllegalArgumentException {
		if (airportName.trim().equals("")) {
			throw new IllegalArgumentException("Vui lòng không để trống.\n");
		}
		this.airportName = airportName;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) throws IllegalArgumentException {
		if (airportCode.trim().equals("")) {
			throw new IllegalArgumentException("Vui lòng không để trống.\n");
		}
		this.airportCode = airportCode;
	}

	public String getAirportAddress() {
		return airportAddress;
	}

	public void setAirportAddress(String airportAddress) throws IllegalArgumentException {
		if (airportAddress.trim().equals("")) {
			throw new IllegalArgumentException("Vui lòng không để trống.\n");
		}
		this.airportAddress = airportAddress;
	}
	
	public void input() {
		Scanner sc = new Scanner(System.in);
		boolean isValid = false;
		
		do {
			try {
				System.out.print("Nhập tên sân bay: ");
				airportName = sc.nextLine();
				setAirportName(airportName);
				
				System.out.print("Nhập mã sân bay: ");
				airportCode = sc.nextLine();
				setAirportCode(airportCode);
				
				System.out.print("Nhập địa chỉ sân bay: ");
				airportAddress = sc.nextLine();
				setAirportAddress(airportAddress);
				
				isValid = true;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} while (!isValid);
	}
	
	public void output() {
		System.out.printf("%-30s %-15s %-31s", getAirportName(), getAirportCode(), getAirportAddress());
	}
}
