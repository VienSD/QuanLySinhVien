package Class;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
	private int ID;
	private String firstName;
	private String lastName;
	private Date dateOB;
	private String gender;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int iD, String firstName, String lastName, Date dateOB, String gender) {
		super();
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOB = dateOB;
		this.gender = gender;
	}
	public int getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOB() {
		return dateOB;
	}
	public void setDateOB(Date dateOB) {
		this.dateOB = dateOB;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public String getStringDate() {
		return sdf.format(dateOB);
	}
	public static Student createSt(String data) throws ParseException, NumberFormatException {
		
		String parts[] = data.split(";");
		String id = parts[0].substring(3);
		String fname = parts[1];
		String lname = parts[2];
		String datob = parts[3];
		String gender = parts[4];
		int idInt = Integer.valueOf(id);
		Date nsDate = sdf.parse(datob);
		Student sv = new Student(idInt, fname, lname, nsDate, gender);
		return sv;
		
	}
	public void print() {
		System.out.format("| SV%05d | %-21s | %-10s | %-7s | %-4s |\n", 
				ID, firstName, lastName, getStringDate(), gender);
	}
	@Override
	public String toString() {
		return "SV" + String.format("%05d", ID) + ";" + firstName + ";" + lastName + ";" + getStringDate() + ";" +  gender;
	}
	
	
}
