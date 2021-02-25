package com.tpluss.scolarite.scolaritev1.core.emploi;

import com.tpluss.scolarite.scolaritev1.core.classe.ClasseEntity;
import com.tpluss.scolarite.scolaritev1.core.classe.ClasseRepo;
import com.tpluss.scolarite.scolaritev1.core.emploi.models.GetEmploiModel;
import com.tpluss.scolarite.scolaritev1.core.emploi.models.PostEmploisModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/emploi")
public class EmploiController {

    @Autowired
    EmploiRepo emploiRepo;

    @Autowired
    ClasseRepo classeRepo;




    @GetMapping("/all")
    ResponseEntity getAllemplois ( ){

        ArrayList <EmploiEntity > emploiEntityList  = (ArrayList<EmploiEntity>) emploiRepo.findAll();

        ArrayList <GetEmploiModel> getEmploiModels = new ArrayList<>( ) ;

        for ( int i  =0 ; i<emploiEntityList.size() ; i++ ){
            GetEmploiModel getEmploiModel = new GetEmploiModel() ;
            BeanUtils.copyProperties(emploiEntityList.get(i) , getEmploiModel);
            ClasseEntity classeEntity = emploiEntityList.get(i).classeIdEmp ;

            Optional<ClasseEntity> ceop=  classeRepo.findById(classeEntity.getId()) ;
            if ( !ceop.isPresent())
                continue;
            BeanUtils.copyProperties(ceop.get(),getEmploiModel);
            getEmploiModels.add(getEmploiModel) ;
        }

        return  new ResponseEntity(getEmploiModels , HttpStatus.OK) ;
    }


    @PostMapping
    ResponseEntity postTousEmplois(@RequestBody PostEmploisModel emplois){


        emplois.getEmploiRequestModels().forEach(emploi-> {
            System.out.println(emploi.toString());
            Optional<ClasseEntity> ce=classeRepo.findById(emploi.getClasse());
            if ( !ce.isPresent())
                return;

            ClasseEntity classeEntity=ce.get();
            EmploiEntity emploiEntity=new EmploiEntity();
            BeanUtils.copyProperties(emploi,emploiEntity);
            emploiEntity.setClasseIdEmp(classeEntity);
            classeEntity.getEmplois().add(emploiEntity);

            emploiRepo.save(emploiEntity);
            classeRepo.save(classeEntity) ;


        });







        return new ResponseEntity(emplois, HttpStatus.OK);
    }
}
