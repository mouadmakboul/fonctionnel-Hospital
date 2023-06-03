package com.example.hosptal.repository;

import com.example.hosptal.entities.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Page<ClientEntity> findByNomContains(String keyword, Pageable pageable);

    @Query("select c from ClientEntity c where c.nom like :x")
    Page<ClientEntity> chercher(@Param("x") String keyword, Pageable pageable);
}
