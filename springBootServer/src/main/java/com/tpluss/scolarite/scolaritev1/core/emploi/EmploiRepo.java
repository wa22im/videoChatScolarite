package com.tpluss.scolarite.scolaritev1.core.emploi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmploiRepo extends JpaRepository<EmploiEntity,Long> {
}
