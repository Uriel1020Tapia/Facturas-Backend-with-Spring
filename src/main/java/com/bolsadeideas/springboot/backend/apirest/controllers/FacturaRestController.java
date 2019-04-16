package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Factura;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Producto;
import com.bolsadeideas.springboot.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {
	
	@Autowired//para poder inyectar
	private IClienteService clienteService;
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})//ANOTACION PARA INDICAR QUE SOLO EL USER Y ADMIN TIENEN PERMISO A ESTE METODO 
	@GetMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Factura show(@PathVariable Long id) {
		return clienteService.findFacturaById(id);
	}
	
	@Secured("ROLE_ADMIN")//ANOTACION PARA INDICAR QUE SOLO ELADMIN TIENE PERMISO A ESTE METODO 
	@DeleteMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.deleteFacturaById(id);
	}
	
	@Secured({"ROLE_ADMIN"})//ANOTACION PARA INDICAR QUE SOLO EL ADMIN TIENE PERMISO A ESTE METODO 
	@GetMapping("/facturas/filtrar-productos/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Producto> filtrarProductos(@PathVariable String term){
		return clienteService.findByNombreContainingIgnoreCase(term);
	}
	
	@Secured({"ROLE_ADMIN"})//ANOTACION PARA INDICAR QUE SOLO EL ADMIN TIENE PERMISO A ESTE METODO 
	@PostMapping("/facturas")
	public ResponseEntity<?> create(@Valid @RequestBody Factura factura, BindingResult result) {
		System.out.println("bODY===========>: " + factura);
		Factura facturaNew = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			//FORMA 2 MANEJO DE ERRORES
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + " ' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			facturaNew  = clienteService.saveFactura(factura);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al realizar la inserccion ala base de datos!");
			response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La Factura  ha sido creada con exito!");
		response.put("factura",facturaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
//	@PostMapping("/facturas")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Factura crear(@RequestBody Factura factura) {
//		return clienteService.saveFactura(factura);
//	}
	
	
}
