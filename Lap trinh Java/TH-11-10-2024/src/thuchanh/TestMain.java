package thuchanh;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.Scanner;

public class TestMain extends JFrame {
//	private JLabel nameLabel, codeLabel, addressLabel, typeLabel, runwayLabel, airplaneLabel, capacityLabel, weaponStorageLabel, radarLabel;
//	private JTextField nameField, codeField, addressField, runwayField, airplaneField, capacityField, weaponStorageField, radarField;
//	private JComboBox<String> typeComboBox;
//	private JPanel inputPanel, buttonMenuPanel;
//	private JButton addButton, deleteButton; 
//	private JTable airplaneTable;
//	private DefaultTableModel tableModel;
//	private JScrollPane tableScrollPane;
//	private JMenuBar menuBar;
//	
//	String internationalColumnNames[] = {"Tên", "Mã sân bay", "Địa chỉ", "Số đường bay", "Số máy bay", "Sức chứa"};
//	String militaryColumnNames[] = {"Tên", "Mã sân bay,", "Địa chỉ", "Số kho chứa vũ khí", "Số trạm radar chiến lược"};
//	
//	public TestMain() {
//		appGUI();
//	}
//	
//	public void appGUI() {
//        setTitle("Airport Management");
//        setSize(900, 700);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//        
//        // Xây dựng MenuBar
//        menuBar = new JMenuBar();
//        JMenu fileMenu = new JMenu("File");
//        JMenuItem openItem = new JMenuItem("Open");
//        fileMenu.add(openItem);
//        
//        // Thêm menu vào menuBar
//        menuBar.add(fileMenu);
//
//        // Thêm menuBar vào JFrame
//        setJMenuBar(menuBar);
//
//        // Xây dựng Panel Nhập thông tin
//        inputPanel = new JPanel(new GridLayout(0, 2, 15, 15));
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        typeLabel = new JLabel("Loại sân bay:");
//        inputPanel.add(typeLabel);
//        typeComboBox = new JComboBox<>(new String[] {"International Airport", "Military Airport"});
//        inputPanel.add(typeComboBox);
//
//        nameLabel = new JLabel("Tên:");
//        inputPanel.add(nameLabel);
//        nameField = new JTextField();
//        inputPanel.add(nameField);
//
//        codeLabel = new JLabel("Mã sân bay:");
//        inputPanel.add(codeLabel);
//        codeField = new JTextField();
//        inputPanel.add(codeField);
//
//        addressLabel = new JLabel("Địa chỉ:");
//        inputPanel.add(addressLabel);
//        addressField = new JTextField();
//        inputPanel.add(addressField);
//
//        // Gọi phương thức để cập nhật các trường nhập liệu
//        updateFields();
//
//        // Thêm ActionListener cho JComboBox
//        typeComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateFields(); // Cập nhật các trường nhập liệu khi loại sân bay thay đổi
//            }
//        });
//
//        add(inputPanel, BorderLayout.NORTH);
//        
//        // Xây dựng Panel các nút chức năng
//        buttonMenuPanel = new JPanel(new FlowLayout());
//        buttonMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//        
//        addButton = new JButton("Thêm máy bay");
//        buttonMenuPanel.add(addButton);
//        
//        deleteButton = new JButton("Xóa máy bay");
//        buttonMenuPanel.add(deleteButton);
//        
//        add(buttonMenuPanel, BorderLayout.SOUTH);
//    }
//
//    private void updateFields() {
//        // Xóa các trường hiện có trong inputPanel
//        if (runwayLabel != null) inputPanel.remove(runwayLabel);
//        if (runwayField != null) inputPanel.remove(runwayField);
//        if (airplaneLabel != null) inputPanel.remove(airplaneLabel);
//        if (airplaneField != null) inputPanel.remove(airplaneField);
//        if (capacityLabel != null) inputPanel.remove(capacityLabel);
//        if (capacityField != null) inputPanel.remove(capacityField);
//        if (weaponStorageLabel != null) inputPanel.remove(weaponStorageLabel);
//        if (weaponStorageField != null) inputPanel.remove(weaponStorageField);
//        if (radarLabel != null) inputPanel.remove(radarLabel);
//        if (radarField != null) inputPanel.remove(radarField);
//
//        // Xóa bảng hiện tại (nếu có)
//        if (tableScrollPane != null) {
//            remove(tableScrollPane);
//        }
//        
//        // Lấy loại sân bay được chọn
//        String selectedType = (String)typeComboBox.getSelectedItem();
//
//        if ("International Airport".equals(selectedType)) {
//            runwayLabel = new JLabel("Số đường bay:");
//            inputPanel.add(runwayLabel);
//            runwayField = new JTextField();
//            inputPanel.add(runwayField);
//
//            airplaneLabel = new JLabel("Số máy bay:");
//            inputPanel.add(airplaneLabel);
//            airplaneField = new JTextField();
//            inputPanel.add(airplaneField);
//
//            capacityLabel = new JLabel("Sức chứa tối đa:");
//            inputPanel.add(capacityLabel);
//            capacityField = new JTextField();
//            inputPanel.add(capacityField);
//            
//            // Xây dựng bảng hiển thị dữ liệu
//            tableModel = new DefaultTableModel(internationalColumnNames, 0);
//            airplaneTable = new JTable(tableModel);
//            tableScrollPane = new JScrollPane(airplaneTable);
//            add(tableScrollPane, BorderLayout.CENTER); // Thêm bảng vào bên phải của khung chính
//            
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            setVisible(true);  
//        } else if ("Military Airport".equals(selectedType)) {
//        	weaponStorageLabel = new JLabel("Số kho vũ khí:");
//            inputPanel.add(weaponStorageLabel);
//            weaponStorageField = new JTextField();
//            inputPanel.add(weaponStorageField);
//            
//            radarLabel = new JLabel("Số trạm radar chiến lược:");
//            inputPanel.add(radarLabel);
//            radarField = new JTextField();
//            inputPanel.add(radarField);
//            
//            tableModel = new DefaultTableModel(militaryColumnNames, 0);
//            airplaneTable = new JTable(tableModel);
//            tableScrollPane = new JScrollPane(airplaneTable);
//            add(tableScrollPane, BorderLayout.CENTER); // Thêm bảng vào bên phải của khung chính
//            
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            setVisible(true);  
//        }
//
//        // Cập nhật lại giao diện người dùng
//        inputPanel.revalidate();
//        inputPanel.repaint();
//	}
	
