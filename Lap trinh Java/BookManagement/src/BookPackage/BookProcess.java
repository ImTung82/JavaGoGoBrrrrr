package BookPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BookProcess implements ActionListener {
	private String URL = "jdbc:sqlserver://TOG:1433;databaseName=BookDB;encrypt=false";
	private String username = "sa";
	private String password = "123456";
	private Connection connection;
	
	private GUIBook gui;
	
    // Constructor nhận tham chiếu đến GUI_findHV
    public BookProcess(GUIBook gui) {
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
	
	public void getBookbyID() {
		// TODO Auto-generated method stub
		Connection connection = getCon();
		String query = "SELECT * FROM tbBook";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) gui.bookList.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trước khi thêm dữ liệu mới
            
            while (rs.next()) {
                String ID = rs.getString("ID");
                String Name = rs.getString("Name");
                String Year = rs.getString("Year");
                String Author = rs.getString("Author");
                int Price = rs.getInt("Price");
                
                // Thêm dữ liệu vào bảng
                Object[] row = {
                	ID,
                	Name,
               		Year,
               		Author,
               		Price
                };
                model.addRow(row);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertBook() {
		Connection connection = getCon();
		String query = "INSERT INTO tbBook VALUES (?, ?, ?, ?, ?)";
		
		String ID = gui.IDField.getText();
		String Name = gui.NameField.getText();
		String Year = gui.YearCB.getSelectedItem().toString();
		String Author = gui.AuthorField.getText();
		String Price = gui.PriceField.getText();
		
		if (ID.isEmpty() || Name.isEmpty() || Year.isEmpty() || Author.isEmpty() || gui.PriceField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(gui, "Vui lòng điền đầy đủ thông tin.", "Thông báo", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, ID);
			stmt.setString(2, Name);
			stmt.setString(3, Year);
			stmt.setString(4, Author);
			stmt.setInt(5, Integer.parseInt(Price));
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(gui, "Thêm sách thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
	        getBookbyID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(gui, "Thêm sách thất bại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void updateBook() {
        Connection connection = getCon();
        String query = "UPDATE tbBook SET Name = ?, Year = ?, Author = ?, Price = ? WHERE ID = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
        	stmt.setString(1, gui.NameField.getText());
        	stmt.setString(2, gui.YearCB.getSelectedItem().toString());
        	stmt.setString(3, gui.AuthorField.getText());
        	stmt.setInt(4, Integer.parseInt(gui.PriceField.getText()));
        	stmt.setString(5, gui.IDField.getText());
        	
        	gui.IDField.setText("");
        	gui.NameField.setText("");
        	gui.AuthorField.setText("");
        	gui.PriceField.setText("");
        	gui.YearCB.setSelectedItem("2018");
        	
        	stmt.executeUpdate();
        	
            getBookbyID();
        	JOptionPane.showMessageDialog(gui, "Cập nhật thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(gui, "Cập nhật thất bại", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		
		if (source == gui.addButton) {
			insertBook();
		} else if (source == gui.updateButton) {
			updateBook();
		}
	}

}
