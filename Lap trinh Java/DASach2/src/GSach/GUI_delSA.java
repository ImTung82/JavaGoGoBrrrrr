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

public class GUI_delSA extends JFrame {
	JLabel NamXBLabel, GiaBLabel;
	JComboBox<String> NamXB;
	JTextField GiaBTextField;
	JButton delButton;
	JTable saList;
	
	String columnNames[] = {"Mã sách", "Tên sách", "Năm xuất bản", "Giá bán", "Thành tiền"};
	
	XLSach actionListener = new XLSach(this);
	
	public void appGUI() {
        setTitle("Quản lý sách");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        NamXBLabel = new JLabel("Nhà xuất bản");
        NamXB = new JComboBox<>(new String[] {"2019", "2020", "2021"});
        inputPanel.add(NamXBLabel);
        inputPanel.add(NamXB);
        
        add(inputPanel, BorderLayout.NORTH);
        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        saList = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(saList);
        
        add(tableScrollPane, BorderLayout.CENTER);
        
        JPanel buttonMenuPanel = new JPanel(new FlowLayout());
        buttonMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        delButton = new JButton("Xóa sách");
        buttonMenuPanel.add(delButton);
        delButton.addActionListener(actionListener);
        
        add(buttonMenuPanel, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        actionListener.getSA();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI_delSA gui = new GUI_delSA();
		gui.appGUI();
	}

}
