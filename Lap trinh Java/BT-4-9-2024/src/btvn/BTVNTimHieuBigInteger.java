package btvn;

// Trong bài toán Fibonacci, với kiểu dữ liệu long, ta sẽ gặp tình trạng tràn số bắt đầu từ số Fibonacci thứ 93 trở đi
// Ta có thể khắc phục điều này bằng cách sử dụng kiểu dữ liệu BigInteger
// Kiểu dữ liệu BigInteger không có giới hạn về kích thước
import java.math.BigInteger;

public class BTVNTimHieuBigInteger {
    public static void main(String[] args) {
        System.out.println("100 số Fibonacci đầu tiên:");
        // 2 số đầu tiên của dãy Fibonacci là 1
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.valueOf(1);
        
        // In ra hai số đầu tiên của dãy Fibonacci
        System.out.print(" " + a + " " + b);
        
        // Bắt đầu xuất ra màn hình sử dụng vòng lặp từ số thứ 3 
        for (int i = 2; i < 100; ++i) {
            // Xuống dòng mỗi 10 số
            if (i % 10 == 0) {
                System.out.println();
            }
            
            // Số tiếp theo của dãy Fibonacci
            BigInteger next = a.add(b);
            
            // In ra số Fibonacci tiếp theo
            System.out.print(" " + next);
            
            // Cập nhật cho lần lặp tiếp theo
            a = b;
            b = next;    
        }
	}
    
}