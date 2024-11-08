package GLuong;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import GLuong.XLLuong;

public class GUI_updateNV extends JFrame {
	JTextField MaNVField;
	JTextField HotenField;
	JTextField LuongField;
	JTextField searchField;
	private JLabel MaMVLabel, HotenLabel, LuongLabel, DiachiLabel;
	JComboBox<String> DiachiCB;
	JButton searchButton;
	JButton updateButton;
	
    // Khởi tạo actionListener với tham chiếu đến GUI
    XLLuong actionListener = new XLLuong(this);
	
	public void appGUI() {
        setTitle("Quản lý lương");
        setSize(650, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        MaMVLabel = new JLabel("Mã NV:");
        MaNVField = new JTextField();
        MaNVField.setEditable(false);
        inputPanel.add(MaMVLabel);
        inputPanel.add(MaNVField);
        
        HotenLabel = new JLabel("Họ tên:");
        HotenField = new JTextField();
        inputPanel.add(HotenLabel);
        inputPanel.add(HotenField);
        
        DiachiLabel = new JLabel("Địa chỉ:");
        inputPanel.add(DiachiLabel);
        DiachiCB = new JComboBox<>(new String[] {"Hải Phòng", "Hà Nội", "Nam Định"});
        inputPanel.add(DiachiCB);
        
        LuongLabel = new JLabel("Lương:");
        LuongField = new JTextField();
        inputPanel.add(LuongLabel);
        inputPanel.add(LuongField);
        
        add(inputPanel, BorderLayout.NORTH);
        
        JPanel buttonMenuPanel = new JPanel(new FlowLayout());
        buttonMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Trên, trái, dưới, phải
        
        searchButton = new JButton("Tìm kiếm nhân viên");
        buttonMenuPanel.add(searchButton);
        searchButton.addActionListener(actionListener);
        
        searchField = new JTextField(12);
        buttonMenuPanel.add(searchField);
        
        buttonMenuPanel.add(new JLabel(" || "));
        
        updateButton = new JButton("Cập nhật nhân viên");
        buttonMenuPanel.add(updateButton);
        updateButton.addActionListener(actionListener);
        
        add(buttonMenuPanel, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);  
	}
	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
        // Đảm bảo giao diện người dùng được khởi tạo trên luồng sự kiện
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI_updateNV gui = new GUI_updateNV(); // Tạo đối tượng GUI_updateNV
                gui.appGUI(); // Gọi phương thức appGUI() trên đối tượng
            }
        });
	}

}
