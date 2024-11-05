

import java.util.Scanner;
import java.util.InputMismatchException;

public class InternationalAirport extends Airport {
    private int numberOfRunways;
    private int numberOfAirplanes;
    private int passengerCapacity;

    public InternationalAirport() {

    }

    public InternationalAirport(int numberOfRunways, int numberOfAirplanes, int passengerCapacity) {
        super();
        this.numberOfRunways = numberOfRunways;
        this.numberOfAirplanes = numberOfAirplanes;
        this.passengerCapacity = passengerCapacity;
    }

    public int getNumberOfRunways() {
        return numberOfRunways;
    }

    public void setNumberOfRunways(int numberOfRunways) {
        this.numberOfRunways = numberOfRunways;
    }

    public int getNumberOfAirplanes() {
        return numberOfAirplanes;
    }

    public void setNumberOfAirplanes(int numberOfAirplanes) {
        this.numberOfAirplanes = numberOfAirplanes;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public void input() throws Exception {
        super.input();
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Nhập số đường bay: ");
            numberOfRunways = sc.nextInt();
            System.out.print("Nhập số máy bay: ");
            numberOfAirplanes = sc.nextInt();
            System.out.print("Nhập sức chứa hành khách: ");
            passengerCapacity = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Dữ liệu nhập không hợp lệ! Vui lòng nhập lại.");
            sc.nextLine(); // Clear buffer
        } finally {
            System.out.println("Hoàn tất quá trình nhập liệu cho sân bay quốc tế.");
        }
    }

    public void output() {
        super.output();
        System.out.printf("%-30d %-30d %-30d", getNumberOfRunways(), getNumberOfAirplanes(), getPassengerCapacity());
        System.out.println();
    }
}
