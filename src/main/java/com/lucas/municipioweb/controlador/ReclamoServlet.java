/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.lucas.municipioweb.controlador;

import com.lucas.municipioweb.modelo.Modelo;
import com.lucas.municipioweb.modelo.ModeloReclamo;
import com.lucas.municipioweb.modelo.daos.ReclamoDAO;
import com.lucas.municipioweb.modelo.dtos.Reclamo;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas oliva
 */
public class ReclamoServlet extends HttpServlet {

   private Modelo<Reclamo> modelo;

    @Override
    public void init() throws ServletException {
        //super.init();
        this.modelo = new ModeloReclamo(new ReclamoDAO());
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String accion = request.getParameter("accion");  
        
        if((accion == null) || accion.isEmpty())
            accion="listar";
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}