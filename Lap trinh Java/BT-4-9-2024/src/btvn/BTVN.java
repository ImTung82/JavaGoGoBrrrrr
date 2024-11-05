package btvn;

public class BTVN {

	public static void main(String[] args) {
		System.out.println("100 số Fibonacci đầu tiên:");
		// 2 số đầu tiên của dãy Fibonacci là 1
		long a = 1, b = 1;
		
		System.out.print(" " + a + " " + b);
		
		// Bắt đầu xuất ra màn hình sử dụng vòng lặp từ số thứ 3 
		for (int i = 2; i < 100; ++i) {
			// Xuống dòng mỗi 10 số
			if (i % 10 == 0) {
				System.out.println();
			}
			
			// Số tiếp theo của dãy Fibonacci
			long next = a + b;
			
			// In ra số Fibonacci tiếp theo
			System.out.print(" " + next);
			
			// Cập nhật cho lần lặp tiếp theo
			a = b;
			b = next;	
		}

	}
}