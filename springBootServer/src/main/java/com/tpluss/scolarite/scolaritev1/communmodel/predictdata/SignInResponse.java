package com.tpluss.scolarite.scolaritev1.communmodel.predictdata;

import com.tpluss.scolarite.scolaritev1.core.emploi.EmploiEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;


import  java.util.List ;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {
    public String nom;
    public String prenom;

    String picsName ;


}
