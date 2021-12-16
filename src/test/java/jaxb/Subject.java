package jaxb;

public class Subject {
	private String subjectname;
	private String subjectId;
 
 
	//Default constructor
	public Subject(){
 
	}
 
	//Parameterised constructor
	public Subject(String subjectname, String subjectId){
		this.subjectname = subjectname;
		this.subjectId = subjectId;
	}
 
	public String getSubjectname() {
		return subjectname;
	}
 
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
 
	public String getSubjectId() {
		return subjectId;
	}
 
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
 
}

