package com.qdart.api.service;



import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.qdart.api.exceptions.AgendaNotFoundException;
import com.qdart.api.model.Agenda;
import com.qdart.api.model.User;
import com.qdart.api.repository.AgendaRepository;


@Service
public class AgendaService {
	//Vamos a traer a JPA para que nos brinde los métodos paara recuperar los datos
	private final AgendaRepository repository;
	
	//Constructor permite la inyección de dependencias
	@Autowired
	public AgendaService(AgendaRepository repository) {
		this.repository = repository;
	}
		
	//Crear método de tipo Get para traer a todas las agendas
	public List<Agenda> allAgendas() {
		return repository.findAll();
	}
	
	// Crear método de tipo Get para traer una agenda por Id, arrojando una excepción si no se encuentra
	public Agenda obtenerAgendaPorId(Long id) {
	    return repository.findById(id)
	            .orElseThrow(() -> new AgendaNotFoundException(id));  // Crear una excepción personalizada
	}
		
	// Crear método para eliminar una agenda por ID. Comprobar la existencia antes de la eliminación.
	public void eliminarAgenda(Long id) {
	    if (repository.existsById(id)) {
	        repository.deleteById(id);
	    } else {
	        // Lanzar una excepción personalizada si la agenda con el ID no existe
	        throw new AgendaNotFoundException(id);
	    }
	}
		
	
	public Agenda agregarAgenda(Agenda agenda) {
	    if (agenda.getNombreCliente() == null || agenda.getFechaEvento() == null) {
	        throw new IllegalArgumentException("La agenda debe tener un nombre y una fecha válidos");
	    }

	    // Puedes eliminar la verificación de la existencia de la agenda con un ID específico,
	    // ya que el ID es autoincremental y se asignará automáticamente por la base de datos.
	    
	    try {
	        return repository.save(agenda);
	    } catch (DataAccessException e) {
	        // Manejar la excepción de la base de datos según tus necesidades
	        throw new RuntimeException("Error al guardar la agenda en la base de datos", e);
	    }
	}
	/* Método para actualizar la información de una agenda.
	 * Se busca la agenda por su ID y se actualizan los campos proporcionados en el objeto `agenda`.
	 * Si la agenda existe, se aplican las actualizaciones y se guarda en el repositorio.
	 * Si no existe, se crea una nueva instancia con el ID proporcionado y se guarda en el repositorio. */
	public Agenda actualizarAgenda(Agenda agenda, Long id) {
	    return repository.findById(id)
	            .map(agendaEncontrada -> {
	                // Actualizar cada campo según las modificaciones
	    agendaEncontrada.setNombreCliente(agenda.getNombreCliente());
	    agendaEncontrada.setTelefono(agenda.getTelefono());             		agendaEncontrada.setDireccionEvento(agenda.getDireccionEvento());
	    agendaEncontrada.setFechaEvento(agenda.getFechaEvento());             		agendaEncontrada.setHorarioEventoInicial(agenda.getHorarioEventoInicial());                		agendaEncontrada.setHorarioEventoFinal(agenda.getHorarioEventoFinal());	                		agendaEncontrada.setComentarioServicio(agenda.getComentarioServicio());
	    agendaEncontrada.setIdServicio(agenda.getIdServicio());	                		
	    agendaEncontrada.setIdEstado(agenda.getIdEstado());

	       // Guardar la agenda actualizada en el repositorio
	       return repository.save(agendaEncontrada);
	       })
	       .orElseGet(() -> {
	                // Si no existe, crear una nueva instancia con el ID y guardar en el repositorio
	       agenda.setId(id);
	       return repository.save(agenda);
	       });
}

		
	/*
	 * Podemos buscar una agenda por medio de su nombre para recuperar información. Para ello dependemos de JPQL en el repository y ResponseEntity que se ejecutará en el controller.
	 * Vamos a crear un método para buscar a un usuario por nombre `getAgendaByName`
	 */
	public Agenda getAgendaByName(String nombreCliente) {
		return repository.findByNombreCliente(nombreCliente);
	}	
		
}
