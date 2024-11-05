

import java.util.Scanner;
import java.util.InputMismatchException;

public class Airport {
    private String airportName;
    private String airportCode;
    private String airportAddress;

    public Airport() {

    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportAddress() {
        return airportAddress;
    }

    public void setAirportAddress(String airportAddress) {
        this.airportAddress = airportAddress;
    }

    public Airport(String airportName, String airportCode, String airportAddress) {
        this.airportName = airportName;
        this.airportCode = airportCode;
        this.airportAddress = airportAddress;
    }

    public void input() throws Exception {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Nhập tên sân bay: ");
            airportName = sc.nextLine();

            System.out.print("Nhập mã sân bay: ");
            airportCode = sc.nextLine();

            System.out.print("Nhập địa chỉ sân bay: ");
            airportAddress = sc.nextLine();
        } catch (Exception e) {
            throw new Exception("Đã xảy ra lỗi khi nhập thông tin sân bay: " + e.getMessage());
        }
    }

    public void output() {
        System.out.printf("%-30s %-15s %-31s", getAirportName(), getAirportCode(), getAirportAddress());
    }
}
