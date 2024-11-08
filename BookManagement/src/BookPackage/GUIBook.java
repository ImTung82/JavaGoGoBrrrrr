package BookPackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

public class GUIBook extends JFrame {
	JLabel IDLabel, NameLabel, AuthorLabel, PriceLabel, YearLabel;
	JTextField IDField, NameField, AuthorField, PriceField;
	JComboBox<String> YearCB;
	JButton addButton, updateButton;
	JTable bookList;
	String[] columnNames = {"Mã sách", "Tên sách", "Năm xuất bản", "Tác giả", "Giá bán"};
	
	BookProcess actionListener = new BookProcess(this);
	
	public void appGUI() {
        setTitle("Quản lý sách");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        IDLabel = new JLabel("Mã sách:");
        IDField = new JTextField();
        inputPanel.add(IDLabel);
        inputPanel.add(IDField);
        
        NameLabel = new JLabel("Tên sách:");
        NameField = new JTextField();
        inputPanel.add(NameLabel);
        inputPanel.add(NameField);
        
        YearLabel = new JLabel("Năm xuất bản:");
        inputPanel.add(YearLabel);
        YearCB = new JComboBox<>(new String[] {"2018", "2019", "2020"});
        inputPanel.add(YearCB);
        
        AuthorLabel = new JLabel("Tác giả:");
        AuthorField = new JTextField();
        inputPanel.add(AuthorLabel);
        inputPanel.add(AuthorField);
        
        PriceLabel = new JLabel("Giá bán:");
        PriceField = new JTextField();
        inputPanel.add(PriceLabel);
        inputPanel.add(PriceField);
        
        add(inputPanel, BorderLayout.NORTH);
        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        bookList = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookList);
        
        // Thêm MouseListener để lấy dữ liệu khi chọn một dòng trong bảng
        bookList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = bookList.getSelectedRow(); // Lấy chỉ số của dòng được chọn
                if (selectedRow != -1) { // Nếu có dòng nào đó được chọn
                    // Lấy dữ liệu từ các cột của dòng đó và đặt vào các JTextField và JComboBox
                    IDField.setText(bookList.getValueAt(selectedRow, 0).toString());
                    NameField.setText(bookList.getValueAt(selectedRow, 1).toString());
                    YearCB.setSelectedItem(bookList.getValueAt(selectedRow, 2).toString());
                    AuthorField.setText(bookList.getValueAt(selectedRow, 3).toString());
                    PriceField.setText(bookList.getValueAt(selectedRow, 4).toString());
//                    IDField.setEditable(false);
                }
            }
        });
        
        add(tableScrollPane, BorderLayout.CENTER);
        
        JPanel buttonMenuPanel = new JPanel(new FlowLayout());
        buttonMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Trên, trái, dưới, phải
        
        addButton = new JButton("Add Book");
        buttonMenuPanel.add(addButton);
        addButton.addActionListener(actionListener);
        
        buttonMenuPanel.add(new JLabel(" || "));
        
        updateButton = new JButton("Update Book");
        buttonMenuPanel.add(updateButton);
        updateButton.addActionListener(actionListener);
        
        add(buttonMenuPanel, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        // Gọi getBookbyID() sau khi đã khởi tạo giao diện
        actionListener.getBookbyID();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUIBook gui = new GUIBook();
		gui.appGUI();
	}

}
