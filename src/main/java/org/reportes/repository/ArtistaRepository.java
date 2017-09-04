/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.reportes.repository;

import org.reportes.model.Artista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Evelio
 */
public interface ArtistaRepository extends CrudRepository<Artista, Integer>{
    Page<Artista> findAll(Pageable pageable);
    
    Page<Artista> findByNombreIgnoreCaseLikeAndCiLike(String nombre, String ci, Pageable pageable);
}
