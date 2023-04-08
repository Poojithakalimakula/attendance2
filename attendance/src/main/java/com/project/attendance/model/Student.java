package com.project.attendance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Blob;

@Entity
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="student1")
@DynamicInsert
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  int sid;

    private String sadmin;

    private String sname;

    private String sbranch;

    private int syear;

    private String sgmail;
    private String sph;

    @Lob
    private byte[] qrCodeImage;
}