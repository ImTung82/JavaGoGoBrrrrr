package GSach;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class XLSach implements ActionListener {
	private String URL = "jdbc:sqlserver://TOG:1433;databaseName=DLSach2;encrypt=false";
	private String username = "sa";
	private String password = "123456";
	private Connection connection;
	private GUI_delSA gui;
	
	
    // Constructor nhận tham chiếu đến GUI_findHV
    public XLSach(GUI_delSA gui) {
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
	
	public void getSA() {
		Connection connection = getCon();
		String query = "SELECT * FROM tbSach";
		
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) gui.saList.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trước khi thêm dữ liệu mới
            
            while (rs.next()) {
                String MaS = rs.getString("MaS");
                String TenS = rs.getString("TenS");
                int NamXB = rs.getInt("NamXB");
                int GiaB = rs.getInt("GiaB");
                
                Sach temp = new Sach(MaS, TenS, NamXB, GiaB);
                double Thanhtien = temp.Thanhtien(NamXB, GiaB);
                
                // Thêm dữ liệu vào bảng
                Object[] row = {
                	MaS,
                	TenS,
               		NamXB,
               		GiaB,
               		Thanhtien
                };
                model.addRow(row);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSA() {
	    int confirm = JOptionPane.showConfirmDialog(
	        gui, 
	        "Bạn có chắc chắn muốn xóa sách này không?", 
	        "Xác nhận xóa", 
	        JOptionPane.YES_NO_OPTION
	    );

	    if (confirm == JOptionPane.YES_OPTION) {
	        Connection connection = getCon();
	        String query = "DELETE FROM tbSach WHERE NamXB = ?";
	            
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setInt(1, Integer.parseInt(gui.NamXB.getSelectedItem().toString()));
	            stmt.executeUpdate();
	                
	            getSA(); // Cập nhật lại danh sách sau khi xóa
	            JOptionPane.showMessageDialog(gui, "Xóa thành công!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(gui, "Xóa thất bại! Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        return;
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == gui.delButton) {
			deleteSA();
		}
	}
}
