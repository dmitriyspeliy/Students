package com.example.studentsnew.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table( name = "students", schema = "public")
public class Student {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Integer id;

    @Column(name = "second_name", nullable = false, length = 32)
    private String secondName;

    @Column(name = "first_name", nullable = false, length = 32)
    private String firstName;

    @Column(name = "patronymic", nullable = false, length = 32)
    private String patronymic;

    @Column(name = "birth_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Column(name = "group_name", nullable = false, length = 32)
    private String groupName;

}