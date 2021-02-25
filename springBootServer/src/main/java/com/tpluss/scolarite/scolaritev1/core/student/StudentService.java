package com.tpluss.scolarite.scolaritev1.core.student;


import com.tpluss.scolarite.scolaritev1.core.classe.ClasseEntity;
import com.tpluss.scolarite.scolaritev1.core.classe.ClasseRepo;
import com.tpluss.scolarite.scolaritev1.core.controller.UploadService;
import com.tpluss.scolarite.scolaritev1.core.emploi.EmploiRepo;
import com.tpluss.scolarite.scolaritev1.core.faceid.faceidentity.FaceId;
import com.tpluss.scolarite.scolaritev1.core.faceid.faceidentity.FaceIdRepo;
import com.tpluss.scolarite.scolaritev1.core.student.models.GetStudentByID;
import com.tpluss.scolarite.scolaritev1.core.student.models.PostNewStudentRequest;
import com.tpluss.scolarite.scolaritev1.core.student.models.ResponsePostStudent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.event.HyperlinkEvent;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {


    @Autowired
    StudentRepo studentRepo ;

    @Autowired
    FaceIdRepo faceIdRepo ;

    @Autowired
    ClasseRepo classeRepo;

    @Autowired
    EmploiRepo emploiRepo ;

    @Autowired
    UploadService uploadService ;




    public ResponseEntity<?> getSudentById(Long idStudent) {

    Optional<StudentEntity> studentEntityOptionaly = studentRepo.findById(idStudent) ;

    if (  !studentEntityOptionaly.isPresent() ) return  null ;

        StudentEntity studentEntity = studentEntityOptionaly.get() ;
        GetStudentByID getStudentByID = new GetStudentByID(  )  ;

        BeanUtils.copyProperties(studentEntity,getStudentByID);


        return  new ResponseEntity< >(getStudentByID , HttpStatus.OK);

    }

    public ResponseEntity<?> postNewStudent(PostNewStudentRequest newStudentRequest) throws IOException {



      StudentEntity newStudent = new StudentEntity() ;
      FaceId faceId=new FaceId();

      BeanUtils.copyProperties(newStudentRequest,faceId);


      BeanUtils.copyProperties(newStudentRequest , newStudent);


        faceId.setStudentId(newStudent);
         studentRepo.save(newStudent);
        faceIdRepo.save(faceId);

        ResponsePostStudent idstudent = new ResponsePostStudent() ;

        idstudent.setIdstudent(newStudent.getId());

        System.out.println(newStudent.getId());



      return new ResponseEntity(idstudent  , HttpStatus.OK) ;
    }

    public  List<StudentEntity>gettAllStudents() {
        return  studentRepo.findAll() ;
    }

    public ResponseEntity<?> deleteAllData() {

        emploiRepo.deleteAll();



        faceIdRepo.deleteAll();
        studentRepo.deleteAll();

        classeRepo.deleteAll();


        return  new ResponseEntity<>(null , HttpStatus.OK) ;
    }
}
