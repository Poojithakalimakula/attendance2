package com.project.attendance.service;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Base64;

import com.project.attendance.model.Student;
import com.project.attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.attendance.qrcode.QRCodeGenerator;
import com.project.attendance.model.Student;
import com.project.attendance.repository.StudentRepository;
import com.google.zxing.WriterException;

@Service
public class Studentservice implements  Studentdao 
{
	@Autowired
	private StudentRepository repo;

	@Override
	public List<Student> getAllStudents() {
		return (List<Student>) repo.findAll();
	}


    public void saveStudent(Student student) throws WriterException, IOException {
        // Generate QR code
        QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
        @SuppressWarnings("static-access")
		byte[] qrCodeImage = qrCodeGenerator.generateQRCode(student.getSadmin(), 200, 200);
        student.setQrCodeImage(qrCodeImage);

        // Save student to database
        repo.save(student);
    }
   

    public byte[] getQRCodeImageByAdmissionNumber(String sadmin) throws IOException, WriterException {
        Student student = repo.findBySadmin(sadmin);
        byte[] qrCodeImage = student.getQrCodeImage();
        String encodedImage = Base64.getEncoder().encodeToString(qrCodeImage);
        return Base64.getDecoder().decode(encodedImage);
    }

    public Student findByQrCode(String qrCode) {
        return repo.findByQrCodeImage(qrCode);
    }

    public Student getStudentDataByAdmissionNumber(String ano) {
        return repo.findBySadmin(ano);
    }


    public void markAttendance(byte[] admissionNumberBytes) {
    }
}
		
	
	   
	


