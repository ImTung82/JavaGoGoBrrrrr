package thuchanh;

import java.util.InputMismatchException;
import java.util.Scanner;

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
	
	public void setNumberOfRunways(int numberOfRunways) throws IllegalArgumentException {
		if (numberOfRunways < 0) {
			throw new IllegalArgumentException("\nVui lòng nhập một số nguyên dương.\n");
		}
		this.numberOfRunways = numberOfRunways;
	}
	
	public int getNumberOfAirplanes() {
		return numberOfAirplanes;
	}
	
	public void setNumberOfAirplanes(int numberOfAirplanes) throws IllegalArgumentException {
		if (numberOfAirplanes < 0) {
			throw new IllegalArgumentException("\nVui lòng nhập một số nguyên dương.\n");
		}
		this.numberOfAirplanes = numberOfAirplanes;
	}
	
	public int getPassengerCapacity() {
		return passengerCapacity;
	}
	
	public void setPassengerCapacity(int passengerCapacity) throws IllegalArgumentException {
		if (passengerCapacity < 0) {
			throw new IllegalArgumentException("Vui lòng nhập một số nguyên dương.\n");
		}
		this.passengerCapacity = passengerCapacity;
	}
	
	public void input() {
		super.input();
		boolean isValid = false;
		
		Scanner sc = new Scanner(System.in);
		
		do {
			try {			
				System.out.print("Nhập số đường bay: ");
				numberOfRunways = sc.nextInt();
				setNumberOfRunways(numberOfRunways);
				sc.nextLine();
				
				System.out.print("Nhập số máy bay: ");
				numberOfAirplanes = sc.nextInt();
				setNumberOfAirplanes(numberOfAirplanes);
				sc.nextLine();
				
				System.out.print("Nhập sức chứa hành khách: ");
				passengerCapacity = sc.nextInt();
				setPassengerCapacity(passengerCapacity);
				sc.nextLine();
				
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
		
		System.out.printf("%-30d %-30d %-30d", getNumberOfRunways(), getNumberOfAirplanes(), getPassengerCapacity());
		System.out.println();
	}
}
