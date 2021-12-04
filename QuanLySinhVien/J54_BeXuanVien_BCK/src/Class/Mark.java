package Class;

import java.text.ParseException;
import java.util.Date;

public class Mark {
	private Integer IDStudent;
	private Integer IDSubject;
	private Float mark;

	public Mark() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mark(Integer iDStudent, Integer iDSubject, Float mark) {
		super();
		IDStudent = iDStudent;
		IDSubject = iDSubject;
		this.mark = mark;
	}

	public Integer getIDStudent() {
		return IDStudent;
	}

	public void setIDStudent(Integer iDStudent) {
		IDStudent = iDStudent;
	}

	public Integer getIDSubject() {
		return IDSubject;
	}

	public void setIDSubject(Integer iDSubject) {
		IDSubject = iDSubject;
	}

	public Float getMark() {
		return mark;
	}

	public void setMark(Float mark) {
		this.mark = mark;
	}

	public static Mark createSt(String data) throws ParseException, NumberFormatException {

		String parts[] = data.split(";");
		int idIntSt = Integer.valueOf(parts[0].substring(3));
		int idIntSb = Integer.valueOf(parts[1]);
		float mark = Float.valueOf(parts[2]);
		Mark sv = new Mark(idIntSt, idIntSb, mark);
		return sv;

	}
	
}
