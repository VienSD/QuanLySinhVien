package Class;

import java.text.ParseException;

public class Subject {
	private Integer ID;
	private String name;
	private Float subjectCoe;
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Subject(Integer iD, String name, Float subjectCoe) {
		super();
		ID = iD;
		this.name = name;
		this.subjectCoe = subjectCoe;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getSubjectCoe() {
		return subjectCoe;
	}
	public void setSubjectCoe(Float subjectCoe) {
		this.subjectCoe = subjectCoe;
	}
	public static Subject createSt(String data) throws ParseException, NumberFormatException {

		String parts[] = data.split(";");
		int idIntSb = Integer.valueOf(parts[0]);
		String name = parts[1];
		float sbCoe = Float.valueOf(parts[0]);
		Subject sv = new Subject(idIntSb, name, sbCoe);
		return sv;

	}
	
}
