  package com.project.attendance.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.attendance.model.Attendance;
import com.project.attendance.model.Student;
import com.project.attendance.repository.AttendanceRepository;
import com.project.attendance.repository.StudentRepository;

import java.util.List;

// Service class
@Service
public class AttendanceService {


	    @Autowired
	    private StudentRepository studentRepository;

	    @Autowired
	    private AttendanceRepository attendanceRepository;


	    public void takeAttendance(String qrCodeContent) throws Exception {

	        Student student = studentRepository.findByQrCodeImage(qrCodeContent);
	        if (student == null) {
	            throw new Exception("Student not found.");
	        }

	        Attendance attendance = new Attendance();
	        attendance.setStudent(student);
	        attendance.setTime(new Date());
	        attendanceRepository.save(attendance);
	    }
	

        


		public List<Attendance> getAllAttendance() {
		    List<Attendance> attendanceList = (List<Attendance>) attendanceRepository.findAll();
		    return attendanceList;
		}

}

