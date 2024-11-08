package GSach;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class XLSach implements ISach, ActionListener {
	private String URL = "jdbc:sqlserver://TOG:1433;databaseName=DLSach;encrypt=false";
	private String username = "sa";
	private String password = "123456";
	private Connection connection;
	private GUI_findSA gui;
	
    // Constructor nhận tham chiếu đến GUI_findHV
    public XLSach(GUI_findSA gui) {
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
                String NhaXB = rs.getString("NhaXB");
                int NamXB = rs.getInt("NamXB");
                int GiaB = rs.getInt("GiaB");
                
                Sach temp = new Sach(MaS, TenS, NhaXB, NamXB, GiaB);
                double KhuyenMai = temp.Khuyenmai(NamXB, GiaB);
                
                // Thêm dữ liệu vào bảng
                Object[] row = {
                	MaS,
                	TenS,
               		NhaXB,
               		NamXB,
               		GiaB,
               		KhuyenMai
                };
                model.addRow(row);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getSAbyNXBGB() {
		Connection connection = getCon();
		String query = "SELECT * FROM tbSach WHERE NhaXB = ? AND GiaB = ?";
		
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, gui.NhaXBCB.getSelectedItem().toString());
			stmt.setInt(2, Integer.parseInt(gui.GiaBTextField.getText()));
			
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) gui.saList.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trước khi thêm dữ liệu mới
            
            while (rs.next()) {
                String MaS = rs.getString("MaS");
                String TenS = rs.getString("TenS");
                String NhaXB = rs.getString("NhaXB");
                int NamXB = rs.getInt("NamXB");
                int GiaB = rs.getInt("GiaB");
                
                Sach temp = new Sach(MaS, TenS, NhaXB, NamXB, GiaB);
                double KhuyenMai = temp.Khuyenmai(NamXB, GiaB);
                
                // Thêm dữ liệu vào bảng
                Object[] row = {
                	MaS,
                	TenS,
               		NhaXB,
               		NamXB,
               		GiaB,
               		KhuyenMai
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
		Object source = e.getSource();
		
		if (source == gui.searchButton) {
			getSAbyNXBGB();
		}
	}
}
