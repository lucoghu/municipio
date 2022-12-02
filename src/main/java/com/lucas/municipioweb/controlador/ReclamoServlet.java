/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.lucas.municipioweb.controlador;

import com.lucas.municipioweb.modelo.Modelo;
import com.lucas.municipioweb.modelo.ModeloReclamo;
import com.lucas.municipioweb.modelo.daos.ReclamoDAO;
import com.lucas.municipioweb.modelo.dtos.Categoria;
import com.lucas.municipioweb.modelo.dtos.Persona;
import com.lucas.municipioweb.modelo.dtos.Reclamo;
import com.lucas.municipioweb.modelo.dtos.TipoCategoria;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;


/**
 *
 * @author Lucas oliva
 */
public class ReclamoServlet extends HttpServlet {

   private Modelo<Reclamo> modelo;

    @Override
    public void init() throws ServletException {
        super.init();
        this.modelo = new ModeloReclamo(new ReclamoDAO());
    }
    

   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       // int idpersona= Integer.parseInt(resquest.session.getAttribute("idpersona"));
        HttpSession sesion = request.getSession();
        //se necita un objeto Persona, no solo su id, para poder crear un Reclamo
       // int idpersona=Integer.parseInt((String)sesion.getAttribute("idpersona"));
        Persona persona=(Persona)sesion.getAttribute("persona");
         
        String accion = request.getParameter("accion");
  
          
          if("verreclamo".equals(accion))
            verReclamo(request,response, persona);
               else if("insertar".equals(accion)) {
                   try{
                       generarReclamo(request, response, persona);
                       }catch(ServletException | IOException ex){
                        request.setAttribute("Mensaje", "No se pudo Generar el Reclamo" + ex.getMessage());
                       }
                    }
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
    }

   

    private void verReclamo(HttpServletRequest request, HttpServletResponse response, Persona per) {
       Collection<Reclamo> reclamos; //lista de Reclamos completas, incluye objeto Persona, en JSP mostrar lo necesario
        try{
        reclamos = modelo.listar();       
        request.setAttribute("ListaRU", reclamos);
        RequestDispatcher rd;  //declaro variable
        rd=request.getRequestDispatcher("pages/ListaDeReclamosUsuario.jsp");
        rd.forward(request, response);
        }catch(ServletException | IOException ex){
           request.setAttribute("Mensaje", "No se pudo Listar sus Reclamos" + ex.getMessage());
           }
        }

    
    private void generarReclamo(HttpServletRequest request, HttpServletResponse response, Persona per) throws ServletException, IOException {
        
        int creado=0;
        String domicilio = request.getParameter("domicilio");
        
        String cate="";
        String cat = request.getParameter("categoria");
   
       if ("alumbrado".equals(cat)) {
          cate="alumbrado";
        }
       if ("arboldo".equals(cat)) {
          cate="arbolado";
        }
       if ("limpieza".equals(cat)) {
          cate="limpieza";
        }
       if ("pluvial".equals(cat)) {
          cate="pluvial";
        }
        
       String descripcion=request.getParameter("descripcion");
        //Date f= new Date();  // fecha actual, pero tambien muestra hora y zona hoaria, no me sirve
        LocalDate fe= LocalDate.now(); // muestra este formato: yyyy-MM-dd
      
      // SimpleDateFormat formateo= new SimpleDateFormat("yyyy-MM-dd");
       Date fechacreacion=java.sql.Date.valueOf(fe);
       
       creado= modelo.crear(new Reclamo(fechacreacion, null, domicilio, new Categoria(TipoCategoria.valueOf(cate.toUpperCase())),descripcion,new Persona(per.getIdpersona(),per.getDni(),per.getNombre(),per.getApellido(),per.getMail(),per.getTelef(),per.getUser(),per.getClave(),0)));
       RequestDispatcher rd;
       if(creado!=0){ //hay que pasar la variable de sesion, entonces Dispatcher y no response.sendRedirect("pagina.jsp")
         rd=request.getRequestDispatcher("pages/PanelContribuyente.jsp");
         rd.forward(request, response); 
       } /*else { //hay que enviar error de carg de reclamo, idem a loginIncorrecto.jsp
             rd=request.getRequestDispatcher("pages/ErrorRegistroReclamo.jsp");
             rd.forward(request, response);
           } */
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}