package Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Class.Student;

public class Search {
	
	private static String s1 = "┌─────────────────────────────────────────────────┐";
	
	private static String s2 = "├─────────────────────────────────────────────────┤";
	
	private static String s3 = "└─────────────────────────────────────────────────┘";
	private static Scanner sc = new Scanner(System.in);

	public static void searchInfo(String str) {
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
						System.out.println("Nhập mã sinh viên:");
						String stringId = sc.nextLine();
						if (stringId.startsWith("SV") == false) {
							System.out.println("nhập theo dạng chuẩn SV.....");
							break;
						}
						int idSearch = 0;
						try {
							idSearch = Integer.parseInt(stringId.substring(3));
						} catch (Exception e) {
							System.out.println("sau 'SV' phải gồm chuỗi số");
							break;
						}
						showById(idSearch, str);
						break;
					case 2:
						System.out.println("Nhập tên sinh viên:");
						String stringName = sc.nextLine();
						showListByName(stringName, str);
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

	private static void showListByName(String stringName, String str) {
		FileReader in = null;
		BufferedReader bfr = null;
		ArrayList<Student> listSt = new ArrayList<>();
		ArrayList<Student> listStSrc = new ArrayList<>();
		try {
			in = new FileReader(str + "\\sinhvien_en.txt");
			bfr = new BufferedReader(in);
			String line;
			while ((line = bfr.readLine()) != null) {
				if (line.startsWith("#"))
					continue;
				listSt.add(Student.createSt(line));
			}
			
			for (Student student : listSt) {
				if (student.getLastName().equals(stringName))
					listStSrc.add(student);
			}
			if (listStSrc.size() == 0) {
				System.out.println("Không có sinh viên có tên: " + stringName );
			}
			else {
				listStSrc.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
				int page = 1;
				if (listStSrc.size() < 20 ) {
					page = 1;
				}
				else page =  (listStSrc.size() % 20 == 0) ? listStSrc.size() / 20 : (listStSrc.size() / 20 + 1);
				int pageShow = 0;
				int choose;
				do {
					UpdateList.printPage(pageShow, page, listStSrc);
					while (true) {
						
						choose = 1;
						System.out.format("%-25s", "\n1. Xem trang tiếp theo ");
						System.out.format("%-25s", "2. Trở về trang trước ");
						System.out.format("%-25s", "3. Dến trang cuối ");
						System.out.format("%-25s", "\n4. Dến trang đầu tiên ");
						System.out.format("%-25s", "5. Xem trang cụ thể ");
						System.out.format("%-25s", "6. Xem chi tiết điểm ");
						System.out.format("%-25s", "\n0. Để quay lại ");
						System.out.println("\nChọn:");
						try {
							String stringChoose = sc.nextLine();
							choose = Integer.parseInt(stringChoose);
							switch (choose) {
							case 1:
								if (page == 1) {
									System.out.println("chỉ có 1 trang");
									UpdateList.printPage(0, page, listStSrc);
									break;
								}
								if (pageShow == (page - 1)) {
									System.out.println("bạn đang ở trang cuối rồi");
									UpdateList.printPage(pageShow, page, listStSrc);
									break;
								}
								UpdateList.printPage(++pageShow, page, listStSrc);
								break;
							case 2:
								if (pageShow == 0) {
									System.out.println("bạn đang ở đầu trang rồi");
									UpdateList.printPage(pageShow, page, listStSrc);
									break;
								}
								UpdateList.printPage(--pageShow, page, listStSrc);
								break;
							case 3:
								pageShow = page;
								UpdateList.printPage(pageShow - 1, page, listStSrc);
								break;
							case 4:
								pageShow = 0;
								UpdateList.printPage(pageShow, page, listStSrc);
								break;
							case 5:
								if (page == 1) {
									System.out.println("chỉ có 1 trang");
									UpdateList.printPage(0, page, listStSrc);
									break;
								}
								System.out.println("\nChọn trang:");
								String stringPage = sc.nextLine();
								int tmp = Integer.parseInt(stringPage);
								if (tmp > page) {
									System.out.println("không có trang bạn vừa chọn");
									UpdateList.printPage(pageShow, page, listStSrc);
									break;
								}
								pageShow = tmp;
								UpdateList.printPage(--pageShow, page, listStSrc);
								break;
							case 6:
								System.out.println("Nhập mã sv:");
								String stringId = sc.nextLine();
								if (stringId.startsWith("SV") == false) {
									System.out.println("nhập theo dạng chuẩn SV.....");
									UpdateList.printPage(pageShow, page, listStSrc);
									break;
								}
								int idSearch = 0;
								try {
									idSearch = Integer.parseInt(stringId.substring(3));
								} catch (Exception e) {
									System.out.println("sau 'SV' phải gồm chuỗi số");
									UpdateList.printPage(pageShow, page, listStSrc);
									break;
								}
								UpdateList.showMarkById(idSearch, listSt, str);
								break;
							case 0:
								break;
							default:
								System.out.println("Không có lựa chọn này");
								UpdateList.printPage(pageShow, page, listStSrc);
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bfr.close(); // dong luong
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static void showById(int idSearch, String str) {
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

			printInfo(idSearch, listSt, str);
		} catch (IOException | NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void printInfo(int idSearch, ArrayList<Student> listSt, String str) {
		try {
			UpdateList.showMarkById(idSearch, listSt, str);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static void menuPrint() {
		System.out.println("\n" + s1);
		System.out.println("|                     Tìm kiếm                    |");
		System.out.println(s2);
		System.out.format("| %-2d. %-44s|", 1, "Tìm kiếm theo mã sinh viên");
		System.out.format("\n| %-2d. %-44s|", 2, "Tìm kiếm theo tên sinh viên");
		System.out.format("\n| %-2d. %-44s|", 0, "Quay lại");
		System.out.println("\n" + s3);
	}
	
}
