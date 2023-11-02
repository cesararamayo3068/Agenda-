package com.developer.agenda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.developer.agenda.exceptions.CustomNotFoundException;
import com.developer.agenda.models.Contacto;
import com.developer.agenda.models.Empresa;
import com.developer.agenda.services.EmpresaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	@GetMapping
	public ResponseEntity<List<Empresa>> getAllEmpresas() {
		List<Empresa> empresas = empresaService.getAllEmpresas();
		return new ResponseEntity<>(empresas, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getEmpresaById(@PathVariable Long id) {
		Empresa empresa = empresaService.getEmpresaById(id);

		if (empresa == null) {
			throw new CustomNotFoundException("Empresa no encontrada con ID: " + id);
		}

		return new ResponseEntity<>(empresa, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> addEmpresa(@RequestBody Empresa empresa) {
		Empresa nuevaEmpresa = empresaService.addEmpresa(empresa);
		String mensaje = "Empresa con ID " + nuevaEmpresa.getId() + " agregada con éxito.";
		return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
		if (id == null) {
			return new ResponseEntity<>("El ID no puede ser nulo.", HttpStatus.BAD_REQUEST);
		}

		Optional<Empresa> empresaExistente = empresaService.findById(id);

		if (empresaExistente.isPresent()) {
			Empresa empresaActualizada = empresaExistente.get();

			// Actualizar campos específicos
			empresaActualizada.setNombre(empresa.getNombre());

			if (empresa.getContactos() != null) {
				empresaActualizada.getContactos().clear(); 

				for (Contacto contacto : empresa.getContactos()) {
					contacto.setEmpresa(empresaActualizada);
					empresaActualizada.getContactos().add(contacto);
				}
			}

			empresaService.addEmpresa(empresaActualizada);

			String mensaje = "Empresa con ID " + id + " actualizada con éxito.";
			return new ResponseEntity<>(mensaje, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se encontró ninguna empresa con el ID proporcionado.",
					HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteEmpresa(@PathVariable Long id) {
		empresaService.deleteEmpresa(id);
		String mensaje = "Empresa con ID " + id + " eliminada con éxito.";
		return new ResponseEntity<>(mensaje, HttpStatus.NO_CONTENT);
	}

}
