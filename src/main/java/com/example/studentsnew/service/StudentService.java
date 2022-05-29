package com.example.studentsnew.service;



import com.example.studentsnew.model.Student;
import com.example.studentsnew.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudents(int id){
        studentRepository.deleteById(id);
    }

    public void updateStudent(Map<String, String> requestParams){
        int valueID = Integer.parseInt(requestParams.get("id"));
        String secondName = requestParams.get("secondName");
        String firstName = requestParams.get("firstName");
        String patronymic = requestParams.get("patronymic");
        String groupName = requestParams.get("groupName");
        Date date;
        try {
            date =new SimpleDateFormat("yyyy-MM-dd").parse(requestParams.get("birthDate"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Optional<Student> student = studentRepository.findById(valueID);
        if(student.isPresent()){
            Student studentInTable = student.get();
            if(secondName!=null && !secondName.equals(studentInTable.getSecondName())){
                studentInTable.setSecondName(secondName);
            }
            if(firstName!=null && !firstName.equals(studentInTable.getFirstName())){
                studentInTable.setFirstName(firstName);
            }
            if(patronymic!=null && !patronymic.equals(studentInTable.getPatronymic())){
                studentInTable.setPatronymic(patronymic);
            }
            if(groupName!=null && !groupName.equals(studentInTable.getGroupName())){
                studentInTable.setGroupName(groupName);
            }
            if(date!=null && !date.equals(studentInTable.getBirthDate())){
                studentInTable.setBirthDate(date);
            }
            studentRepository.save(studentInTable);
        }


    }


}