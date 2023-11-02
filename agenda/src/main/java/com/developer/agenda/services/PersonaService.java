package com.developer.agenda.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.agenda.models.Persona;
import com.developer.agenda.repositories.PersonaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
	

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    public Persona addPersona(Persona persona) {
        // Puedes realizar validaciones antes de guardar la persona
        return personaRepository.save(persona);
    }

    public Persona updatePersona(Long id, Persona persona) {
        // Puedes implementar la actualización según tus necesidades
        return personaRepository.save(persona);
    }

    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }
    
    public Optional<Persona> findById(Long id) {
        return personaRepository.findById(id);
    }
    
    public List<Persona> findByNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
    }

    public List<Persona> findByCiudad(String ciudad) {
        return personaRepository.findByCiudad(ciudad);
    }

}
