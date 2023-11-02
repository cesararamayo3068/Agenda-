package com.developer.agenda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.agenda.models.Contacto;
import com.developer.agenda.models.Empresa;
import com.developer.agenda.repositories.EmpresaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
	
	  @Autowired
	    private EmpresaRepository empresaRepository;

	    public List<Empresa> getAllEmpresas() {
	        return empresaRepository.findAll();
	    }

	    public Empresa getEmpresaById(Long id) {
	        return empresaRepository.findById(id).orElse(null);
	    }

	    public Empresa addEmpresa(Empresa empresa) {
	        // Puedes realizar validaciones antes de guardar la empresa
	        return empresaRepository.save(empresa);
	    }

	    public Empresa updateEmpresa(Long id, Empresa empresa) {
	        // Puedes implementar la actualización según tus necesidades
	        return empresaRepository.save(empresa);
	    }

	    public void deleteEmpresa(Long id) {
	        empresaRepository.deleteById(id);
	    }
	    
	    public Optional<Empresa> findById(Long id) {
			return empresaRepository.findById(id);
		}

}
