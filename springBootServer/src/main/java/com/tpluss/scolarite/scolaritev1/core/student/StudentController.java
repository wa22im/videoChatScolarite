package com.tpluss.scolarite.scolaritev1.core.student;


import com.tpluss.scolarite.scolaritev1.core.student.models.PostNewStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/student")
public class StudentController {


    @Autowired
    StudentService studentService ;


    @GetMapping("/all")
    ResponseEntity<?> getAllstudents () {
        return  new ResponseEntity<>(studentService.gettAllStudents() , HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    ResponseEntity <?>  getSudentById (@RequestParam  Long idStudent ){
        return  studentService.getSudentById(idStudent) ;
    }

    @PostMapping
    ResponseEntity<?> postNewStudent(@RequestBody PostNewStudentRequest newStudentRequest ) throws IOException {

      return   studentService.postNewStudent(newStudentRequest) ;
    }

    @DeleteMapping
    ResponseEntity<?> deleteAllStudents (){
        return  studentService.deleteAllData();
    }
}
