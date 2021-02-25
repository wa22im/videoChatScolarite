package com.tpluss.scolarite.scolaritev1.core.faceid.faceidcontroller;


import com.tpluss.scolarite.scolaritev1.core.faceid.faceprint.FacePrintService;
import com.tpluss.scolarite.scolaritev1.core.faceid.models.RequestPostFaceIdArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faceid")
public class FaceIdController {

    @Autowired
    FacePrintService facePrintService ;

    @PostMapping
    ResponseEntity<?> postFaceIdArray (@RequestBody RequestPostFaceIdArray requestPostFaceIdArray) {
        return  facePrintService.postFaceIdArray(requestPostFaceIdArray) ;
    }




    @GetMapping("/{id}")
    ResponseEntity<?> postFaceIdArray (@PathVariable  String id) {
        return  facePrintService.getFacePrintById(id) ;
    }

}

