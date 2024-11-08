package GLuong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class XLLuong implements ActionListener {
	private String URL = "jdbc:sqlserver://TOG:1433;databaseName=DLLuong;encrypt=false";
	private String username = "sa";
	private String password = "123456";
	private Connection connection;
	
    private GUI_updateNV gui;

    // Constructor nhận tham chiếu đến GUI_updateNV
    public XLLuong(GUI_updateNV gui) {
        this.gui = gui;
    }
	
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
	
    public void getNVbyMa(String maNV) {
        Connection connection = getCon();
        String query = "SELECT * FROM tbNhanvien WHERE MaNV = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maNV);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Lấy thông tin nhân viên từ kết quả truy vấn
                String hoten = rs.getString("Hoten");
                String diachi = rs.getString("Diachi");
                int luong = rs.getInt("Luong");

                // Cập nhật thông tin lên giao diện
                gui.MaNVField.setText(maNV);
                gui.HotenField.setText(hoten);
                gui.DiachiCB.setSelectedItem(diachi);
                gui.LuongField.setText(String.valueOf(luong));
                
                gui.searchField.setText("");
            } else {
            	JOptionPane.showMessageDialog(gui, "Không tìm thấy nhân viên với mã NV đã nhập", "Thông báo", JOptionPane.WARNING_MESSAGE);
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
	
	public void updateNV() {
        Connection connection = getCon();
        String query = "UPDATE tbNhanvien SET Hoten = ?, Diachi = ?, Luong = ? WHERE MaNV = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
        	stmt.setString(1, gui.HotenField.getText());
        	stmt.setString(2, gui.DiachiCB.getSelectedItem().toString());
        	stmt.setInt(3, Integer.parseInt(gui.LuongField.getText()));
        	stmt.setString(4, gui.MaNVField.getText());
        	
        	gui.MaNVField.setText("");
        	gui.HotenField.setText("");
        	gui.LuongField.setText("");
        	
        	stmt.executeUpdate();
        	
        	JOptionPane.showMessageDialog(gui, "Cập nhật thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        JButton sourceButton = (JButton) e.getSource();

        if (sourceButton == gui.searchButton) {
            String maNV = gui.searchField.getText();
            getNVbyMa(maNV);
        } else if (sourceButton == gui.updateButton) {
            updateNV();
        }
	}
}
