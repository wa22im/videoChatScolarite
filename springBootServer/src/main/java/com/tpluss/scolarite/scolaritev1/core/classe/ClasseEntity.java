package com.tpluss.scolarite.scolaritev1.core.classe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpluss.scolarite.scolaritev1.core.emploi.EmploiEntity;
import com.tpluss.scolarite.scolaritev1.core.student.StudentEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


@Entity
@Table(name = "classe")
public class ClasseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String codeClasse;

    String anneeScolaire;


    @OneToMany(fetch = FetchType.LAZY   , mappedBy = "classeIdEmp")
    @JsonIgnore
    private List  <EmploiEntity> emplois;
}
