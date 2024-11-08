package GDiem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUI_findHV extends JFrame {
	JComboBox<String> lopComboBox;
	JRadioButton maleGTRadioButton;
	JRadioButton femaleGTRadioButton;
	JButton searchButton;
	JTable studentTable;
	private JLabel lopLabel, GTLabel;
	private String[] columnNames = {"Mã học viên", "Họ tên", "Giới tính", "Lớp", "Điểm", "Kết quả"};
	
    // Khởi tạo actionListener với tham chiếu đến GUI
    XLDiem actionListener = new XLDiem(this);
	
	public void appGUI() {
        setTitle("Quản lý điểm");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        lopLabel = new JLabel("Lớp");
        lopComboBox = new JComboBox<>(new String[] {"63PM1", "63PM2", "63TH1", "63TH2"});
        inputPanel.add(lopLabel);
        inputPanel.add(lopComboBox);
        
        ButtonGroup radioButtonGroup = new ButtonGroup();
        maleGTRadioButton = new JRadioButton("Nam");
        femaleGTRadioButton = new JRadioButton("Nữ");
        
        maleGTRadioButton.addActionListener(actionListener);
        femaleGTRadioButton.addActionListener(actionListener);
        
        radioButtonGroup.add(maleGTRadioButton);
        radioButtonGroup.add(femaleGTRadioButton);
        
        
        JPanel radioButtonPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        radioButtonPanel.add(maleGTRadioButton);
        radioButtonPanel.add(femaleGTRadioButton);
        
        GTLabel = new JLabel("Giới tính");
        
        inputPanel.add(GTLabel);
        inputPanel.add(radioButtonPanel);
        
        add(inputPanel, BorderLayout.NORTH);
        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        add(tableScrollPane, BorderLayout.CENTER);
        
        JPanel buttonMenuPanel = new JPanel(new FlowLayout());
        buttonMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Trên, trái, dưới, phải
        
        searchButton = new JButton("Tìm kiếm");
        buttonMenuPanel.add(searchButton);
        searchButton.addActionListener(actionListener);    
        
        add(buttonMenuPanel, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI_findHV gui = new GUI_findHV();
		gui.appGUI();
	}
}