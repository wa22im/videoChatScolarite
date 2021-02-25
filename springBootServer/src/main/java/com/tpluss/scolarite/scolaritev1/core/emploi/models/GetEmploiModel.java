package com.tpluss.scolarite.scolaritev1.core.emploi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@NoArgsConstructor
@Setter

public class GetEmploiModel {

    int jourSemaine;

    String matinEntree;
    String matinSortie;
    String soirEntree;
    String soirSortie;
    String codeClasse ;
    String anneeScolaire;


}