	public static void main(String[] args) throws Exception {
        // Đảm bảo giao diện người dùng được khởi tạo trên luồng sự kiện
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AirportFrame airportFrame = new AirportFrame();
                airportFrame.appGUI(); // Gọi phương thức để tạo giao diện
                airportFrame.setVisible(true); // Hiển thị cửa sổ
            }
        });
		
//		while(true) {
//			int choice = -1;
//			
//            try {
//                System.out.println("* CHƯƠNG TRÌNH QUẢN LÝ SÂN BAY *\n");
//                System.out.println("=======================");
//                System.out.println("1. Thêm sân bay");
//                System.out.println("2. Xóa sân bay");
//                System.out.println("3. Hiển thị danh sách sân bay");
//                System.out.println("4. Tìm kiếm sân bay");
//                System.out.println("5. Lưu dữ liệu vào file");
//                System.out.println("6. Tải dữ liệu từ file");
//                System.out.println("7. Lưu dữ liệu vào file nhị phân");
//                System.out.println("8. Tải dữ liệu từ file nhị phân\n");
//                System.out.println("0. Dừng chương trình");
//                System.out.println("=======================");
//
//                System.out.print("\nVui lòng nhập lựa chọn của bạn: ");
//                choice = sc.nextInt();
//            } catch (InputMismatchException e) {
//                System.out.println("Đầu vào không hợp lệ, vui lòng nhập một số nguyên.\n\n\n");
//                sc.nextLine(); 
//                continue;
//            }
//			
//			switch (choice) {
//				case 1:
//					int add_choice;
//					System.out.print("Nhap Lua Chon (1: International Airport || 2: Military Airport): ");
//					add_choice = sc.nextInt();
//					
//					if (add_choice == 1) {
//						InternationalAirport temp = new InternationalAirport();
//						temp.input();
//						Airport_list.add(temp);
//						System.out.println("\n\n\n");
//					} else if (add_choice == 2) {
//						MilitaryAirport temp = new MilitaryAirport();
//						temp.input();
//						Airport_list.add(temp);
//						System.out.println("\n\n\n");
//					}
//					
//					break;
//				
//				case 2:
//				    int delete_choice;
//					
//					if (Airport_list.size() == 0) {
//						System.out.println("Không có dữ liệu để xóa.\n\n\n");
//						break;
//					}
//					
//					System.out.println("\n");
//					System.out.println("==========================");
//					System.out.println("Danh sách sân bay: ");
//					System.out.println("==========================");
//					
//                    for (int i = 0; i < Airport_list.size(); ++i) {
//                        Airport airport = Airport_list.get(i);
//                        
//                        System.out.println("STT: " + (i+1));
//                        
//                        if (airport instanceof InternationalAirport) {
//                            System.out.println("Loại sân bay: International Airport");
//                            System.out.printf("%-30s %-15s %-30s %-30s %-30s %-30s\n", "Tên", "Mã", "Địa chỉ", "Số đường bay", "Số lượng máy bay", "Sức chứa hành khách");
//                            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
//                            ((InternationalAirport) airport).output();
//                            System.out.println("\n");
//                        } else if (airport instanceof MilitaryAirport) {
//                            System.out.println("Loại sân bay: Military Airport");
//                            System.out.printf("%-30s %-15s %-30s %-30s %-30s\n", "Tên", "Mã", "Địa chỉ", "Số lượng kho chứa vũ khí", "Số lượng trạm radar chiến lược");
//                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
//                            ((MilitaryAirport) airport).output();
//                            System.out.println("\n");
//                        }
//                    }
//					
//					System.out.println("\n\n");
//
//				    System.out.print("Nhập STT của sân bay muốn xóa: ");
//				    delete_choice = sc.nextInt();
//
//				    Airport_list.remove(delete_choice - 1);
//					System.out.println("\nXóa thành công.\n\n");
//				    
//					break;
//					
//				case 3:
//					if (Airport_list.size() == 0) {
//						System.out.println("Không có dữ liệu để hiển thị.\n\n\n");
//						break;
//					}
//					
//					System.out.println("\n");
//					System.out.println("==========================");
//					System.out.println("Danh sách sân bay: ");
//					System.out.println("==========================");
//
//                    for (int i = 0; i < Airport_list.size(); ++i) {
//                        Airport airport = Airport_list.get(i);
//
//                        if (airport instanceof InternationalAirport) {
//                            System.out.println("\nLoại sân bay: International Airport");
//                            System.out.printf("%-30s %-15s %-30s %-30s %-30s %-30s\n", "Tên", "Mã", "Địa chỉ", "Số đường bay", "Số lượng máy bay", "Sức chứa hành khách");
//                            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
//                            ((InternationalAirport) airport).output();
//                        } else if (airport instanceof MilitaryAirport) {
//                            System.out.println("\nLoại sân bay: Military Airport");
//                            System.out.printf("%-30s %-15s %-30s %-30s %-30s\n", "Tên", "Mã", "Địa chỉ", "Số lượng kho chứa vũ khí", "Số lượng trạm radar chiến lược");
//                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
//                            ((MilitaryAirport) airport).output();
//                        }
//                    }
//					
//					System.out.println("\n\n\n");
//					
//					break;
//				
//				case 4:
//					boolean found = false;
//					
//					String name_finding;
//					
//					System.out.print("Nhập tên sân bay muốn tìm: ");
//					name_finding = sc.next();
//					
//					for (int i = 0; i < Airport_list.size(); ++i) {
//						if (Airport_list.get(i).getAirportName().equals(name_finding)) {
//	                        if (Airport_list.get(i) instanceof InternationalAirport) {
//	                            System.out.println("\nLoại sân bay: International Airport");
//	                            System.out.printf("%-30s %-15s %-30s %-30s %-30s %-30s\n", "Tên", "Mã", "Địa chỉ", "Số đường bay", "Số lượng máy bay", "Sức chứa hành khách");
//	                            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
//	                            ((InternationalAirport) Airport_list.get(i)).output();
//	                            System.out.println("\n");
//	                        } else if (Airport_list.get(i) instanceof MilitaryAirport) {
//	                            System.out.println("\nLoại sân bay: Military Airport");
//	                            System.out.printf("%-30s %-15s %-30s %-30s %-30s\n", "Tên", "Mã", "Địa chỉ", "Số lượng kho chứa vũ khí", "Số lượng trạm radar chiến lược");
//	                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
//	                            ((MilitaryAirport) Airport_list.get(i)).output();
//	                            System.out.println("\n");
//	                        }
//	                        
//	                        found = true;
//	                        break;
//						}
//					}
//					
//					if (!found) {
//						System.out.println("\nKhông tìm thấy sân bay bạn đã nhập.\n\n\n");
//					}
//					
//					break;
//					
//				case 5:
//				    String outputFileName = "C:\\Tung_TLU\\TLU_Hoc\\2024_2025(Nam 3)\\Lap trinh Java\\TH-20-9-2024\\file.txt";
//
//				    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
//				        for (Airport airport : Airport_list) {
//				            if (airport instanceof InternationalAirport) {
//				                writer.write("International\n");
//				                InternationalAirport ia = (InternationalAirport) airport;
//				                writer.write(ia.getAirportName() + "\n");
//				                writer.write(ia.getAirportCode() + "\n");
//				                writer.write(ia.getAirportAddress() + "\n");
//				                writer.write(ia.getNumberOfRunways() + "\n");
//				                writer.write(ia.getNumberOfAirplanes() + "\n");
//				                writer.write(ia.getPassengerCapacity() + "\n");
//				            } else if (airport instanceof MilitaryAirport) {
//				                writer.write("Military\n");
//				                MilitaryAirport ma = (MilitaryAirport) airport;
//				                writer.write(ma.getAirportName() + "\n");
//				                writer.write(ma.getAirportCode() + "\n");
//				                writer.write(ma.getAirportAddress() + "\n");
//				                writer.write(ma.getNumberOfWeaponStorage() + "\n");
//				                writer.write(ma.getNumberOfRadarStations() + "\n");
//				            }
//				        }
//				        System.out.println("Ghi dữ liệu vào file thành công. \n\n\n");
//				    } catch (IOException e) {
//				        e.printStackTrace();
//				    }
//				    break;
//
//				case 6:
//				    String inputFileName = "C:\\Tung_TLU\\TLU_Hoc\\2024_2025(Nam 3)\\Lap trinh Java\\TH-20-9-2024\\file.txt";
//
//				    try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
//				        String line;
//				        while ((line = reader.readLine()) != null) {
//				            if (line.equals("International")) {
//				                InternationalAirport ia = new InternationalAirport();
//				                ia.setAirportName(reader.readLine());
//				                ia.setAirportCode(reader.readLine());
//				                ia.setAirportAddress(reader.readLine());
//				                ia.setNumberOfRunways(Integer.parseInt(reader.readLine()));
//				                ia.setNumberOfAirplanes(Integer.parseInt(reader.readLine()));
//				                ia.setPassengerCapacity(Integer.parseInt(reader.readLine()));
//				                Airport_list.add(ia);
//				            } else if (line.equals("Military")) {
//				                MilitaryAirport ma = new MilitaryAirport();
//				                ma.setAirportName(reader.readLine());
//				                ma.setAirportCode(reader.readLine());
//				                ma.setAirportAddress(reader.readLine());
//				                ma.setNumberOfWeaponStorage(Integer.parseInt(reader.readLine()));
//				                ma.setNumberOfRadarStations(Integer.parseInt(reader.readLine()));
//				                Airport_list.add(ma);
//				            }
//				        }
//				        System.out.println("Đọc dữ liệu từ file thành công. \n\n\n");
//				    } catch (IOException e) {
//				        e.printStackTrace();
//				    }
//				    break;
//				    
//                case 7: 
//                    String serializedFileName = "C:\\Tung_TLU\\TLU_Hoc\\2024_2025(Nam 3)\\Lap trinh Java\\TH-20-9-2024\\airports.dat";
//                    
//                    try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(serializedFileName))) {
//                        os.writeObject(Airport_list);
//                        System.out.println("Dữ liệu đã được lưu thành công.\n\n\n");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//
//                case 8:
//                    serializedFileName = "C:\\Tung_TLU\\TLU_Hoc\\2024_2025(Nam 3)\\Lap trinh Java\\TH-20-9-2024\\airports.dat";
//                    
//                    try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(serializedFileName))) {
//                        Airport_list = (ArrayList<Airport>)os.readObject();
//                        System.out.println("Dữ liệu đã được tải thành công.\n\n\n");
//                    } catch (IOException | ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//					
//				case 0:
//					System.out.println("\n* Thông báo: Dừng chương trình thành công.");
//					sc.close();
//					return;
//					
//				default:
//					System.out.println("Đầu vào không hợp lệ. Vui lòng lựa chọn lại.\n\n\n");
//					continue;
//			}
//		}
	}
}
