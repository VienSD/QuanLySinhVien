package Task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Class.Student;

public class ShowTableMark {
	private static String s1 = "┌─────────────────────────────────────────────────┐";
	
	private static String s2 = "├─────────────────────────────────────────────────┤";
	
	private static String s3 = "└─────────────────────────────────────────────────┘";
	private static Scanner sc = new Scanner(System.in);
	public static void showList(String str) {
		int choose;
		do {
			while (true) {
				choose = 1;
				menuPrint();
				System.out.println("Chọn:");
				try {
					String stringChoose = sc.nextLine();
					choose = Integer.parseInt(stringChoose);
					switch (choose) {
					case 1:
						showListMark(str);
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
		
	}



	



	private static void showListMark(String str) {
		FileReader in = null;
		BufferedReader bfr = null;
		ArrayList<Student> listSt = new ArrayList<>();
		try {
			in = new FileReader(str + "\\sinhvien_en.txt");
			bfr = new BufferedReader(in);
			String line;
			while ((line = bfr.readLine()) != null) {
				if (line.startsWith("#"))
					continue;
				listSt.add(Student.createSt(line));
			}
			listSt.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
			int page = 1;
			if (listSt.size() < 20 ) {
				page = 1;
			}
			else page =  (listSt.size() % 20 == 0) ? listSt.size() / 20 : (listSt.size() / 20 + 1);
			int pageShow = 0;
			int choose;
			do {
				printPage(pageShow, page, listSt, str);
				while (true) {
					
					choose = 1;
					System.out.format("%-25s", "\n1. Xem trang tiếp theo ");
					System.out.format("%-25s", "2. Trở về trang trước ");
					System.out.format("%-25s", "3. Dến trang cuối ");
					System.out.format("%-25s", "\n4. Dến trang đầu tiên ");
					System.out.format("%-25s", "5. Xem trang cụ thể ");;
					System.out.format("%-25s", "0. Để quay lại ");
					System.out.println("\nChọn:");
					try {
						String stringChoose = sc.nextLine();
						choose = Integer.parseInt(stringChoose);
						switch (choose) {
						case 1:
							if (page == 1) {
								System.out.println("chỉ có 1 trang");
								printPage(0, page, listSt, str);
								break;
							}
							if (pageShow  == page - 1) {
								System.out.println("bạn đang ở trang cuối rồi");
								printPage(pageShow, page, listSt, str);
								break;
							}
							
							printPage(++pageShow, page, listSt, str);
							break;
						case 2:
							if (pageShow == 0) {
								System.out.println("bạn đang ở đầu trang rồi");
								printPage(pageShow, page, listSt, str);
								break;
							}
							printPage(--pageShow, page, listSt, str);
							break;
						case 3:
							pageShow = page;
							printPage(pageShow -1, page, listSt, str);
							break;
						case 4:
							pageShow = 0;
							printPage(pageShow, page, listSt, str);
							break;
						case 5:
							System.out.println("\nChọn trang:");
							String stringPage = sc.nextLine();
							int tmp = Integer.parseInt(stringPage);
							if (tmp > page) {
								System.out.println("không có trang bạn vừa chọn");
								printPage(pageShow, page, listSt, str);
								break;
							}
							pageShow = tmp;
							printPage(--pageShow, page, listSt, str);
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
		} catch (IOException | NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	private static void printPage(int pageShow, int page, ArrayList<Student> listSt, String str) {
		System.out.println("\n---------------------DANH SÁCH SINH VIÊN-----------------------------");
		System.out.println("-----------------------Trang " + (pageShow + 1) + "/" + page + "-----------------------------------");
		for (int i = 20 * pageShow; i < 20 * (pageShow + 1); i++) {
			if(i > listSt.size())
				break;
			UpdateList.showMarkById(listSt.get(i).getID(), listSt, str);
		}
		System.out.println("-----------------------Trang " + (pageShow + 1) + "/" + page + "-----------------------------------");
		
	}







	private static void menuPrint() {
		System.out.println("\n" +s1);
		System.out.println("|                Hiển thị bảng điểm               |");
		System.out.println(s2);
		System.out.format("| %-2d. %-44s|", 1, "Hiển thị theo ds tên Môn");
		System.out.format("\n| %-2d. %-44s|", 0, "Quay lại");
		System.out.println("\n" + s3);
	}
	
}

