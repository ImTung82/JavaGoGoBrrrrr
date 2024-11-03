package BookPackage;

public class Document {
	private String ID;
	private String Name;
	
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Document(String iD, String name) {
		super();
		ID = iD;
		Name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
