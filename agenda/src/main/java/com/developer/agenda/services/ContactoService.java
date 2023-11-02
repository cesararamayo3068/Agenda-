package com.developer.agenda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.agenda.models.Contacto;
import com.developer.agenda.models.Persona;
import com.developer.agenda.repositories.ContactoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoService {

	@Autowired
	private ContactoRepository contactoRepository;

	public List<Contacto> getAllContactos() {
		return contactoRepository.findAll();
	}

	public Contacto getContactoById(Long id) {
		return contactoRepository.findById(id).orElse(null);
	}

	public Contacto addContacto(Contacto contacto) {

		return contactoRepository.save(contacto);
	}

	public Contacto updateContacto(Long id, Contacto contacto) {
		return contactoRepository.save(contacto);
	}

	public void deleteContacto(Long id) {
		contactoRepository.deleteById(id);
	}

	public Optional<Contacto> findById(Long id) {
		return contactoRepository.findById(id);
	}
}
