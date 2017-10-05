/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.reportes.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author eve
 */
@Component
public class MyFilter implements HandlerInterceptor{  
        
  
    @Override
    public boolean preHandle(HttpServletRequest request, 
                   HttpServletResponse response, Object object) throws Exception {
           
           
           String method = request.getMethod();
           String pass = request.getParameter("password");
           System.out.println("METHOD::" + method);
           System.out.println("PASS::" + pass);
           return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
                   Object object, ModelAndView model)
                   throws Exception {
           
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
                   Object object, Exception arg3)
                   throws Exception {
           
    }

    
 

    
}
