package com.project.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.attendance.model.Attendance;
import com.project.attendance.service.AttendanceService;




@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/attendance")
    public ResponseEntity<String> takeAttendance(@RequestParam("qrCodeContent") String qrCodeContent) {
        try {
            attendanceService.takeAttendance(qrCodeContent);
            return ResponseEntity.ok("Attendance successfully taken.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/attendance1")
   public ResponseEntity<List<Attendance>> getAttendance() {
       List<Attendance> attendanceList = attendanceService.getAllAttendance();
       return ResponseEntity.ok(attendanceList);
   }
}






