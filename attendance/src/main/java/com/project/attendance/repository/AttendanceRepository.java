package com.project.attendance.repository;

import com.project.attendance.model.Attendance;
import com.project.attendance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {

    List<Attendance> findByStudentAndTimeBetween(Student student, Date startDate, Date endDate);

    List<Attendance> findByTime(Date date);
}
