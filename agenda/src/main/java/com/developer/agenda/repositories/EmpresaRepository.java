package com.developer.agenda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.agenda.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
	List<Empresa> findByNombre(String nombre);

}
