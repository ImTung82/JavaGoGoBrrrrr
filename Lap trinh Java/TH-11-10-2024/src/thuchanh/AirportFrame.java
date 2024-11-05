package thuchanh;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AirportFrame extends JFrame {
	private JLabel nameLabel, codeLabel, addressLabel, typeLabel, runwayLabel, airplaneLabel, capacityLabel, weaponStorageLabel, radarLabel;
	private JTextField nameField, codeField, addressField, runwayField, airplaneField, capacityField, weaponStorageField, radarField, searchField;
	private JComboBox<String> typeComboBox;
	private JPanel inputPanel, buttonMenuPanel;
	private JButton addButton, deleteButton, searchButton, editButton; 
	private JTable airplaneTable;
	private DefaultTableModel tableModel;
	private JScrollPane tableScrollPane;
	private JMenu fileMenu, loadMenu, saveMenu;
	private JMenuItem loadFromTextFileMenuItem, loadFromBinaryFileMenuItem, saveToTextFileMenuItem, saveToBinaryFileMenuItem;
	private JMenuBar menuBar;
	private String URL = "jdbc:sqlserver://TOG:1433;databaseName=TH_Java;encrypt=false";
	private String username = "sa";
	private String password = "123456";
    private Connection connection;
	
    private ArrayList<Airport> airportList = new ArrayList<>();
	
	String internationalColumnNames[] = {"Tên", "Mã sân bay", "Địa chỉ", "Số đường bay", "Số máy bay", "Sức chứa"};
	String militaryColumnNames[] = {"Tên", "Mã sân bay", "Địa chỉ", "Số kho chứa vũ khí", "Số trạm radar chiến lược"};
	
    // Tạo đối tượng AirportActionListener
    AirportActionListener actionListener = new AirportActionListener(this);
	
    public Connection connectToDatabase() {
        Connection connection = null;
        try {
            // Kết nối tới cơ sở dữ liệu SQL Server
            connection = DriverManager.getConnection(URL, username, password);
            System.out.println("Kết nối tới cơ sở dữ liệu thành công.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Kết nối tới cơ sở dữ liệu thất bại.");
        }
        return connection;
    }
    
	public void appGUI() {
        setTitle("Airport Management");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Xây dựng MenuBar
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        loadMenu = new JMenu("Load File");
        saveMenu = new JMenu("Save File");
        
        loadFromTextFileMenuItem = new JMenuItem("Text");
        loadFromTextFileMenuItem.addActionListener(actionListener);
        
        loadFromBinaryFileMenuItem = new JMenuItem("Binary");
        loadFromBinaryFileMenuItem.addActionListener(actionListener);
        
        saveToTextFileMenuItem = new JMenuItem("Text");
        saveToTextFileMenuItem.addActionListener(actionListener);
        
        saveToBinaryFileMenuItem = new JMenuItem("Binary");
        saveToBinaryFileMenuItem.addActionListener(actionListener);
        
        loadMenu.add(loadFromTextFileMenuItem);
        loadMenu.add(loadFromBinaryFileMenuItem);
        saveMenu.add(saveToTextFileMenuItem);
        saveMenu.add(saveToBinaryFileMenuItem);
        
        fileMenu.add(loadMenu);
        fileMenu.add(saveMenu);
        
        // Thêm menu vào menuBar
        menuBar.add(fileMenu);

        // Thêm menuBar vào JFrame
        setJMenuBar(menuBar);

        // Xây dựng Panel Nhập thông tin
        inputPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        typeLabel = new JLabel("Loại sân bay:");
        inputPanel.add(typeLabel);
        typeComboBox = new JComboBox<>(new String[] {"International Airport", "Military Airport"});
        inputPanel.add(typeComboBox);

        nameLabel = new JLabel("Tên:");
        inputPanel.add(nameLabel);
        nameField = new JTextField();
        inputPanel.add(nameField);

        codeLabel = new JLabel("Mã sân bay:");
        inputPanel.add(codeLabel);
        codeField = new JTextField();
        inputPanel.add(codeField);

        addressLabel = new JLabel("Địa chỉ:");
        inputPanel.add(addressLabel);
        addressField = new JTextField();
        inputPanel.add(addressField);

        // Gọi phương thức để cập nhật các trường nhập liệu
        updateFields();
        
        // Gán ActionListener cho JComboBox và JButton
        typeComboBox.addActionListener(actionListener);

        add(inputPanel, BorderLayout.NORTH);
        
        // Xây dựng Panel các nút chức năng
        buttonMenuPanel = new JPanel(new FlowLayout());
        buttonMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Trên, trái, dưới, phải
        
        addButton = new JButton("Thêm sân bay");
        buttonMenuPanel.add(addButton);
        
        addButton.addActionListener(actionListener);
        
        editButton = new JButton("Sửa sân bay");
        buttonMenuPanel.add(editButton);
        
        editButton.addActionListener(actionListener);
        
        deleteButton = new JButton("Xóa sân bay");
        buttonMenuPanel.add(deleteButton);
        
        deleteButton.addActionListener(actionListener);
        
        buttonMenuPanel.add(new JLabel(" || "));
        
        searchField = new JTextField(20);
        buttonMenuPanel.add(searchField);

        searchButton = new JButton("Tìm kiếm sân bay");
        buttonMenuPanel.add(searchButton);
        
        searchButton.addActionListener(actionListener);
        
        add(buttonMenuPanel, BorderLayout.SOUTH);
        
        // Tải dữ liệu từ cơ sở dữ liệu
        loadAllAirports();
    }

    public void updateFields() {
        // Xóa các trường hiện có trong inputPanel
        if (runwayLabel != null) inputPanel.remove(runwayLabel);
        if (runwayField != null) inputPanel.remove(runwayField);
        if (airplaneLabel != null) inputPanel.remove(airplaneLabel);
        if (airplaneField != null) inputPanel.remove(airplaneField);
        if (capacityLabel != null) inputPanel.remove(capacityLabel);
        if (capacityField != null) inputPanel.remove(capacityField);
        if (weaponStorageLabel != null) inputPanel.remove(weaponStorageLabel);
        if (weaponStorageField != null) inputPanel.remove(weaponStorageField);
        if (radarLabel != null) inputPanel.remove(radarLabel);
        if (radarField != null) inputPanel.remove(radarField);

        // Xóa bảng hiện tại (nếu có)
        if (tableScrollPane != null) {
            remove(tableScrollPane);
        }
        
        // Lấy loại sân bay được chọn
        String selectedType = (String)typeComboBox.getSelectedItem();

        if ("International Airport".equals(selectedType)) {
            runwayLabel = new JLabel("Số đường bay:");
            inputPanel.add(runwayLabel);
            runwayField = new JTextField();
            inputPanel.add(runwayField);

            airplaneLabel = new JLabel("Số máy bay:");
            inputPanel.add(airplaneLabel);
            airplaneField = new JTextField();
            inputPanel.add(airplaneField);

            capacityLabel = new JLabel("Sức chứa tối đa:");
            inputPanel.add(capacityLabel);
            capacityField = new JTextField();
            inputPanel.add(capacityField);
            
            // Xây dựng bảng hiển thị dữ liệu
            tableModel = new DefaultTableModel(internationalColumnNames, 0);
            airplaneTable = new JTable(tableModel);
            tableScrollPane = new JScrollPane(airplaneTable);
            add(tableScrollPane, BorderLayout.CENTER);
            
            for (Airport airport : airportList) {
                if (airport instanceof InternationalAirport) {
                    InternationalAirport temp = (InternationalAirport) airport;
                    tableModel.addRow(new Object[] {
                        temp.getAirportName(),
                        temp.getAirportCode(),
                        temp.getAirportAddress(),
                        temp.getNumberOfRunways(),
                        temp.getNumberOfAirplanes(),
                        temp.getPassengerCapacity()
                    });
                }
            }
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);  
        } else if ("Military Airport".equals(selectedType)) {
        	weaponStorageLabel = new JLabel("Số kho vũ khí:");
            inputPanel.add(weaponStorageLabel);
            weaponStorageField = new JTextField();
            inputPanel.add(weaponStorageField);
            
            radarLabel = new JLabel("Số trạm radar chiến lược:");
            inputPanel.add(radarLabel);
            radarField = new JTextField();
            inputPanel.add(radarField);
            
            tableModel = new DefaultTableModel(militaryColumnNames, 0);
            airplaneTable = new JTable(tableModel);
            tableScrollPane = new JScrollPane(airplaneTable);
            add(tableScrollPane, BorderLayout.CENTER);
            
            for (Airport airport : airportList) {
                if (airport instanceof MilitaryAirport) {
                	MilitaryAirport temp = (MilitaryAirport) airport;
                    tableModel.addRow(new Object[] {
                        temp.getAirportName(),
                        temp.getAirportCode(),
                        temp.getAirportAddress(),
                        temp.getNumberOfWeaponStorage(),
                        temp.getNumberOfRadarStations()
                    });
                }
            }
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);  
        }

        // Cập nhật lại giao diện người dùng
        inputPanel.revalidate();
        inputPanel.repaint();
    }
    
    public void addAirport() {
        String name = nameField.getText().trim();
        String code = codeField.getText().trim();
        String address = addressField.getText().trim();
        String selectedType = (String)typeComboBox.getSelectedItem();

        if ("International Airport".equals(selectedType)) {
            String runway = runwayField.getText().trim();
            String airplanes = airplaneField.getText().trim();
            String capacity = capacityField.getText().trim();
            
            if (name.isEmpty() || code.isEmpty() || address.isEmpty() || runway.isEmpty() || airplanes.isEmpty() || capacity.isEmpty()) {
            	JOptionPane.showMessageDialog(this, "Thông tin không được để trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else {
            	// Kiểm tra xem mã sân bay đã tồn tại trước đó chưa
            	for (Airport airport : airportList) {
            		if (airport instanceof InternationalAirport && airport.getAirportCode().equals(code)) {
            			JOptionPane.showMessageDialog(this, "Mã sân bay đã tồn tại. Vui lòng chọn một mã sân bay khác.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            			return;
            		}
            	}
            	// Thêm dữ liệu vào mảng lưu trữ
            	InternationalAirport temp = new InternationalAirport();
            	temp.setAirportName(name);
            	temp.setAirportCode(code);
            	temp.setAirportAddress(address);
            	temp.setNumberOfRunways(Integer.parseInt(runway));
            	temp.setNumberOfAirplanes(Integer.parseInt(airplanes));
            	temp.setPassengerCapacity(Integer.parseInt(capacity));
            	
            	airportList.add(temp);
            	
//                // Thêm hàng vào bảng
//                tableModel.addRow(new Object[]{name, code, address, runway, airplanes, capacity});	
                
                String query = "INSERT INTO International_Airport_List VALUES (?, ?, ?, ?, ?, ?)";
                
                try (Connection connection = connectToDatabase();
                	PreparedStatement pstmt = connection.prepareStatement(query)) {
                      
                    pstmt.setString(1, temp.getAirportName());
                    pstmt.setString(2, temp.getAirportCode());
                    pstmt.setString(3, temp.getAirportAddress());
                    pstmt.setInt(4, temp.getNumberOfRunways());
                    pstmt.setInt(5, temp.getNumberOfAirplanes());
                    pstmt.setInt(6, temp.getPassengerCapacity());
                       
                    pstmt.executeUpdate();
                    loadAllAirports();
                    JOptionPane.showMessageDialog(this, "Thêm sân bay thành công.", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Thêm sân bay thất bại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                            System.out.println("Kết nối đã được đóng.");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else if ("Military Airport".equals(selectedType)) {
            String weaponStorage = weaponStorageField.getText().trim();
            String radar = radarField.getText().trim();
            if (name.isEmpty() || code.isEmpty() || address.isEmpty() || weaponStorage.isEmpty() || radar.isEmpty()) {
            	JOptionPane.showMessageDialog(this, "Thông tin không được để trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else {
            	// Kiểm tra xem mã sân bay đã tồn tại trước đó chưa
            	for (Airport airport : airportList) {
            		if (airport instanceof MilitaryAirport && airport.getAirportCode().equals(code)) {
            			JOptionPane.showMessageDialog(this, "Mã sân bay đã tồn tại. Vui lòng chọn một mã sân bay khác.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            			return;
            		}
            	}
            	
            	// Thêm dữ liệu vào mảng lưu trữ
            	MilitaryAirport temp = new MilitaryAirport();
            	temp.setAirportName(name);
            	temp.setAirportCode(code);
            	temp.setAirportAddress(address);
            	temp.setNumberOfWeaponStorage(Integer.parseInt(weaponStorage));
            	temp.setNumberOfRadarStations(Integer.parseInt(radar));
            	
            	airportList.add(temp);
            	
//                // Thêm hàng vào bảng
//                tableModel.addRow(new Object[]{name, code, address, weaponStorage, radar});
                
                String query = "INSERT INTO Military_Airport_List VALUES (?, ?, ?, ?, ?)";
                
                try (Connection connection = connectToDatabase();
                	PreparedStatement pstmt = connection.prepareStatement(query)) {
                      
                    pstmt.setString(1, temp.getAirportName());
                    pstmt.setString(2, temp.getAirportCode());
                    pstmt.setString(3, temp.getAirportAddress());
                    pstmt.setInt(4, temp.getNumberOfWeaponStorage());
                    pstmt.setInt(5, temp.getNumberOfRadarStations());
                       
                    pstmt.executeUpdate();
                    loadAllAirports();
                    JOptionPane.showMessageDialog(this, "Thêm sân bay thành công.", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Thêm sân bay thất bại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                            System.out.println("Kết nối đã được đóng.");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        // Xóa các trường nhập liệu sau khi thêm
        clearFields();
    }

    public void clearFields() {
        nameField.setText("");
        codeField.setText("");
        addressField.setText("");
        if (runwayField != null) runwayField.setText("");
        if (airplaneField != null) airplaneField.setText("");
        if (capacityField != null) capacityField.setText("");
        if (weaponStorageField != null) weaponStorageField.setText("");
        if (radarField != null) radarField.setText("");
    }
    
    public void deleteAirport() {
        int selectedRow = airplaneTable.getSelectedRow(); // Lấy hàng đang được chọn
        String selectedType = (String)typeComboBox.getSelectedItem();
        
        if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
        	int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        	
        	if (confirm == JOptionPane.YES_OPTION) {
        		if (selectedType.equals("International Airport")) {
        			// Lấy airportCode từ dòng đã chọn để xóa khỏi cơ sở dữ liệu
            		String selectedAirportCode = (String)tableModel.getValueAt(selectedRow, 1);
            		
            		String query = "DELETE FROM International_Airport_List WHERE airportCode = ?";
                    try (Connection connection = connectToDatabase();
                        PreparedStatement pstmt = connection.prepareStatement(query)) {
                        pstmt.setString(1, selectedAirportCode);
                        pstmt.executeUpdate();
                            
                        tableModel.removeRow(selectedRow); // Xóa hàng
                        airportList.remove(selectedRow); // Xóa dữ liệu trong list
                        
                        loadAllAirports();
                        JOptionPane.showMessageDialog(this, "Xóa sân bay thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Xóa sân bay thất bại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }
        		} else if (selectedType.equals("Military Airport")) {
        			// Lấy mã code từ dòng đã chọn để xóa khỏi cơ sở dữ liệu
            		String selectedAirportCode = (String)tableModel.getValueAt(selectedRow, 1);
            		
            		String query = "DELETE FROM Military_Airport_List WHERE airportCode = ?";
                    try (Connection connection = connectToDatabase();
                        	PreparedStatement pstmt = connection.prepareStatement(query)) {
                            pstmt.setString(1, selectedAirportCode);
                            pstmt.executeUpdate();
                            
                            tableModel.removeRow(selectedRow); // Xóa hàng
                            airportList.remove(selectedRow); // Xóa dữ liệu trong list
                            
                            loadAllAirports();
                            JOptionPane.showMessageDialog(this, "Xóa sân bay thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(this, "Xóa sân bay thất bại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        }		
        			}
        		}
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void searchAirport() {
        String keyword = searchField.getText().trim().toLowerCase();

        // Tạo một JFrame mới để hiển thị kết quả tìm kiếm
        JFrame resultFrame = new JFrame("Search Results");
        resultFrame.setSize(800, 600);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setLayout(new GridLayout(2, 1));

        // Tạo hai mô hình bảng mới
        DefaultTableModel internationalTableModel = new DefaultTableModel(internationalColumnNames, 0);
        DefaultTableModel militaryTableModel = new DefaultTableModel(militaryColumnNames, 0);

        // Tìm kiếm trong danh sách sân bay
        for (Airport airport : airportList) {
            if (airport.getAirportName().toLowerCase().contains(keyword) || airport.getAirportCode().toLowerCase().contains(keyword) || airport.getAirportAddress().toLowerCase().contains(keyword)) {
                if (airport instanceof InternationalAirport) {
                    InternationalAirport temp = (InternationalAirport) airport;
                    internationalTableModel.addRow(new Object[]{
                        temp.getAirportName(),
                        temp.getAirportCode(),
                        temp.getAirportAddress(),
                        temp.getNumberOfRunways(),
                        temp.getNumberOfAirplanes(),
                        temp.getPassengerCapacity()
                    });
                } else if (airport instanceof MilitaryAirport) {
                    MilitaryAirport temp = (MilitaryAirport) airport;
                    militaryTableModel.addRow(new Object[]{
                        temp.getAirportName(),
                        temp.getAirportCode(),
                        temp.getAirportAddress(),
                        temp.getNumberOfWeaponStorage(),
                        temp.getNumberOfRadarStations()
                    });
                }
            }
        }

        // Tạo và hiển thị bảng cho sân bay quốc tế
        JTable internationalTable = new JTable(internationalTableModel);
        JScrollPane internationalScrollPane = new JScrollPane(internationalTable);
        internationalScrollPane.setBorder(BorderFactory.createTitledBorder("International Airports"));

        // Tạo và hiển thị bảng cho sân bay quân sự
        JTable militaryTable = new JTable(militaryTableModel);
        JScrollPane militaryScrollPane = new JScrollPane(militaryTable);
        militaryScrollPane.setBorder(BorderFactory.createTitledBorder("Military Airports"));

        // Thêm hai bảng vào JFrame mới
        resultFrame.add(internationalScrollPane);
        resultFrame.add(militaryScrollPane);

        // Kiểm tra nếu không tìm thấy sân bay
        if (internationalTableModel.getRowCount() == 0 && militaryTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sân bay dựa vào thông tin đã cung cấp", "Thông báo", JOptionPane.WARNING_MESSAGE);
            resultFrame.dispose(); // Đóng frame nếu không có kết quả
        } else {
            resultFrame.setVisible(true); // Hiển thị JFrame nếu có kết quả
        }
    }
    
    public void editAirport() {
        // Tạo một JFrame mới phục vụ việc sửa
        JFrame editFrame = new JFrame("Edit");
        editFrame.setSize(600, 350);
        editFrame.setLocationRelativeTo(null);
        editFrame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Khai báo JTextField ngoài để tránh lỗi bị rỗng
        JTextField nameField = new JTextField();
        JTextField codeField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField runwayField = new JTextField();
        JTextField airplaneField = new JTextField();
        JTextField capacityField = new JTextField();
        JTextField weaponStorageField = new JTextField();
        JTextField radarField = new JTextField();

        int selectedRow = airplaneTable.getSelectedRow(); // Lấy hàng đang được chọn
        String selectedType = (String) typeComboBox.getSelectedItem();

        if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
            JLabel nameLabel = new JLabel("Tên:");
            inputPanel.add(nameLabel);
            inputPanel.add(nameField);

            JLabel codeLabel = new JLabel("Mã sân bay:");
            inputPanel.add(codeLabel);
            codeField.setEditable(false);
            inputPanel.add(codeField);

            JLabel addressLabel = new JLabel("Địa chỉ:");
            inputPanel.add(addressLabel);
            inputPanel.add(addressField);

            if (selectedType.equals("International Airport")) {
                JLabel runwayLabel = new JLabel("Số đường bay:");
                inputPanel.add(runwayLabel);
                inputPanel.add(runwayField);

                JLabel airplaneLabel = new JLabel("Số máy bay:");
                inputPanel.add(airplaneLabel);
                inputPanel.add(airplaneField);

                JLabel capacityLabel = new JLabel("Sức chứa tối đa:");
                inputPanel.add(capacityLabel);
                inputPanel.add(capacityField);

                // Điền các giá trị hiện tại vào các trường
                String selectedAirportName = (String) tableModel.getValueAt(selectedRow, 0);
                String selectedAirportCode = (String) tableModel.getValueAt(selectedRow, 1);
                String selectedAirportAddress = (String) tableModel.getValueAt(selectedRow, 2);
                String selectedAirportRunway = tableModel.getValueAt(selectedRow, 3).toString();
                String selectedAirportPlane = tableModel.getValueAt(selectedRow, 4).toString();
                String selectedAirportCapacity = tableModel.getValueAt(selectedRow, 5).toString();

                nameField.setText(selectedAirportName);
                codeField.setText(selectedAirportCode);
                addressField.setText(selectedAirportAddress);
                runwayField.setText(selectedAirportRunway);
                airplaneField.setText(selectedAirportPlane);
                capacityField.setText(selectedAirportCapacity);
            } else if (selectedType.equals("Military Airport")) {
                JLabel weaponStorageLabel = new JLabel("Số kho vũ khí:");
                inputPanel.add(weaponStorageLabel);
                inputPanel.add(weaponStorageField);

                JLabel radarLabel = new JLabel("Số trạm radar chiến lược:");
                inputPanel.add(radarLabel);
                inputPanel.add(radarField);

                // Điền các giá trị hiện tại vào các trường
                String selectedAirportName = (String) tableModel.getValueAt(selectedRow, 0);
                String selectedAirportCode = (String) tableModel.getValueAt(selectedRow, 1);
                String selectedAirportAddress = (String) tableModel.getValueAt(selectedRow, 2);
                String selectedAirportWeaponStorage = tableModel.getValueAt(selectedRow, 3).toString();
                String selectedAirportRadarStation = tableModel.getValueAt(selectedRow, 4).toString();

                nameField.setText(selectedAirportName);
                codeField.setText(selectedAirportCode);
                addressField.setText(selectedAirportAddress);
                weaponStorageField.setText(selectedAirportWeaponStorage);
                radarField.setText(selectedAirportRadarStation);
            }

            inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            editFrame.add(inputPanel, BorderLayout.NORTH);

            JButton editButton = new JButton("Sửa");
            buttonPanel.add(editButton);

            JButton cancelButton = new JButton("Hủy");
            buttonPanel.add(cancelButton);

            // Nút Cancel đóng cửa sổ chỉnh sửa
            cancelButton.addActionListener(e -> {
                editFrame.dispose();
            });

            editFrame.add(buttonPanel, BorderLayout.SOUTH);
            editFrame.setVisible(true);

            // Thêm sự kiện cho nút Edit
            editButton.addActionListener(e -> {
                try {
                    String newAirportName = nameField.getText();
                    String newAirportCode = codeField.getText();
                    String newAirportAddress = addressField.getText();
                    
                    // Kiểm tra các trường không được để trống
                    if (newAirportName.isEmpty() || newAirportCode.isEmpty() || newAirportAddress.isEmpty()) {
                        JOptionPane.showMessageDialog(editFrame, "Không được để trống thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return; // Dừng lại nếu có trường bị trống
                    }

                    if (selectedType.equals("International Airport")) {
                    	// Kiểm tra các trường không được để trống trước khi chuyển đổi
                    	if (runwayField.getText().isEmpty() || airplaneField.getText().isEmpty() || capacityField.getText().isEmpty()) {
                    	    JOptionPane.showMessageDialog(editFrame, "Không được để trống thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    	    return; // Dừng lại nếu có trường bị trống
                    	}
                    	
                        int newAirportRunway = Integer.parseInt(runwayField.getText());
                        int newAirportPlane = Integer.parseInt(airplaneField.getText());
                        int newAirportCapacity = Integer.parseInt(capacityField.getText());

                        // Cập nhật cơ sở dữ liệu cho International Airport
                        String query = "UPDATE International_Airport_List SET airportName = ?, airportAddress = ?, numberOfRunways = ?, numberOfAirplanes = ?, passengerCapacity = ? WHERE airportCode = ?";
                        try (Connection connection = connectToDatabase();
                            PreparedStatement pstmt = connection.prepareStatement(query)) {
                            pstmt.setString(1, newAirportName);
                            pstmt.setString(2, newAirportAddress);
                            pstmt.setInt(3, newAirportRunway);
                            pstmt.setInt(4, newAirportPlane);
                            pstmt.setInt(5, newAirportCapacity);
                            pstmt.setString(6, newAirportCode);
                            pstmt.executeUpdate();

                            loadAllAirports();
                            editFrame.dispose();
                            JOptionPane.showMessageDialog(editFrame, "Sửa sân bay thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                        } catch (SQLException exx) {
                            exx.printStackTrace();
                            JOptionPane.showMessageDialog(editFrame, "Sửa sân bay thất bại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        }
                    } else if (selectedType.equals("Military Airport")) {
                    	// Kiểm tra các trường không được để trống trước khi chuyển đổi
                    	if (weaponStorageField.getText().isEmpty() || radarField.getText().isEmpty()) {
                    	    JOptionPane.showMessageDialog(editFrame, "Không được để trống thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    	    return; // Dừng lại nếu có trường bị trống
                    	}
                    	
                        int newAirportWeaponStorage = Integer.parseInt(weaponStorageField.getText());
                        int newAirportRadarStation = Integer.parseInt(radarField.getText());

                        // Cập nhật cơ sở dữ liệu cho Military Airport
                        String query = "UPDATE Military_Airport_List SET airportName = ?, airportAddress = ?, numberOfWeaponStorage = ?, numberOfRadarStations = ? WHERE airportCode = ?";
                        try (Connection connection = connectToDatabase();
                            PreparedStatement pstmt = connection.prepareStatement(query)) {
                            pstmt.setString(1, newAirportName);
                            pstmt.setString(2, newAirportAddress);
                            pstmt.setInt(3, newAirportWeaponStorage);
                            pstmt.setInt(4, newAirportRadarStation);
                            pstmt.setString(5, newAirportCode);
                            pstmt.executeUpdate();

                            loadAllAirports();
                            editFrame.dispose();
                            JOptionPane.showMessageDialog(editFrame, "Sửa sân bay thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                        } catch (SQLException exx) {
                            exx.printStackTrace();
                            JOptionPane.showMessageDialog(editFrame, "Sửa sân bay thất bại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(editFrame, "Có lỗi xảy ra khi sửa dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void loadFromTextFile() {
        String inputFileName = "C:\\Tung_TLU\\TLU_Hoc\\2024_2025(Nam 3)\\Lap trinh Java\\TH-20-9-2024\\file.txt";

        // Xóa dữ liệu cũ
        airportList.clear();
        tableModel.setRowCount(0);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] data = line.split(",");
                if (data[0].equals("International")) {
                    InternationalAirport ia = new InternationalAirport();
                    ia.setAirportName(data[1]);
                    ia.setAirportCode(data[2]);
                    ia.setAirportAddress(data[3]);
                    ia.setNumberOfRunways(Integer.parseInt(data[4]));
                    ia.setNumberOfAirplanes(Integer.parseInt(data[5]));
                    ia.setPassengerCapacity(Integer.parseInt(data[6]));
                    airportList.add(ia);
                    tableModel.addRow(new Object[] {
                        ia.getAirportName(),
                        ia.getAirportCode(),
                        ia.getAirportAddress(),
                        ia.getNumberOfRunways(),
                        ia.getNumberOfAirplanes(),
                        ia.getPassengerCapacity()
                    });
                } else if (data[0].equals("Military")) {
                    MilitaryAirport ma = new MilitaryAirport();
                    ma.setAirportName(data[1]);
                    ma.setAirportCode(data[2]);
                    ma.setAirportAddress(data[3]);
                    ma.setNumberOfWeaponStorage(Integer.parseInt(data[4]));
                    ma.setNumberOfRadarStations(Integer.parseInt(data[5]));
                    airportList.add(ma);
                    tableModel.addRow(new Object[] {
                        ma.getAirportName(),
                        ma.getAirportCode(),
                        ma.getAirportAddress(),
                        ma.getNumberOfWeaponStorage(),
                        ma.getNumberOfRadarStations()
                    });
                }
            }
            updateFields();
            JOptionPane.showMessageDialog(this, "Đọc dữ liệu từ file thành công.", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void saveToTextFile() {
	    String outputFileName = "C:\\Tung_TLU\\TLU_Hoc\\2024_2025(Nam 3)\\Lap trinh Java\\TH-20-9-2024\\file.txt";

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
	        for (Airport airport : airportList) {
	            if (airport instanceof InternationalAirport) {
	                writer.write("International,");
	                InternationalAirport ia = (InternationalAirport) airport;
	                writer.write(ia.getAirportName() + ",");
	                writer.write(ia.getAirportCode() + ",");
	                writer.write(ia.getAirportAddress() + ",");
	                writer.write(ia.getNumberOfRunways() + ",");
	                writer.write(ia.getNumberOfAirplanes() + ",");
	                writer.write(ia.getPassengerCapacity() + "\n");
	            } else if (airport instanceof MilitaryAirport) {
	                writer.write("Military,");
	                MilitaryAirport ma = (MilitaryAirport) airport;
	                writer.write(ma.getAirportName() + ",");
	                writer.write(ma.getAirportCode() + ",");
	                writer.write(ma.getAirportAddress() + ",");
	                writer.write(ma.getNumberOfWeaponStorage() + ",");
	                writer.write(ma.getNumberOfRadarStations() + "\n");
	            }
	        }
            JOptionPane.showMessageDialog(this, "Ghi dữ liệu vào file thành công.", "Thông báo", JOptionPane.PLAIN_MESSAGE);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
    
    public void loadFromBinaryFile() {
        String serializedFileName = "C:\\Tung_TLU\\TLU_Hoc\\2024_2025(Nam 3)\\Lap trinh Java\\TH-20-9-2024\\airports.dat";

        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(serializedFileName))) {
            airportList = (ArrayList<Airport>) is.readObject();
            updateFields();
            JOptionPane.showMessageDialog(this, "Đọc dữ liệu từ file thành công.", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void saveToBinaryFile() {
        String serializedFileName = "C:\\Tung_TLU\\TLU_Hoc\\2024_2025(Nam 3)\\Lap trinh Java\\TH-20-9-2024\\airports.dat";
        
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(serializedFileName))) {
            os.writeObject(airportList);
            JOptionPane.showMessageDialog(this, "Ghi dữ liệu vào file thành công.", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi ghi file.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void loadInternationalAirports() {
        Connection connection = connectToDatabase();
        String query = "SELECT * FROM International_Airport_List";

        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("airportName");
                String code = rs.getString("airportCode");
                String address = rs.getString("airportAddress");
                int runwayCount = rs.getInt("numberOfRunways");
                int airplaneCount = rs.getInt("numberOfAirplanes");
                int capacity = rs.getInt("passengerCapacity");

                InternationalAirport airport = new InternationalAirport(runwayCount, airplaneCount, capacity);
                airport.setAirportName(name);
                airport.setAirportCode(code);
                airport.setAirportAddress(address);
                airportList.add(airport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Kết nối đã được đóng.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadMilitaryAirports() {
        Connection connection = connectToDatabase();
        String query = "SELECT * FROM Military_Airport_List";

        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("airportName");
                String code = rs.getString("airportCode");
                String address = rs.getString("airportAddress");
                int weaponStorage = rs.getInt("numberOfWeaponStorage");
                int radarStations = rs.getInt("numberOfRadarStations");

                MilitaryAirport airport = new MilitaryAirport(weaponStorage, radarStations);
                airport.setAirportName(name);
                airport.setAirportCode(code);
                airport.setAirportAddress(address);
                airportList.add(airport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Kết nối đã được đóng.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadAllAirports() {
    	airportList.clear();
        loadInternationalAirports();
        loadMilitaryAirports();
        updateFields();
    }
    

    // Getter cho các thành phần cần thiết
    public JComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    public JButton getAddButton() {
        return addButton;
    }
    
    public JButton getDeleteButton() {
    	return deleteButton;
    }
    
    public JButton getSearchButton() {
    	return searchButton;
    }
    
    public JMenuItem getLoadFromTextFileMenuItem() {
    	return loadFromTextFileMenuItem;
    }
    
    public JMenuItem getSaveToTextFileMenuItem() {
    	return saveToTextFileMenuItem;
    }
    
    public JMenuItem getLoadFromBinaryFileMenuItem() {
    	return loadFromBinaryFileMenuItem;
    }
    
    public JMenuItem getSaveToBinaryFileMenuItem() {
    	return saveToBinaryFileMenuItem;
    }
    
    public JButton getEditButton() {
    	return editButton;
    }
}