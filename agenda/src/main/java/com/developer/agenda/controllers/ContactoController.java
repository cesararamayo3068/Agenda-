package com.developer.agenda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.developer.agenda.exceptions.CustomNotFoundException;
import com.developer.agenda.models.Contacto;
import com.developer.agenda.services.ContactoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contactos")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;


    @GetMapping
    public ResponseEntity<List<Contacto>> getAllContactos() {
        List<Contacto> contactos = contactoService.getAllContactos();
        return new ResponseEntity<>(contactos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getContactoById(@PathVariable Long id) {
        Contacto contacto = contactoService.getContactoById(id);

        if (contacto == null) {
            throw new CustomNotFoundException("Contacto no encontrado con ID: " + id);
        }

        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addContacto(@RequestBody Contacto contacto) {
        Contacto nuevoContacto = contactoService.addContacto(contacto);
        String mensaje = "Contacto con ID " + nuevoContacto.getId() + " agregado con éxito.";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContacto(@PathVariable Long id, @RequestBody Contacto contacto) {
        if (id == null) {
            return new ResponseEntity<>("El ID no puede ser nulo.", HttpStatus.BAD_REQUEST);
        }

        Optional<Contacto> contactoExistente = contactoService.findById(id);

        if (contactoExistente.isPresent()) {
            Contacto contactoActualizado = contactoExistente.get();

            // Actualizar campos específicos
            contactoActualizado.setEmpresa(contacto.getEmpresa());
            contactoActualizado.setPersona(contacto.getPersona());
    
            // Actualizar relaciones
            if (contacto.getPersona() != null) {
                contactoActualizado.setPersona(contacto.getPersona());
            }

            if (contacto.getEmpresa() != null) {
                contactoActualizado.setEmpresa(contacto.getEmpresa());
            }

            contactoService.addContacto(contactoActualizado);

            String mensaje = "Contacto con ID " + id + " actualizado con éxito.";
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró ningún contacto con el ID proporcionado.", HttpStatus.NOT_FOUND);
        }
    }

    

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContacto(@PathVariable Long id) {
        contactoService.deleteContacto(id);
        String mensaje = "Contacto con ID " + id + " eliminado con éxito.";
        return new ResponseEntity<>(mensaje, HttpStatus.NO_CONTENT);
    }

}
