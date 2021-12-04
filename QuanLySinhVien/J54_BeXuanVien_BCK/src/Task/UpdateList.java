package Task;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Class.Mark;
import Class.Student;
import Class.Subject;

public class UpdateList {
	private static String s = 	"───────────────────────────────────────────────────";
	private static String s1 = "┌─────────────────────────────────────────────────┐";
	
	private static String s2 = "├─────────────────────────────────────────────────┤";
	
	private static String s3 = "└─────────────────────────────────────────────────┘";
	private static Scanner sc = new Scanner(System.in);
	

	public static void updateList(String str) {
		int choose;
		do {
			while (true) {
				choose = 1;
				menuPrintUpdate();
				System.out.println("Chọn:");
				try {
					String stringChoose = sc.nextLine();
					choose = Integer.parseInt(stringChoose);
					switch (choose) {
					case 1:
						updateListStudent(str);
						break;
					case 2:
						modifyListStudent(str);
						break;
					case 3:
						deletStudent(str);
						break;
					case 4:
						showListStudent(str);
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

	private static FileWriter out = null;
	private static BufferedWriter bfw = null;

	private static void deletStudent(String str) {
		try {
			ArrayList<Student> listSt = new ArrayList<>();
			in = new FileReader(str + "\\sinhvien_en.txt");
			bfr = new BufferedReader(in);
			String line;
			while ((line = bfr.readLine()) != null) {
				if (line.startsWith("#"))
					continue;
				listSt.add(Student.createSt(line));
			}
			bfr.close();

			ArrayList<Mark> listMk = new ArrayList<>();
			in = new FileReader(str + "\\diem.txt");
			bfr = new BufferedReader(in);
			String lineMk = null;
			while ((lineMk = bfr.readLine()) != null) {
				if (lineMk.startsWith("#"))
					continue;
				listMk.add(Mark.createSt(lineMk));
			}
			bfr.close();

			out = new FileWriter(str + "\\sinvien_en.txt");
			bfw = new BufferedWriter(out);

			System.out.println("Nhập mã sv:");
			String stringId = sc.nextLine();
			if (stringId.startsWith("SV") == false) {
				System.out.println("vui lòng nhập chuẩn mã sv dạng 'SV...' vd: SV00001");
			} else {
				int idSearch = 0;
				try {
					String stringIdSea = null;
					idSearch = Integer.parseInt(stringId.substring(3));
					int esistSt = 0;
					for (Student student : listSt) {
						if (student.getID() == idSearch)
							esistSt = 1;
					}
					if (esistSt != 0) {
						int check = 0;
						for (int i = 0; i < listMk.size(); i++) {
							if (idSearch == listMk.get(i).getIDStudent()) {
								check++;
							}
						}
						System.out.println(listMk.get(check));
						System.out.println(listMk.size());
						if (check != 0) {
							System.out.println("không được xóa sv " + "đã học");
						} else {
							for (int i = 0; i < listSt.size(); i++) {
								if (listSt.get(i).getID() == idSearch) {
									stringIdSea = "SV" + String.format("%05d", listSt.get(i).getID());
									listSt.remove(i);
									break;
								}
							}
							in = new FileReader(str + "\\sinhvien_en.txt");
							bfr = new BufferedReader(in);
							while ((line = bfr.readLine()) != null) {
								bfw.write("");
							}
							for (Student student : listSt) {
								bfw.write(student.toString());
								bfw.newLine();
							}
							bfr.close();
							System.out.println("Sinh viên mã " + stringIdSea + " đã được xóa");
						}
					} else {
						System.out.println("Không tồn tại sinh viên vừa nhập");
					}
				} catch (Exception e) {
					System.out.println("sau 'SV' phải gồm chuỗi số");
				} finally {
					bfr.close();
					bfw.close();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bfr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static void modifyListStudent(String str) {
		try {
			ArrayList<Student> listSt = new ArrayList<>();
			in = new FileReader(str + "\\sinhvien_en.txt");
			bfr = new BufferedReader(in);
			String line;
			while ((line = bfr.readLine()) != null) {
				if (line.startsWith("#"))
					continue;
				listSt.add(Student.createSt(line));
			}
			bfr.close();
			System.out.println("Nhập mã sv:");
			String stringId = sc.nextLine();
			if (stringId.startsWith("SV") == false) {
				System.out.println("vui lòng nhập chuẩn mã sv dạng 'SV...' vd: SV00001");
			} else {
				int idSearch = 0;
				try {
					String stringIdSea = null;
					idSearch = Integer.parseInt(stringId.substring(3));
					int esistSt = 0;
					for (Student student : listSt) {
						if (student.getID() == idSearch)
							esistSt = 1;
					}
					if (esistSt != 0) {
						for (int i = 0; i < listSt.size(); i++) {
							if(listSt.get(i).getID() == idSearch)
								listSt.remove(i);
						}
						out = new FileWriter(str + "\\sinhvien_en.txt");
						bfw = new BufferedWriter(out);
						System.out.println(s + "\nNhập thông tin sinh viên muốn sửa định dạng như sau:");
						System.out.println("[Họ dệm];[Tên];[Ngày sinh];[Giới tính]" + "\nvd: Nguyen Van;Abc;1/1/1999;nam");
						System.out.println("nhập:");
						line = "SV" + String.format("%05d", idSearch) + ";" + sc.nextLine();
						Student stNew = null;
						try {
							stNew = Student.createSt(line);
							listSt.add(stNew);
						} catch (Exception e) {
							System.out.println("bạn nhập sai định dạng chuỗi thông tin");
						}
						for (Student student : listSt) {
							bfw.write(student.toString());
							bfw.newLine();
						}
						bfr.close();
						bfw.close();
					} else {
						System.out.println("Không tồn tại sinh viên vừa nhập");
					}
				} catch (Exception e) {
//					System.out.println("sau 'SV' phải gồm chuỗi số");
					e.printStackTrace();
				}
			}
		} catch (IOException | NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(bfw != null)
				try {
					bfw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	private static void updateListStudent(String str) {
		try {
			ArrayList<Student> listSt = new ArrayList<>();
			in = new FileReader(str + "\\sinhvien_en.txt");
			bfr = new BufferedReader(in);
			String line;
			while ((line = bfr.readLine()) != null) {
				if (line.startsWith("#"))
					continue;
				listSt.add(Student.createSt(line));
			}
			bfr.close();
			int maxId = 0;
			for (Student student : listSt) {
				maxId = Math.max(maxId, student.getID());
			}
			out = new FileWriter(str + "\\sinhvien_en.txt");
			bfw = new BufferedWriter(out);
			System.out.println(s + "\nNhập thông tin sinh viên mới có định dạng như sau:");
			System.out.println("[Họ dệm];[Tên];[Ngày sinh];[Giới tính]" + "\nvd: Nguyen Van;Abc;1/1/1999;nam");
			System.out.println("nhập:");
			line = "SV" + String.format("%05d", maxId + 1) + ";" + sc.nextLine();
			Student stNew = null;
			try {
				stNew = Student.createSt(line);
				listSt.add(stNew);
				System.out.println("đã thêm thành công");
			} catch (Exception e) {
				System.out.println("bạn nhập sai định dạng chuỗi thông tin");
			}
			for (Student student : listSt) {
				bfw.write(student.toString());
				bfw.newLine();
			}
		} catch (IOException | NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bfw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static FileReader in = null;
	private static BufferedReader bfr = null;

	public static void showListStudent(String str) throws NumberFormatException, ParseException, IOException {

		try {
			ArrayList<Student> listSt = new ArrayList<>();
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
			if (listSt.size() < 20) {
				page = 1;
			} else
				page = (listSt.size() % 20 == 0) ? listSt.size() / 20 : (listSt.size() / 20 + 1);
			int pageShow = 0;
			int choose;
			do {
				printPage(pageShow, page, listSt);
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
								printPage(0, page, listSt);
								break;
							}
							if (pageShow == page - 1) {
								System.out.println("bạn đang ở trang cuối rồi");
								printPage(pageShow, page, listSt);
								break;
							}

							printPage(++pageShow, page, listSt);
							break;
						case 2:
							if (pageShow == 0) {
								System.out.println("bạn đang ở đầu trang rồi");
								printPage(pageShow, page, listSt);
								break;
							}
							printPage(--pageShow, page, listSt);
							break;
						case 3:
							pageShow = page;
							printPage(pageShow - 1, page, listSt);
							break;
						case 4:
							pageShow = 0;
							printPage(pageShow, page, listSt);
							break;
						case 5:
							System.out.println("\nChọn trang:");
							String stringPage = sc.nextLine();
							int tmp = Integer.parseInt(stringPage);
							if (tmp > page) {
								System.out.println("không có trang bạn vừa chọn");
								printPage(pageShow, page, listSt);
								break;
							}
							pageShow = tmp;
							printPage(--pageShow, page, listSt);
							break;
						case 6:
							System.out.println("Nhập mã sinh viên:");
							String stringId = sc.nextLine();
							if (stringId.startsWith("SV") == false) {
								System.out.println("vui lòng nhập chuẩn mã sv dạng 'SV...' vd: SV00001");
								printPage(pageShow, page, listSt);
								break;
							}
							int idSearch = 0;
							try {
								idSearch = Integer.parseInt(stringId.substring(3));
							} catch (Exception e) {
								System.out.println("sau 'SV' phải gồm chuỗi số");
								printPage(pageShow, page, listSt);
								break;
							}
							showMarkById(idSearch, listSt, str);
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

	public static void showMarkById(int idSearch, ArrayList<Student> listSt, String str) {
		try {
			in = new FileReader(str + "\\diem.txt");
			bfr = new BufferedReader(in);
			int check = 0;
			String namStSea = null;
			String datStSea = null;
			for (Student student : listSt) {
				if (student.getID() == idSearch) {
					check = 1;
					namStSea = student.getFirstName() + " " + student.getLastName();
					datStSea = student.getStringDate().toString();
				}
			}
			if (check == 0) {
				System.out.println("không tồn tại sinh viên có mã vừa nhập");
			} else {
				ArrayList<Mark> listMk = new ArrayList<Mark>();
				String line;
				try {
					ArrayList<Integer> idSb = new ArrayList<Integer>();
					ArrayList<Float> markSeac = new ArrayList<Float>();
					ArrayList<String> nameSb = new ArrayList<String>();
					while ((line = bfr.readLine()) != null) {
						if (line.startsWith("#"))
							continue;
						listMk.add(Mark.createSt(line));
					}
					for (Mark mark : listMk) {
						if (mark.getIDStudent() == idSearch) {
							idSb.add(mark.getIDSubject());
							markSeac.add(mark.getMark());
						}
					}
					bfr.close();

					in = new FileReader(str + "\\monhoc_en.txt");
					bfr = new BufferedReader(in);

					ArrayList<Subject> listSb = new ArrayList<Subject>();
					while ((line = bfr.readLine()) != null) {
						if (line.startsWith("#"))
							continue;
						listSb.add(Subject.createSt(line));
					}
					for (Subject subject : listSb) {
						for (int i = 0; i < idSb.size(); i++) {
							if (subject.getID() == idSb.get(i)) {
								nameSb.add(subject.getName());
							}
						}
					}
					float averageMark = 0;
					for (int i = 0; i < idSb.size(); i++) {
						averageMark += markSeac.get(i);
					}

					System.out.println("\n"+s);
					System.out.format("| SV%05d | %-31s | %-10s |\n", idSearch, namStSea, datStSea);
					System.out.println(s);
					if (averageMark != 0) {
						averageMark = averageMark / idSb.size();
						System.out.format("| DTK %45s %.2f |", " ", averageMark);
						System.out.println("\n"+s);
						for (int i = 0; i < idSb.size(); i++) {
							System.out.format("| %03d  %43s  %.2f |\n", idSb.get(i), nameSb.get(i), markSeac.get(i));
						}
					} else {
						System.out.format("| DTK %45s %.2f |", " ", averageMark);
						System.out.println("\n"+s);
						System.out.format("| %54s |\n", "Chưa có điểm");
					}
					System.out.println("\n"+s);
				} catch (NumberFormatException | IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bfr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void printPage(int i, int j, ArrayList<Student> list) {
		System.out.println("\n---------------------DANH SÁCH SINH VIÊN-----------------------------");
		System.out.println("-----------------------Trang " + (i + 1) + "/" + j + "-----------------------------------");
		System.out.println("--------------------------------------------------------------------");
		System.out.format("| %-7s | %-21s | %-10s | %-10s | %-4s |\n", "Mã SV", "Họ đệm", "Tên", "Năm sinh", "Giới");
		System.out.println("--------------------------------------------------------------------");
		for (int z = (20 * i); z < 20 * (i + 1); z++) {
			if (z >= list.size()) {
				break;
			}
			list.get(z).print();
		}
		System.out.println("--------------------------------------------------------------------");
		System.out.println("-----------------------Trang " + (i + 1) + "/" + j + "-----------------------------------");

	}

	private static void menuPrintUpdate() {
		System.out.println("\n" + s1);
		System.out.println("|                 Cập nhật danh sách              |");
		System.out.println(s2);
		System.out.format("| %-2d. %-44s|", 1, "Thêm sinh viên");
		System.out.format("\n| %-2d. %-44s|", 2, "Sửa Thông tin sv");
		System.out.format("\n| %-2d. %-44s|", 3, "Xóa sinh viên");
		System.out.format("\n| %-2d. %-44s|", 4, "Hiển thị danh sách sv");
		System.out.format("\n| %-2d. %-44s|", 0, "Quay lại");
		System.out.println("\n" + s3);
	}
	
}
