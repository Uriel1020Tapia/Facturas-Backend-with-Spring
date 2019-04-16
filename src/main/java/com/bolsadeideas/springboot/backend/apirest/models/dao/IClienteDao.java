package com.bolsadeideas.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Region;

public interface IClienteDao extends JpaRepository<Cliente, Long>{
//long es el tipo de dato del id 
//aqui heredamos los metodos del CrudRepository
	
	@Query("from Region")//consulta JPA para obtener la lista de regiones
	public List<Region> findAllRegiones();//mapeamos el metodo a una consulta
}
