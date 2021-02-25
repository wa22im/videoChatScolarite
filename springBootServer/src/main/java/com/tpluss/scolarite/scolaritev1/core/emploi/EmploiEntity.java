package com.tpluss.scolarite.scolaritev1.core.emploi;

import com.tpluss.scolarite.scolaritev1.core.classe.ClasseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "emploi")
public class EmploiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    int jourSemaine;

    String matinEntree;
    String matinSortie;
    String soirEntree;
    String soirSortie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="classeIdEmp")
    ClasseEntity classeIdEmp;


}
