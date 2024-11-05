package thuchanh;

import java.util.InputMismatchException;
import java.util.Scanner;

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

	public void setNumberOfWeaponStorage(int numberOfWeaponStorage) throws IllegalArgumentException {
		if (numberOfWeaponStorage < 0) {
			throw new IllegalArgumentException("\nVui lòng nhập một số nguyên dương.\n");
		}
		this.numberOfWeaponStorage = numberOfWeaponStorage;
	}

	public int getNumberOfRadarStations() {
		return numberOfRadarStations;
	}

	public void setNumberOfRadarStations(int numberOfRadarStations) throws IllegalArgumentException {
		if (numberOfRadarStations < 0) {
			throw new IllegalArgumentException("\nVui lòng nhập một số nguyên dương.\n");
		}
		this.numberOfRadarStations = numberOfRadarStations;
	}

	public void input() {
		super.input();
		boolean isValid = false;
		
		Scanner sc = new Scanner(System.in);
		
		do {
			try {
				System.out.print("Nhập số kho chứa vũ khí: ");
				numberOfWeaponStorage = sc.nextInt();
				setNumberOfWeaponStorage(numberOfWeaponStorage);
				
				System.out.print("Nhập số trạm radar chiến lược: ");
				numberOfRadarStations = sc.nextInt();
				setNumberOfRadarStations(numberOfRadarStations);
				
				isValid = true;
			} catch (InputMismatchException e) {
	            System.out.println("\nĐầu vào không hợp lệ, vui lòng nhập một số nguyên.\n");
	            sc.nextLine(); 
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} while(!isValid);
	}
	
	public void output() {
		super.output();
		
		System.out.printf("%-30d %-30d", getNumberOfWeaponStorage(), getNumberOfRadarStations());
		System.out.println();
	}
}
