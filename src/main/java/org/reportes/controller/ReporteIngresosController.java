/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.reportes.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.reportes.services.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
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
            
    @Autowired
    DataSource datasource;
    
    @RequestMapping(value = "/ingresos", method = RequestMethod.GET)
    public String onat(@RequestParam(required= false, defaultValue="") String nombre,
                       @RequestParam(required= false, defaultValue="") String ci, 
                       Model model,@SortDefault("nombre") Pageable pageable) {
        
        model.addAttribute("page", serviceArtista.search("%"+nombre+"%","%"+ci+"%",pageable));        
        return "reporteIngresos/index";
    }
    
    @RequestMapping(value = "/ingresos/pdf/{id}", method = RequestMethod.GET)
    public @ResponseBody void pdf(@PathVariable Integer id, 
                                  @RequestParam(value="fechaI", required=true)@DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaI,
                                  @RequestParam(value="fechaF", required=true)@DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaF,
                                  HttpServletResponse response) throws SQLException {
                      
        
        try {
            JasperReport report;
            Resource resource = new ClassPathResource("templates/reporteIngresos/ingresos.jasper");
            report = (JasperReport) JRLoader.loadObject(resource.getInputStream());
            
            Date date = new Date();
            DateFormat hourdateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            String fechaActual = hourdateFormat.format(date);
            
            HashMap<String,Object> params = new HashMap<>();
            params.put("idArtista", id);
            params.put("fechaI", fechaI);
            params.put("fechaF", fechaF);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params,datasource.getConnection());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=Imprimir-Ingresos-" + fechaActual + ".pdf");
            
            final OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                    
        } catch (JRException ex) {
            Logger.getLogger(ReporteIngresosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReporteIngresosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
