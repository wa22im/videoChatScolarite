package com.tpluss.scolarite.scolaritev1.core.faceid.faceprint;


import com.tpluss.scolarite.scolaritev1.core.faceid.faceidentity.FaceId;
import com.tpluss.scolarite.scolaritev1.core.faceid.faceidentity.FaceIdRepo;
import com.tpluss.scolarite.scolaritev1.core.faceid.models.RequestPostFaceIdArray;
import com.tpluss.scolarite.scolaritev1.core.faceid.models.ResponseFaceid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FacePrintService {

    @Autowired
    FaceIdRepo faceIdRepo;

    public ResponseEntity<?> postFaceIdArray(RequestPostFaceIdArray requestPostFaceIdArray) {
      //  String faceUuid = UUID.randomUUID().toString();
/*
        FaceId faceId = new FaceId();

        BeanUtils.copyProperties(requestPostFaceIdArray, faceId);
        // faceId.setFacePrint(requestPostFaceIdArray.getFacePrint());

        faceIdRepo.save(faceId);

        ResponseFaceid responseFaceid = new ResponseFaceid(faceUuid);

        return new ResponseEntity<>(responseFaceid, HttpStatus.OK);*/
        return null;



    }


    public ResponseEntity<?> getFacePrintById(String faceUid) {
        /*
    Optional<FaceId> faceId =    faceIdRepo.findByFaceid(faceUid) ;
      return  new ResponseEntity<>(faceId.get() , HttpStatus.OK) ;
*/
        return null;
    }

}
