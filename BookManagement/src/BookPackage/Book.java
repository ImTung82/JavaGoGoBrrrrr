package BookPackage;

public class Book extends Document {
	private String Year;
	private String Author;
	private int Price;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Book(String iD, String name) {
		super(iD, name);
		// TODO Auto-generated constructor stub
	}

	public Book(String year, String author, int price) {
		super();
		Year = year;
		Author = author;
		Price = price;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}
}
