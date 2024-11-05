package thuchanh1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class TestMain {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Airport> Airport_list = new ArrayList<Airport>();
		
		while(true) {
			int choice = -1;
			
            try {
                System.out.println("* CHƯƠNG TRÌNH QUẢN LÝ SÂN BAY *\n");
                System.out.println("=======================");
                System.out.println("1. Thêm sân bay");
                System.out.println("2. Xóa sân bay");
                System.out.println("3. Hiển thị danh sách sân bay");
                System.out.println("4. Tìm kiếm sân bay");
                System.out.println("5. Lưu dữ liệu vào file");
                System.out.println("6. Tải dữ liệu từ file");
                System.out.println("7. Lưu dữ liệu vào file nhị phân");
                System.out.println("8. Tải dữ liệu từ file nhị phân\n");
                System.out.println("0. Dừng chương trình");
                System.out.println("=======================");

                System.out.print("\nVui lòng nhập lựa chọn của bạn: ");
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Đầu vào không hợp lệ, vui lòng nhập một số nguyên.\n\n\n");
                sc.nextLine(); 
                continue;
            }
			
			switch (choice) {
				case 1:
					int add_choice;
					System.out.print("Nhap Lua Chon (1: International Airport || 2: Military Airport): ");
					add_choice = sc.nextInt();
					
					if (add_choice == 1) {
						InternationalAirport temp = new InternationalAirport();
						temp.input();
						Airport_list.add(temp);
						System.out.println("\n\n\n");
					} else if (add_choice == 2) {
						MilitaryAirport temp = new MilitaryAirport();
						temp.input();
						Airport_list.add(temp);
						System.out.println("\n\n\n");
					}
					
					break;
				
				case 2:
				    int delete_choice;
					
					if (Airport_list.size() == 0) {
						System.out.println("Không có dữ liệu để xóa.\n\n\n");
						break;
					}
					
					System.out.println("\n");
					System.out.println("==========================");
					System.out.println("Danh sách sân bay: ");
					System.out.println("==========================");
					
                    for (int i = 0; i < Airport_list.size(); ++i) {
                        Airport airport = Airport_list.get(i);
                        
                        System.out.println("STT: " + (i+1));
                        
                        if (airport instanceof InternationalAirport) {
                            System.out.println("Loại sân bay: International Airport");
                            System.out.printf("%-30s %-15s %-30s %-30s %-30s %-30s\n", "Tên", "Mã", "Địa chỉ", "Số đường bay", "Số lượng máy bay", "Sức chứa hành khách");
                            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            ((InternationalAirport) airport).output();
                            System.out.println("\n");
                        } else if (airport instanceof MilitaryAirport) {
                            System.out.println("Loại sân bay: Military Airport");
                            System.out.printf("%-30s %-15s %-30s %-30s %-30s\n", "Tên", "Mã", "Địa chỉ", "Số lượng kho chứa vũ khí", "Số lượng trạm radar chiến lược");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
                            ((MilitaryAirport) airport).output();
                            System.out.println("\n");
                        }
                    }
					
					System.out.println("\n\n");

				    System.out.print("Nhập STT của sân bay muốn xóa: ");
				    delete_choice = sc.nextInt();

				    Airport_list.remove(delete_choice - 1);
					System.out.println("\nXóa thành công.\n\n");
				    
					break;
					
				case 3:
					if (Airport_list.size() == 0) {
						System.out.println("Không có dữ liệu để hiển thị.\n\n\n");
						break;
					}
					
					System.out.println("\n");
					System.out.println("==========================");
					System.out.println("Danh sách sân bay: ");
					System.out.println("==========================");

                    for (int i = 0; i < Airport_list.size(); ++i) {
                        Airport airport = Airport_list.get(i);

                        if (airport instanceof InternationalAirport) {
                            System.out.println("\nLoại sân bay: International Airport");
                            System.out.printf("%-30s %-15s %-30s %-30s %-30s %-30s\n", "Tên", "Mã", "Địa chỉ", "Số đường bay", "Số lượng máy bay", "Sức chứa hành khách");
                            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            ((InternationalAirport) airport).output();
                        } else if (airport instanceof MilitaryAirport) {
                            System.out.println("\nLoại sân bay: Military Airport");
                            System.out.printf("%-30s %-15s %-30s %-30s %-30s\n", "Tên", "Mã", "Địa chỉ", "Số lượng kho chứa vũ khí", "Số lượng trạm radar chiến lược");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
                            ((MilitaryAirport) airport).output();
                        }
                    }
					
					System.out.println("\n\n\n");
					
					break;
				
				case 4:
					boolean found = false;
					
					String name_finding;
					
					System.out.print("Nhập tên sân bay muốn tìm: ");
					name_finding = sc.next();
					
					for (int i = 0; i < Airport_list.size(); ++i) {
						if (Airport_list.get(i).getAirportName().equals(name_finding)) {
	                        if (Airport_list.get(i) instanceof InternationalAirport) {
	                            System.out.println("\nLoại sân bay: International Airport");
	                            System.out.printf("%-30s %-15s %-30s %-30s %-30s %-30s\n", "Tên", "Mã", "Địa chỉ", "Số đường bay", "Số lượng máy bay", "Sức chứa hành khách");
	                            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
	                            ((InternationalAirport) Airport_list.get(i)).output();
	                            System.out.println("\n");
	                        } else if (Airport_list.get(i) instanceof MilitaryAirport) {
	                            System.out.println("\nLoại sân bay: Military Airport");
	                            System.out.printf("%-30s %-15s %-30s %-30s %-30s\n", "Tên", "Mã", "Địa chỉ", "Số lượng kho chứa vũ khí", "Số lượng trạm radar chiến lược");
	                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
	                            ((MilitaryAirport) Airport_list.get(i)).output();
	                            System.out.println("\n");
	                        }
	                        
	                        found = true;
	                        break;
						}
					}
					
					if (!found) {
						System.out.println("\nKhông tìm thấy sân bay bạn đã nhập.\n\n\n");
					}
					
					break;
					
				case 5:
				    String outputFileName = "C:\\Tung_TLU\\TLU_Hoc\\2024_2025(Nam 3)\\Lap trinh Java\\TH-20-9-2024\\file.txt";

				    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
				        for (Airport airport : Airport_list) {
				            if (airport instanceof InternationalAirport) {
				                writer.write("International\n");
				                InternationalAirport ia = (InternationalAirport) airport;
				                writer.write(ia.getAirportName() + "\n");
				                writer.write(ia.getAirportCode() + "\n");
				                writer.write(ia.getAirportAddress() + "\n");
				                writer.write(ia.getNumberOfRunways() + "\n");
				                writer.write(ia.getNumberOfAirplanes() + "\n");
				                writer.write(ia.getPassengerCapacity() + "\n");
				            } else if (airport instanceof MilitaryAirport) {
				                writer.write("Military\n");
				                MilitaryAirport ma = (MilitaryAirport) airport;
				                writer.write(ma.getAirportName() + "\n");
				                writer.write(ma.getAirportCode() + "\n");
				                writer.write(ma.getAirportAddress() + "\n");
				                writer.write(ma.getNumberOfWeaponStorage() + "\n");
				                writer.write(ma.getNumberOfRadarStations() + "\n");
				            }
				        }
				        System.out.println("Ghi dữ liệu vào file thành công. \n\n\n");
				    } catch (IOException e) {
				        e.printStackTrace();
				    }
				    break;

				case 6:
				    String inputFileName = "C:\\Tung_TLU\\TLU_Hoc\\2024_2025(Nam 3)\\Lap trinh Java\\TH-20-9-2024\\file.txt";

				    try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
				        String line;
				        while ((line = reader.readLine()) != null) {
				            if (line.equals("International")) {
				                InternationalAirport ia = new InternationalAirport();
				                ia.setAirportName(reader.readLine());
				                ia.setAirportCode(reader.readLine());
				                ia.setAirportAddress(reader.readLine());
				                ia.setNumberOfRunways(Integer.parseInt(reader.readLine()));
				                ia.setNumberOfAirplanes(Integer.parseInt(reader.readLine()));
				                ia.setPassengerCapacity(Integer.parseInt(reader.readLine()));
				                Airport_list.add(ia);
				            } else if (line.equals("Military")) {
				                MilitaryAirport ma = new MilitaryAirport();
				                ma.setAirportName(reader.readLine());
				                ma.setAirportCode(reader.readLine());
				                ma.setAirportAddress(reader.readLine());
				                ma.setNumberOfWeaponStorage(Integer.parseInt(reader.readLine()));
				                ma.setNumberOfRadarStations(Integer.parseInt(reader.readLine()));
				                Airport_list.add(ma);
				            }
				        }
				        System.out.println("Đọc dữ liệu từ file thành công. \n\n\n");
				    } catch (IOException e) {
				        e.printStackTrace();
				    }
				    break;
				    
                case 7: 
                    String serializedFileName = "C:\\Tung_TLU\\TLU_Hoc\\2024_2025(Nam 3)\\Lap trinh Java\\TH-20-9-2024\\airports.dat";
                    
                    try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(serializedFileName))) {
                        os.writeObject(Airport_list);
                        System.out.println("Dữ liệu đã được lưu thành công.\n\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 8:
                    serializedFileName = "C:\\Tung_TLU\\TLU_Hoc\\2024_2025(Nam 3)\\Lap trinh Java\\TH-20-9-2024\\airports.dat";
                    
                    try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(serializedFileName))) {
                        Airport_list = (ArrayList<Airport>)os.readObject();
                        System.out.println("Dữ liệu đã được tải thành công.\n\n\n");
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
					
				case 0:
					System.out.println("\n* Thông báo: Dừng chương trình thành công.");
					sc.close();
					return;
					
				default:
					System.out.println("Đầu vào không hợp lệ. Vui lòng lựa chọn lại.\n\n\n");
					continue;
			}
		}
	}
}
