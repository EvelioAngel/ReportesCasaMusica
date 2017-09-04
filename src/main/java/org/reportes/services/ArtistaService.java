/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.reportes.services;

import org.reportes.model.Artista;
import org.reportes.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 *
 * @author Evelio
 */
@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository repo;
    
    public Page<Artista> list(Pageable pageable){
        return repo.findAll(pageable);
    } 
}
