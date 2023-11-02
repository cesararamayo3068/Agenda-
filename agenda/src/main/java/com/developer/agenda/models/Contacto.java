package com.developer.agenda.models;

import javax.persistence.*;

@Entity
@Table(name = "contactos")
public class Contacto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    
    public Contacto() {
		
	}

	public Contacto(Long id, Persona persona, Empresa empresa) {
		super();
		this.id = id;
		this.persona = persona;
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

    

}
