package com.qdart.api.controller;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qdart.api.model.Agenda;
import com.qdart.api.service.AgendaService;


@RestController  
@RequestMapping("admin") 
@CrossOrigin(origins="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AgendaController {

	private AgendaService agendaService;
	
	@Autowired
	public AgendaController(AgendaService agendaService) {
		this.agendaService = agendaService;
	}
	
	@GetMapping("agendas")
	public List<Agenda> getAgendas() {
		return agendaService.allAgendas();
	}
	
	//path variable, ya que buscamos por id y los id son diferentes, entonces necesito definir el endpoint entre llaves y dentro del método debo crear una anotación @PathVariable
	
	@GetMapping("agendas/{id}") 
	public Agenda obtenerAgenda(@PathVariable(name = "id") Long id) {
		return agendaService.obtenerAgendaPorId(id);
	}
	
	//Delete, de tipo void y define un path variable
	@DeleteMapping("agendas/{id}")
	public void borrarAgenda(@PathVariable(name = "id")Long id) {
		agendaService.eliminarAgenda(id);
	}
	
	//Post, crear una nueva agenda
	/*
	 * ¿Cómo agrego agendas desde Postman? Tenemos que establecer el cuerpo de la instancia en formato JSON (key/value) y mandar el método POST
	 */
	@PostMapping("agendas")
	public Agenda newAgenda(@RequestBody Agenda newAgenda) {
		return agendaService.agregarAgenda(newAgenda);
	}
	
		
	//Put, modificar/actualizar a una agenda existente
	@PutMapping("agendas/{id}")
	public Agenda actualizarAgenda(@RequestBody Agenda agenda, @PathVariable(name = "id")Long id) {
		return agendaService.actualizarAgenda(agenda, id);
	}
		
	
	/*
	 * -- ResponseEntity<> clase de SpringFramework que representa una respuesta HTTP personalizable. Permite controlar el body de la respuesta. Posee dos parámetros:
	 * 		1. Especifica el tipo de datos
	 * 		2. Especifica el código de estado HTTP `HttpStatus.method`
	 * -- @RequestParam anotación de springFramework que se utiliza para vincular parámetros de solicitud HTTP que se enviará a la respuesta. Es decir, permite controlar las llaves-valores dentro del parámetro  
	 * */
		
	//Método para traer una agenda por el nombre del cliente
	@GetMapping("/agendas/byName")
	public ResponseEntity<Agenda> getAgendaByName(@RequestParam(name = "nombreCliente") String nombreCliente) {
		Agenda agenda = agendaService.getAgendaByName(nombreCliente);
		return new ResponseEntity<>(agenda, HttpStatus.OK);
	}
}	
