package jaxb;

import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Student {	
	private String college;
	private String name;
	private String rollNo;
	private String className;
	private int age;
	private int id;
	private List<Subject> subject;
 
	//Default constructor
	public Student(){	}
 
	//Parameterised constructor
	public Student(String name, String rollNo, String className, int age, int id, List<Subject> list){
		this.name = name;
		this.rollNo = rollNo;
		this.className = className;
		this.age = age;
		this.id = id;
		this.subject = list;		
	}	
 
//	@XmlElement
	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getName() {
		return name;
	}
 
//	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
 
	public String getClassName() {
		return className;
	}
 
//	@XmlElement
	public void setClassName(String className) {
		this.className = className;
	}
 
	public String getRollNo() {
		return rollNo;
	}
 
//	@XmlElement
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
 
	public int getAge() {
		return age;
	}
 
	@XmlTransient
	public void setAge(int age) {
		this.age = age;
	}
 
	public int getId() {
		return id;
	}
 
//	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
 
	public List<Subject> getSubject() {
		return subject;
	}
 
//	@XmlElement
	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", rollNo=" + rollNo + ", className=" + className + ", age=" + age + ", id=" + id
				+ ", subject=" + subject + "]";
	}
	
	
 
}
