

import java.util.Scanner;
import java.util.InputMismatchException;

public class MilitaryAirport extends Airport {
    private int numberOfWeaponStorage;
    private int numberOfRadarStations;

    public MilitaryAirport() {

    }

    public MilitaryAirport(int numberOfWeaponStorage, int numberOfRadarStations) {
        super();
        this.numberOfWeaponStorage = numberOfWeaponStorage;
        this.numberOfRadarStations = numberOfRadarStations;
    }

    public int getNumberOfWeaponStorage() {
        return numberOfWeaponStorage;
    }

    public void setNumberOfWeaponStorage(int numberOfWeaponStorage) {
        this.numberOfWeaponStorage = numberOfWeaponStorage;
    }

    public int getNumberOfRadarStations() {
        return numberOfRadarStations;
    }

    public void setNumberOfRadarStations(int numberOfRadarStations) {
        this.numberOfRadarStations = numberOfRadarStations;
    }

    public void input() throws Exception {
        super.input();
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Nhập số kho chứa vũ khí: ");
            numberOfWeaponStorage = sc.nextInt();
            System.out.print("Nhập số trạm radar chiến lược: ");
            numberOfRadarStations = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Dữ liệu nhập không hợp lệ! Vui lòng nhập lại.");
            sc.nextLine(); // Clear buffer
        } finally {
            System.out.println("Hoàn tất quá trình nhập liệu cho sân bay quân sự.");
        }
    }

    public void output() {
        super.output();
        System.out.printf("%-30d %-30d", getNumberOfWeaponStorage(), getNumberOfRadarStations());
        System.out.println();
    }
}
