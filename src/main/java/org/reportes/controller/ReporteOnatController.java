/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.reportes.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eve
 */
@Controller
public class ReporteOnatController {

    @Autowired
    private JdbcTemplate jdbctemplate;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/onat", method = RequestMethod.GET)
    public void ReporteOnat() {
        String sql = "SELECT * "                                
                + "FROM "               
                + "nomina "
                + "WHERE " 
                + "id_nomina = 2"
                +"ORDER BY "
                +"nomina.fecha ASC;";
        List<Map<String, Object>> ls = jdbctemplate.queryForList(sql);

        ls.stream().forEach((row) -> {
            System.out.printf("%d ", row.get("id_nomina"));
            System.out.printf("%s ", row.get("fecha"));
            System.out.println(row.get("codigo"));
        });
    }

}
