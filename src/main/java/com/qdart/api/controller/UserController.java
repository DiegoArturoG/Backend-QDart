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

import com.qdart.api.model.User;
import com.qdart.api.service.UserService;


@RestController 
@RequestMapping("admin") //Solo admin para llamar otros endpoints 
@CrossOrigin(origins="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
//Cualquier dominio tiene acceso al servicio o htttp://5500, métodos que pueden interactuar con el dominio externo

public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("users")
	public List<User> getUsers() {
		return userService.allUsers();
	}
	//path variable(endpoint), ya que buscamos por id y los id son diferentes, entonces necesito definir el endpoint entre llaves y dentro del método debo crear una anotación @PathVariable
	@GetMapping("users/{id}")
	public User getOne(@PathVariable(name = "id") Long id) {
		return userService.getOne(id);
	}
	
	//Delete, de tipo void y define un path variable
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable(name = "id") Long id) {
		userService.deleteUser(id);
	}
	
	//Post, crear un nuevo usuario
	/*
	 * Cómo agrego usuarios desde Postman? Tenemos que establecer el cuerpo de la instancia en formato JSON (key/value) y mandar el método POST
	 */
	@PostMapping("users")
	public User newUser(@RequestBody User newUser) {
		return userService.addUser(newUser);
	}
	
	//Put, modificar, actualizar a un usuario existente 
	@PutMapping("users/{id}")
	public User replaceUser(@RequestBody User user, @PathVariable(name = "id") Long id) {
		return userService.replaceUser(user, id);
	}
	
	/*
	 *  -- ResponeEntity<> clase de SpringFramework que representa una respuesta HTTP personalizable. Permite controlar el body de la respuesta. Posee dos parámetros:
	 *  1. Especifica el tipo de datos
	 *  2. Especifica el código de estado HTTP HttpStatus.nethod
	 *  -- @RequestParam anotación de springFramework que se utiliza para vincular parámetros de solicitud HTTP que se enviará a la respuesta. Es decir, permite controlar las llaves-valores dentro del parámetro.
	 */
	
	//Método para traer un usuario por Email
	@GetMapping("/users/byEmail")
	public ResponseEntity<User> getUserByEmail(@RequestParam(name = "email") String email) {
		User user = userService.getUserByEmail(email);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
}//class