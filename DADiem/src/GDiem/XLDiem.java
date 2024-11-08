package GDiem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class XLDiem implements IHocvien, ActionListener {
	private String URL = "jdbc:sqlserver://TOG:1433;databaseName=DLDiem;encrypt=false";
	private String username = "sa";
	private String password = "123456";
	
    private GUI_findHV gui;

    // Constructor nhận tham chiếu đến GUI_findHV
    public XLDiem(GUI_findHV gui) {
        this.gui = gui;
    }
	
	@Override
	public Connection getCon() {
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

	@Override
	public void getHVbyLop(String lop) {
		// TODO Auto-generated method stub
		Connection connection = getCon();
		String query = "SELECT * FROM tbHocvien WHERE Lop = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, lop);
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) gui.studentTable.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trước khi thêm dữ liệu mới
            
            while (rs.next()) {
                String maHV = rs.getString("MaHV");
                String hoTen = rs.getString("HoTen");
                String gioiTinh = rs.getString("GT");
                String lopHV = rs.getString("Lop");
                float diem = rs.getFloat("Diem");
                
                // Tạo đối tượng Hocvien
                Hocvien hocVien = new Hocvien(maHV, hoTen, lopHV, gioiTinh, diem);
                String ketQua = hocVien.Ketqua(diem); // Lấy kết quả từ phương thức Ketqua
                
                // Thêm dữ liệu vào bảng
                Object[] row = {
                    maHV,
                    hoTen,
                    gioiTinh,
                    lopHV,
                    diem,
                    ketQua // Thêm kết quả vào cột Kết quả
                };
                model.addRow(row);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getHVbyLopGT(String lop, String gt) {
		// TODO Auto-generated method stub
		Connection connection = getCon();
		String query = "SELECT * FROM tbHocvien WHERE Lop = ? AND GT = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, lop);
			stmt.setString(2, gt);
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) gui.studentTable.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trước khi thêm dữ liệu mới
            
            while (rs.next()) {
                String maHV = rs.getString("MaHV");
                String hoTen = rs.getString("HoTen");
                String gioiTinh = rs.getString("GT");
                String lopHV = rs.getString("Lop");
                float diem = rs.getFloat("Diem");
                
                // Tạo đối tượng Hocvien
                Hocvien hocVien = new Hocvien(maHV, hoTen, lopHV, gioiTinh, diem);
                String ketQua = hocVien.Ketqua(diem); // Lấy kết quả từ phương thức Ketqua
                
                // Thêm dữ liệu vào bảng
                Object[] row = {
                    maHV,
                    hoTen,
                    gioiTinh,
                    lopHV,
                    diem,
                    ketQua // Thêm kết quả vào cột Kết quả
                };
                model.addRow(row);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object sourceButton = (Object)e.getSource();

        if (sourceButton == gui.searchButton) {
            String lop = gui.lopComboBox.getSelectedItem().toString();
            getHVbyLop(lop);
        } else if (gui.maleGTRadioButton.isSelected() || gui.femaleGTRadioButton.isSelected()) {
        	String lop = gui.lopComboBox.getSelectedItem().toString();
        	String gt = "";
        	if (gui.maleGTRadioButton.isSelected()) {
        		gt = "Nam";
        	} else if (gui.femaleGTRadioButton.isSelected()) {
        		gt = "Nữ";
        	}
        	getHVbyLopGT(lop, gt);
        }
	}
}
