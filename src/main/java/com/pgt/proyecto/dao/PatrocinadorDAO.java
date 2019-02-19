package com.pgt.proyecto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pgt.proyecto.model.Patrocinador;

@Repository
public interface PatrocinadorDAO extends JpaRepository<Patrocinador, Integer>{

	List<Patrocinador> findByName(String name);
}
