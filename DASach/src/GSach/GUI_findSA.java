package GSach;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class GUI_findSA extends JFrame{
	JLabel NhaXBLabel, GiaBLabel;
	JComboBox<String> NhaXBCB;
	JTextField GiaBTextField;
	JButton searchButton;
	JTable saList;
	
	String columnNames[] = {"Mã sách", "Tên sách", "Nhà xuất bản", "Năm xuất bản", "Giá bán", "Khuyến mại"};
	
	XLSach actionListener = new XLSach(this);
	
	public void appGUI() {
        setTitle("Quản lý sách");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        NhaXBLabel = new JLabel("Nhà xuất bản");
        NhaXBCB = new JComboBox<>(new String[] {"Kim Đồng", "Giáo dục", "Thanh niên"});
        inputPanel.add(NhaXBLabel);
        inputPanel.add(NhaXBCB);
        
        GiaBLabel = new JLabel("Giá bán:");
        GiaBTextField = new JTextField();
        inputPanel.add(GiaBLabel);
        inputPanel.add(GiaBTextField);
        
        add(inputPanel, BorderLayout.NORTH);
        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        saList = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(saList);
        
        add(tableScrollPane, BorderLayout.CENTER);
        
        JPanel buttonMenuPanel = new JPanel(new FlowLayout());
        buttonMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        searchButton = new JButton("Tìm kiếm nhân viên");
        buttonMenuPanel.add(searchButton);
        searchButton.addActionListener(actionListener);
        
        add(buttonMenuPanel, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        actionListener.getSA();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI_findSA gui = new GUI_findSA();
		gui.appGUI();
	}
}
