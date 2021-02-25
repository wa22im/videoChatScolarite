package com.tpluss.scolarite.scolaritev1.core.home.predictcontroller;


import com.tpluss.scolarite.scolaritev1.communmodel.predictdata.Getpredictdata;
import com.tpluss.scolarite.scolaritev1.communmodel.predictdata.SignInResponse;
import com.tpluss.scolarite.scolaritev1.core.classe.ClasseEntity;
import com.tpluss.scolarite.scolaritev1.core.controller.UploadService;
import com.tpluss.scolarite.scolaritev1.core.emploi.EmploiEntity;
import com.tpluss.scolarite.scolaritev1.core.faceid.faceidentity.FaceId;
import com.tpluss.scolarite.scolaritev1.core.faceid.faceidentity.FaceIdRepo;
import com.tpluss.scolarite.scolaritev1.core.home.predictcontroller.models.ResultFaceDest;
import com.tpluss.scolarite.scolaritev1.core.student.StudentEntity;
import com.tpluss.scolarite.scolaritev1.core.student.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.MalformedURLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
public class PredictService {

    @Autowired
    FaceIdRepo faceIdRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    UploadService uploadService ;

    ResponseEntity<?> predictedUser(@RequestBody Getpredictdata getpredictdata) throws MalformedURLException {


        List<Object[]> faceIds = faceIdRepo.findFacePrint(getpredictdata.predictTable);
        if ( faceIds.isEmpty()|| faceIds.size()==0)
            return  new ResponseEntity<>(null  , HttpStatus.OK) ;


        Long faceId=Long.valueOf(faceIds.get(0)[0].toString());

        Long studentId=Long.valueOf(faceIds.get(0)[2].toString());

        Optional<StudentEntity> se=studentRepo.findById(studentId);
        if(!se.isPresent())
            return null;
        StudentEntity studentEntity=se.get();
       // System.out.println(emploiEntity1.toString());
            SignInResponse response=new SignInResponse(
                    studentEntity.getNom(),
                    studentEntity.getPrenom(),
                    studentEntity.getPicsName()
                    );
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    private int getCurrentDay(String currentDay) {
        int day ;
        switch (currentDay.toUpperCase()) {
            case "MONDAY":
                day= 1 ;
                break;
            case "TUESDAY":
                day =2 ;
            break;
            case "WEDNESDAY":
                day = 3 ;
            break;
            case "THURSDAY":
                day = 4 ;
            break;
            case "FRIDAY":
                day= 5 ;
            break;
            case "SATURDAY":
                day= 6 ;
            break;
            default:
                return 0 ;
        }
        return  day ;
    }

    double _euclideanDistance(List<Double> e1, List<Double> e2) {

        double sum = 0.0;

        for (int i = 0; i < e1.size(); i++) {
            sum += Math.pow((e1.get(i) - e2.get(i)), 2);
        }
        return Math.sqrt(sum);
    }


}
