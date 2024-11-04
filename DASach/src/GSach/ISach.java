package GSach;

import java.awt.event.ActionEvent;
import java.sql.Connection;

public interface ISach {
	Connection getCon();

	void getSA();

	void getSAbyNXBGB();

	void actionPerformed(ActionEvent e);
}
