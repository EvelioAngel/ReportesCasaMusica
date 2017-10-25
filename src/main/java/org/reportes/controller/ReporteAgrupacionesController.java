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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author eve
 */
@Controller
public class ReporteAgrupacionesController {
    
    @Autowired
    DataSource datasource;
    
    @RequestMapping(value = "/agrupaciones/pdf", method = RequestMethod.GET)
    public @ResponseBody void pdf(@RequestParam(value="mes", required=true) Integer mes,
                                  @RequestParam(value="anno", required=true) Integer anno,
                                  HttpServletResponse response) throws SQLException {
                      
        
        try {
            JasperReport report;
            Resource resource = new ClassPathResource("templates/reporteAgrupaciones/agrupaciones.jasper");
            report = (JasperReport) JRLoader.loadObject(resource.getInputStream());
            
            Date date = new Date();
            DateFormat hourdateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            String fechaActual = hourdateFormat.format(date);
            
            HashMap<String,Object> params = new HashMap<>();
            params.put("mes", mes);
            params.put("anno", anno);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params,datasource.getConnection());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=Imprimir-Agrupaciones-" + fechaActual + ".pdf");
            
            final OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                    
        } catch (JRException ex) {
            Logger.getLogger(ReporteIngresosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReporteIngresosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
