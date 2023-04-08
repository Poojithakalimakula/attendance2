package com.project.attendance.repository;

import com.project.attendance.model.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findBySadmin(String admissionNumber);

    Student findByQrCodeImage(String qrCodeImage);



    List<Student> findAllByType(final Type sadmin);
}
