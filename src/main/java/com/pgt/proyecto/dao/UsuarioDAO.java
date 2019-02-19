package com.pgt.proyecto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pgt.proyecto.model.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{

	List<Usuario> findByName(String name);
	
	@Query("select u from Usuario u where u.name = :name AND u.password = :password")
	Usuario findLogin(@Param("name") String name, @Param("password") String password);
}
