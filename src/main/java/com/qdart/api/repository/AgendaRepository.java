package com.qdart.api.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qdart.api.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    @Query("SELECT u FROM Agenda u WHERE u.nombreCliente=?1")
    Agenda findByNombreCliente(String nombreCliente);
}

//JPQL... Java Persistence Query Language