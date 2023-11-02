package com.developer.agenda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.agenda.models.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
	
	List<Persona> findByNombre(String nombre);

    List<Persona> findByCiudad(String ciudad);


}
