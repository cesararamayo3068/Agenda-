package com.developer.agenda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.developer.agenda.exceptions.CustomNotFoundException;
import com.developer.agenda.models.Persona;
import com.developer.agenda.repositories.PersonaRepository;
import com.developer.agenda.services.PersonaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private PersonaRepository personaRepository;

	@GetMapping
	public ResponseEntity<List<Persona>> getAllPersonas() {
		List<Persona> personas = personaService.getAllPersonas();
		return new ResponseEntity<>(personas, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
		Persona persona = personaService.getPersonaById(id);

		if (persona == null) {
			throw new CustomNotFoundException("Persona no encontrada con ID: " + id);
		}

		return new ResponseEntity<>(persona, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> addPersona(@RequestBody Persona persona) {
		Persona nuevaPersona = personaService.addPersona(persona);
		String mensaje = "Persona con ID " + nuevaPersona.getId() + " agregada con éxito.";
		return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updatePersona(@PathVariable Long id, @RequestBody Persona persona) {
		if (id == null) {
			// Manejar el caso en que el ID es nulo
			return new ResponseEntity<>("El ID no puede ser nulo.", HttpStatus.BAD_REQUEST);
		}

		Optional<Persona> personaExistente = personaService.findById(id);

		if (personaExistente.isPresent()) {
			// Actualizar los datos de la persona existente con los nuevos datos
			Persona personaActualizada = personaExistente.get();
			personaActualizada.setNombre(persona.getNombre());
			personaActualizada.setApellido(persona.getApellido());
			personaActualizada.setCiudad(persona.getCiudad());

			// Guardar la persona actualizada en la base de datos
			personaService.addPersona(personaActualizada);

			String mensaje = "Persona con ID " + id + " actualizada con éxito.";
			return new ResponseEntity<>(mensaje, HttpStatus.OK);
		} else {
			// Manejar el caso en que no se encuentra la persona con el ID proporcionado
			return new ResponseEntity<>("No se encontró ninguna persona con el ID proporcionado.",
					HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePersona(@PathVariable Long id) {
		Persona persona = personaService.getPersonaById(id);

		if (persona != null) {
			personaService.deletePersona(id);
			String mensajeExito = "Persona con ID " + id + " eliminada con éxito.";
			return new ResponseEntity<>(mensajeExito, HttpStatus.NO_CONTENT);
		} else {
			String mensajeNoExiste = "No existe ninguna persona con el ID " + id;
			return new ResponseEntity<>(mensajeNoExiste, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/buscarPorNombre")
    public ResponseEntity<List<Persona>> findByNombre(@RequestParam String nombre) {
        List<Persona> personas = personaService.findByNombre(nombre);
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/buscarPorCiudad")
    public ResponseEntity<List<Persona>> findByCiudad(@RequestParam String ciudad) {
        List<Persona> personas = personaService.findByCiudad(ciudad);
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

}
