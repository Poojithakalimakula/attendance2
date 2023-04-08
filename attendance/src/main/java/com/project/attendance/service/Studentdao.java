package com.project.attendance.service;

import java.io.IOException;
import java.util.List;

import com.project.attendance.model.Student;
import com.google.zxing.WriterException;

public interface Studentdao {
	public  List<Student> getAllStudents();
	public void  saveStudent(Student student) throws WriterException, IOException;

}
