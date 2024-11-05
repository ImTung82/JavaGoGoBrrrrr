

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class TestMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<InternationalAirport> InternationalAirport_list = new ArrayList<InternationalAirport>();
        ArrayList<MilitaryAirport> MilitaryAirport_list = new ArrayList<MilitaryAirport>();

        while (true) {
            int choice;

            System.out.println("* CHƯƠNG TRÌNH QUẢN LÝ SÂN BAY *\n");
            System.out.println("=======================");
            System.out.println("1. Thêm sân bay");
            System.out.println("2. Xóa sân bay");
            System.out.println("3. Hiển thị danh sách sân bay");
            System.out.println("4. Tìm kiếm sân bay");
            System.out.println("0. Dừng chương trình");
            System.out.println("=======================");

            System.out.print("\nVui lòng nhập lựa chọn của bạn: ");

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Lỗi: Vui lòng nhập số.");
                sc.nextLine(); // clear the invalid input
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        int add_choice;
                        System.out.print("Nhập Lựa Chọn (1: International Airport || 2: Military Airport): ");
                        add_choice = sc.nextInt();

                        if (add_choice == 1) {
                            InternationalAirport temp = new InternationalAirport();
                            temp.input();
                            InternationalAirport_list.add(temp);
                        } else if (add_choice == 2) {
                            MilitaryAirport temp = new MilitaryAirport();
                            temp.input();
                            MilitaryAirport_list.add(temp);
                        }

                        System.out.println("\n* Thông báo: Thêm sân bay thành công.\n\n");
                        break;

                    case 2:
                        // logic for deleting airport...
                        break;

                    case 3:
                        // logic for displaying airports...
                        break;

                    case 4:
                        // logic for searching airports...
                        break;

                    case 0:
                        System.out.println("\n* Thông báo: Dừng chương trình thành công.");
                        sc.close();
                        return;

                    default:
                        System.out.println("Đầu vào không hợp lệ. Vui lòng lựa chọn lại.");
                }
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: " + e.getMessage());
            } finally {
                System.out.println("Hoàn tất lệnh.");
            }
        }
    }
}
