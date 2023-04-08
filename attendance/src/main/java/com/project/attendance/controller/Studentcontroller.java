package com.project.attendance.controller;



import java.io.IOException;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.project.attendance.model.Student;
import com.project.attendance.service.Studentservice;

import com.google.zxing.WriterException;



@Controller
public class Studentcontroller {
	@Autowired
	private Studentservice studentservice;
	@GetMapping("/details")
	public String viewStudentDetails(Model model) {
		model.addAttribute("liststudents", studentservice.getAllStudents());
		return "studentdetails";
	}
	@GetMapping("/newStudenForm")
	public String showStudentForm(Model model) {
		Student student=new  Student();
		model.addAttribute("student", student);
		return "form";
	}
	@GetMapping("/")
	public String showAllOptions() {
		return "Home";
	}
//	@PostMapping("/saveStudent")
//	public String saveStudent(@ModelAttribute("student")Student student) {
//		studentservice.saveStudent(student);
//		return "redirect:/";
//	}
	@PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student, Model model) throws WriterException, IOException {
        studentservice.saveStudent(student);
        model.addAttribute("qrcode", student.getQrCodeImage());
        return "redirect:/";
    }
	@GetMapping("/qrcode/{sadmin}")
	public ResponseEntity<byte[]> getQRCodeImageByAdmissionNumber(@PathVariable("sadmin") String sadmin) throws Exception {
	    byte[] imageBytes = studentservice.getQRCodeImageByAdmissionNumber(sadmin);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);
	    headers.setContentLength(imageBytes.length);
	    return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
	}

	@GetMapping("/student/{id}/")
	public String viewStudent( @PathVariable String id, Model model) {
		model.addAttribute("data", studentservice.getStudentDataByAdmissionNumber(id));
		return "student";
	}
	@GetMapping("/markAttendance/")
	public String markAttendance(Model model) {
	return "attendance";
}


}