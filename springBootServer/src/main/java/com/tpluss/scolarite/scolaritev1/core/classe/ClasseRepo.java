package com.tpluss.scolarite.scolaritev1.core.classe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClasseRepo extends JpaRepository<ClasseEntity,Long> {

    @Override
    Optional<ClasseEntity> findById(Long aLong);
}
