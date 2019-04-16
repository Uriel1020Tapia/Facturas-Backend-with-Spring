package com.bolsadeideas.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username); //metodo Tipo query

//	@Query("select u from Usuari u where u.username=?1") //otra opcion para hacer una consulta sql
//	public Usuario findByUsername(String username);
}
