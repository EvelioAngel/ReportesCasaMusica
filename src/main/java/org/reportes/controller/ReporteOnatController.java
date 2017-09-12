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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class ReporteOnatController {

    @Autowired
    private JdbcTemplate jdbctemplate;
    
    @Autowired
    ArtistaService serviceArtista;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
    
    @RequestMapping(value = "/onat", method = RequestMethod.GET)
    public String onat(@RequestParam(required= false, defaultValue="") String nombre,
                       @RequestParam(required= false, defaultValue="") String ci, 
                       Model model,@SortDefault("nombre") Pageable pageable) {
        
        model.addAttribute("page", serviceArtista.search("%"+nombre+"%","%"+ci+"%",pageable));        
        return "reporteOnat/index";
    }
    
    @RequestMapping(value = "/onat/pdf/{id}/{anno}", method = RequestMethod.GET)
    public @ResponseBody void pdf(@PathVariable Integer id, 
                                  @PathVariable Integer anno,
                                  HttpServletResponse response) throws SQLException {
        
        try {
            JasperReport report;
            Resource resource = new ClassPathResource("templates/reporteOnat/newReport.jasper");
            //report = (JasperReport) JRLoader.loadObjectFromFile("templates/reporteOnat/newReport.jasper");
            report = (JasperReport) JRLoader.loadObject(resource.getInputStream());
            
            //fecha y hora actual para renombrar los pdf
            Date date = new Date();
            DateFormat hourdateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            String fechaActual = hourdateFormat.format(date);
            
            //alfinal se pasa la coneccion que se la estoy pidiendo al jdbctemplate
            HashMap<String,Object> params = new HashMap<>();
            params.put("idArtista", id);
            //poner el año actual
            params.put("anno", anno);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params,jdbctemplate.getDataSource().getConnection());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=Imprimir-Ingresos-" + fechaActual + ".pdf");
            
            final OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        
        } catch (JRException ex) {
            Logger.getLogger(ReporteOnatController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReporteOnatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @RequestMapping(value = "/onat/sql", method = RequestMethod.GET)
    public void sql() {
        String sql = "SELECT * FROM nomina WHERE id_nomina = 2 ORDER BY nomina.fecha ASC;";
        
        List<Map<String, Object>> ls = jdbctemplate.queryForList(sql);

        ls.stream().forEach((row) -> {
            System.out.printf("%d ", row.get("id_nomina"));
            System.out.printf("%s ", row.get("fecha"));
            System.out.println(row.get("codigo"));
        });
    }

}
