package com.mss;

import java.util.List;

public interface StudentDAO {
	public int saveStudent(Student s);
	public int updateStudent(Student s);
public int deleteStudent(Student s);
	public List<Student> getStudent();
}
