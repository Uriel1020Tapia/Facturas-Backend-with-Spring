package com.bolsadeideas.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	
	@Query("select p from Producto p where p.nombre like %?1%")//buscamos cuando la palabra tenga en cualquier parte una letra cadena consulta jpa
	public List<Producto> findByNombre(String term);//opcion uno busca por nombre y que sea igual ala cadena que le pasamos pero en esta opcion armamos nosotros el query
	
	public List<Producto> findByNombreContainingIgnoreCase(String term);//metodo del crudRepository Buscar la cadena que le pasemos e ignora mayusculas
	
	public List<Producto> findByNombreStartingWithIgnoreCase(String term);//tercera opcion

}
