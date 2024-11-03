package GDiem;

import java.awt.event.ActionEvent;
import java.sql.Connection;

public interface IHocvien {
	public Connection getCon();
	void actionPerformed(ActionEvent e);
	void getHVbyLop(String maHV);
	void getHVbyLopGT(String lop, String gt);
}
