package com.developer.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.agenda.models.Contacto;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {
	

}
