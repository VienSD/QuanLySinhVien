package Main;

import java.io.File;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import Task.Search;
import Task.ShowTableMark;
import Task.UpdateList;

public class Main {
	private static String s = 	"───────────────────────────────────────────────────";
	private static String s1 = "┌─────────────────────────────────────────────────┐";
	
	private static String s2 = "├─────────────────────────────────────────────────┤";
	
	private static String s3 = "└─────────────────────────────────────────────────┘";
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// chạy trên cmd
		if (args[0].startsWith("-dir=") == false) {
			System.out.println("Bạn nhập sai định dạng đầu vào ");
		} else {
			String partten = args[0].substring(5, args[0].length());
			System.out.println(partten);
			File fileSt = new File(partten + "\\sinhvien_en.txt");
			File fileOb = new File(partten + "\\monhoc_en.txt");
			File fileMk = new File(partten + "\\diem.txt");
			if (fileSt.exists() == true && fileOb.exists() == true && fileMk.exists() == true) {
				int choose;
				do {
					System.out.println("\t\t\t" + s1);
					System.out.println("\t\t\t|\tQUẢN LÝ SINH VIÊN \t\t|\t\t");
					System.out.println("\t\t\t" + s3);
					while (true) {
						choose = 1;
					menuPrint();
						System.out.println("Chọn:");
						try {
							String stringChoose = sc.nextLine();
							choose = Integer.parseInt(stringChoose);
							switch (choose) {
							case 1:
								UpdateList.updateList(partten);
								break;
							case 2:
								ShowTableMark.showList(partten);
								break;
							case 3:
								Search.searchInfo(partten);
								break;
							case 0:
								break;
							default:
								System.out.println("Không có lựa chọn này");
								break;
							}
						} catch (Exception e) {
							System.out.println("Nhập sai");
						} finally {
							if (choose == 0) {
								break;
							}
						}
					}

				} while (choose != 0);
			} else {
				System.out.println("File vừa nhập không tồn tại "
						+ "hoặc không có file dữ liệu\n,"
						+ " mời bạn nhập lại đường dẫn");
			}
		}
	
	}
	

	
	private static void menuPrint() {
		System.out.println("\n" + s1);
		System.out.println("|                      MENU                       |");
		System.out.println(s2);

		System.out.format("| %-2d. %-44s|", 1, "Cập Nhật Danh sách");
		System.out.format("\n| %-2d. %-44s|", 2, "Hiển thị Bảng Điểm");
		System.out.format("\n| %-2d. %-44s|", 3, "Tìm kiếm ");
		System.out.format("\n| %-2d. %-44s|", 0, "Thoát");
		System.out.println("\n"+s3);
	}

}
