package com.developer.agenda.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "empresas")
public class Empresa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Contacto> contactos;
    
    public Empresa() {
    	
    }

	public Empresa(Long id, String nombre, List<Contacto> contactos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contactos = contactos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
    
    

}
