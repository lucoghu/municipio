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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
      
        doPost(request,response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
        HttpSession sesion = request.getSession();
        //se necita un objeto Persona, no solo su id, para poder crear un Reclamo
       // int idpersona=Integer.parseInt((String)sesion.getAttribute("idpersona"));
        Persona persona=(Persona)sesion.getAttribute("persona");
         
        String accion = request.getParameter("accion");
  
          
         if("listaruser".equals(accion))
                         try {
                             buscarReclamos(request,response, persona);
        } catch (Exception ex) {
            request.setAttribute("Mensaje", "No se pudo listar el Reclamo del Usuario" + ex.getMessage());
        }
               
        if("insertar".equals(accion)) {
                   try{
                       generarReclamo(request, response, persona);
                       }catch(ServletException | IOException ex){
                        request.setAttribute("Mensaje", "No se pudo Generar el Reclamo" + ex.getMessage());
                       }
                    }
        if("listarreclamos".equals(accion)) {
           verReclamos(request,response);}
        
        if("buscaridReclamo".equals(accion)) {     
                 try {
                        buscaridReclamo(request, response);
                    } catch (Exception ex) {
                        request.setAttribute("mensaje", "No se pudo encontrar el Reclamo" + ex.getMessage());
                      //  ex.printStackTrace();
                    }
        }
        
        
        if("actualizar".equals(accion)) {
            try {
                actualizarReclamo(request,response);
            } catch (ParseException ex) {
                request.setAttribute("mensaje", "No se pudo actualizar el Reclamo" + ex.getMessage());
            }
        }
        
        if("eliminar".equals(accion)) {
           try {
                 eliminarReclamo(request, response);
                 } catch (Exception ex) {
                 request.setAttribute("mensaje", "No se pudo eliminar el reclamo" + ex.getMessage());
                 }
        }
        
       if("cerrar".equals(accion)) {
           cerrarSesion(request,response);
        
       }  
        
    }

   
    private void verReclamos(HttpServletRequest request, HttpServletResponse response) {
       Collection<Reclamo> reclamos; //lista de Reclamos completas, incluye objeto Persona, en JSP mostrar lo necesario
        try{
        reclamos = modelo.listar();       
        
        request.setAttribute("ListaR", reclamos); //cargo el parametro resquest
        RequestDispatcher rd;  //declaro variable
        rd=request.getRequestDispatcher("pages/ListaDeReclamos.jsp");  //redirecciono el servlet mediante el objeto rd hacia el archivo jsp
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
       if ("arbolado".equals(cat)) {
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
    
    
    
    //Para actulizar Reclamo buscado por su id
    private void buscaridReclamo(HttpServletRequest request, HttpServletResponse response)  
     throws Exception  {
          Reclamo r;
          int idreclamo=Integer.parseInt(request.getParameter("idreclamo"));
          
          r=modelo.buscarKey(idreclamo);
          request.setAttribute("reclamoencontrado", r);
          
          //envio los datos del reclamo encontrado al formulario de actualización, para actualizar datos
         RequestDispatcher rd;
         rd=request.getRequestDispatcher("pages/FormularioActualizacionReclamo.jsp");
         rd.forward(request, response);
     }
    
    
    
    private void actualizarReclamo(HttpServletRequest request, HttpServletResponse response) 
       throws ServletException, IOException, ParseException{
       int actualizado=0;
          
       int idreclamo=Integer.parseInt(request.getParameter("idreclamo"));
       SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
  
        Date fechacreacion=formato.parse(request.getParameter("fechacreacion"));
        
        Date fecharesolucion=formato.parse(request.getParameter("fecharesolucion"));
      
       String domicilio=request.getParameter("domicilio");
       int idcategoria=Integer.parseInt(request.getParameter("idcategoria"));
       String cat = request.getParameter("categoria()");   
       
       
       String descripcion=request.getParameter("comentario"); 
       
       //Persona perso = (Persona)request.getParameter("perso");//no funciona para objetos
       int idpersona=Integer.parseInt(request.getParameter("idpersona"));
           
          actualizado= modelo.actualizar(new Reclamo(idreclamo,fechacreacion,fecharesolucion,domicilio,new Categoria(idcategoria,TipoCategoria.valueOf(cat.toUpperCase())),descripcion,new Persona(idpersona)));
       
          // RequestDispatcher rd;
          if(actualizado!=0){
               verReclamos(request, response);
          } 
    }
    
     private void buscarReclamos(HttpServletRequest request, HttpServletResponse response, Persona p)  
     throws Exception  {
          Collection<Reclamo> reclamos;
          int idpersona=p.getIdpersona(); 
          
          reclamos=modelo.buscarEntero(idpersona); 
          request.setAttribute("reclamosuser", reclamos);
          RequestDispatcher rd;
          rd=request.getRequestDispatcher("pages/ListaDeReclamosUsuario.jsp");
          rd.forward(request, response);
     }
     
     
     private void eliminarReclamo(HttpServletRequest request, HttpServletResponse response)  
      throws Exception {
          int eliminado=0;
          int idreclamo=Integer.parseInt(request.getParameter("idreclamo"));
          eliminado=modelo.eliminar(idreclamo);
          if(eliminado!=0){
           verReclamos(request, response);
          }
     }
    
     
      private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
         HttpSession sesion = request.getSession();
         sesion.setAttribute("usuariosesion", null);
         sesion.invalidate();
         response.sendRedirect("index.jsp");
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
