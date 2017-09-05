/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.reportes.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import org.reportes.services.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author eve
 */
@Controller
public class ReporteIngresosController {
    @Autowired
    ArtistaService serviceArtista;
    
    @RequestMapping(value = "/ingresos", method = RequestMethod.GET)
    public String onat(@RequestParam(required= false, defaultValue="") String nombre,
                       @RequestParam(required= false, defaultValue="") String ci, 
                       Model model,@SortDefault("nombre") Pageable pageable) {
        
        model.addAttribute("page", serviceArtista.search("%"+nombre+"%","%"+ci+"%",pageable));        
        return "reporteIngresos/index";
    }
    
    @RequestMapping(value = "/ingresos/pdf/{id}", method = RequestMethod.GET)
    public @ResponseBody void pdf(@PathVariable Integer id, 
                                  @RequestParam(value="fechaI", required=true) String fechaI,
                                  @RequestParam(value="fechaF", required=true) String fechaF,
                                  HttpServletResponse response) throws SQLException {
        System.out.println(id);
        System.out.println(fechaI);
        System.out.println(fechaF);
    }
}
