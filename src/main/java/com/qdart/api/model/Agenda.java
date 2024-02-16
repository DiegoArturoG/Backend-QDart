package com.qdart.api.model;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * En la clase donde se establece la lógica del negocio (model) voy a hacer que coincida con mi DB.
 * Es decir, la clase se convierte en una Entity, con la anotación @Entity 
 * Para establecer la tabla de la DB a la que corresponde esta entidad, utilizamos la anotación @Table(name="table") y el nombre de la tabla
 * Indicarle a JPA mi punto de entrada (PK), usando la anotación @Id
 * Como queremos que el Id sea autoincrementable usamos la anotación @GeneratedValue(strategy = GenerationType.IDENTITY
 * Y si queremos, podemos establecer la columna a la que corresponde cada atributo con la anotación @Column(name="name"), pero SI TENEMOS QUE DEFINIR el nombre de la primera columna sobre el atributo id 
 */


@Entity
@Table(name="agenda")	//nombre de la tabla de la DB
public class Agenda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_agenda")
	private Long id;
	@Column(name="nombre_cliente")
	private String nombreCliente;
	@Column(name="telefono")
	private String telefono;
	@Column(name="direccion_evento")
	private String direccionEvento;
	@Column(name="fecha_evento")
	private LocalDate fechaEvento;
	@Column(name="horario_evento_inicial")
	private LocalTime horarioEventoInicial;
	@Column(name="horario_evento_final")
	private LocalTime horarioEventoFinal;
	@Column(name="comentario_servicio")
	private String comentarioServicio;
	@Column(name="id_servicio")
	private String idServicio;
	@Column(name="id_estado")
	private String idEstado;

	//JPA necesita un método que le permita construir cualquier objeto sin tomar en cuenta sus atributos. Este método se conoce como constructor vacío
		public Agenda() {	
		}

	public Agenda(Long id, String nombreCliente, String telefono, String direccionEvento, LocalDate fechaEvento,
			LocalTime horarioEventoInicial, LocalTime horarioEventoFinal, String comentarioServicio, String idServicio,
			String idEstado) {
		this.id = id;
		this.nombreCliente = nombreCliente;
		this.telefono = telefono;
		this.direccionEvento = direccionEvento;
		this.fechaEvento = fechaEvento;
		this.horarioEventoInicial = horarioEventoInicial;
		this.horarioEventoFinal = horarioEventoFinal;
		this.comentarioServicio = comentarioServicio;
		this.idServicio = idServicio;
		this.idEstado = idEstado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccionEvento() {
		return direccionEvento;
	}

	public void setDireccionEvento(String direccionEvento) {
		this.direccionEvento = direccionEvento;
	}

	public LocalDate getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(LocalDate fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public LocalTime getHorarioEventoInicial() {
		return horarioEventoInicial;
	}

	public void setHorarioEventoInicial(LocalTime horarioEventoInicial) {
		this.horarioEventoInicial = horarioEventoInicial;
	}

	public LocalTime getHorarioEventoFinal() {
		return horarioEventoFinal;
	}

	public void setHorarioEventoFinal(LocalTime horarioEventoFinal) {
		this.horarioEventoFinal = horarioEventoFinal;
	}

	public String getComentarioServicio() {
		return comentarioServicio;
	}

	public void setComentarioServicio(String comentarioServicio) {
		this.comentarioServicio = comentarioServicio;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	@Override
	public String toString() {
		return "Agenda [id=" + id + ", nombreCliente=" + nombreCliente + ", telefono=" + telefono + ", direccionEvento="
				+ direccionEvento + ", fechaEvento=" + fechaEvento + ", horarioEventoInicial=" + horarioEventoInicial
				+ ", horarioEventoFinal=" + horarioEventoFinal + ", comentarioServicio=" + comentarioServicio
				+ ", idServicio=" + idServicio + ", idEstado=" + idEstado + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(comentarioServicio, direccionEvento, fechaEvento, horarioEventoFinal, horarioEventoInicial,
				id, idEstado, idServicio, nombreCliente, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		return Objects.equals(comentarioServicio, other.comentarioServicio)
				&& Objects.equals(direccionEvento, other.direccionEvento)
				&& Objects.equals(fechaEvento, other.fechaEvento)
				&& Objects.equals(horarioEventoFinal, other.horarioEventoFinal)
				&& Objects.equals(horarioEventoInicial, other.horarioEventoInicial) && Objects.equals(id, other.id)
				&& Objects.equals(idEstado, other.idEstado) && Objects.equals(idServicio, other.idServicio)
				&& Objects.equals(nombreCliente, other.nombreCliente) && Objects.equals(telefono, other.telefono);
	}

	
		
}