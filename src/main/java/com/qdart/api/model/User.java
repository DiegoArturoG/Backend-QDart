package com.qdart.api.model;

/*
 * En la clase donde se establece la lógica del negocio (model) voy a hacer que coincida con mi DB.
 * Es decir, la clase se convierte en una Entity, con la anotación @Entity 
 * Para establecer la tabla de la DB a la que corresponde esta entidad, utilizamos la anotación @Table(name="table") y el nombre de la tabla
 * Indicarle a JPA mi punto de entrada (PK), usando la anotación @Id
 * Como queremos que el Id sea autoincrementable usamos la anotación @GeneratedValue(strategy = GenerationType.IDENTITY
 * Y si queremos, podemos establecer la columna a la que corresponde cada atributo con la anotación @Column(name="name") pero si tenemos que definir el nombre de la primera columna sobre el atributo Id
 */

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cliente") //Nombre de la tabla de la DB
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cliente")
	private Long id;
	@Column(name="usuario")
	private String nombre;
	@Column(name="correo")
	private String email;
	@Column(name="contrasena")
	private String password;
	@Column(name="rol")
	private String rol;
	@Column(name="estatus")
	private String estatus;
	
	//JPA necesita un método que le permita construir cualquier objto sin tomar en cuenta sus atributos. Este método se conoce como contructor vacío
	public User() {
	}

	public User(Long id, String nombre, String email, String password, String rol, String estatus) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.estatus = estatus;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password + ", rol=" + rol
				+ ", estatus=" + estatus + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, estatus, id, nombre, password, rol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(estatus, other.estatus)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && Objects.equals(rol, other.rol);
	}

	
	
}//
