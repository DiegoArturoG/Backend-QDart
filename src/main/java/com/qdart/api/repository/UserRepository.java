package com.qdart.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.qdart.api.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	//JPQL... Java Persistence Query Languaje
	@Query("SELECT u FROM User u WHERE u.email=?1") //Consultas integradas de JPA, tipo String,  Seleccionar todo de la entidad
	User findByEmail (String email); //findByEmail : Método de consulta
	
}
