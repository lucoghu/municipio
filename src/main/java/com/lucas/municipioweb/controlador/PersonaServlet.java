package com.lucas.municipioweb.controlador;

import com.lucas.municipioweb.modelo.Modelo;
import com.lucas.municipioweb.modelo.ModeloPersona;
import com.lucas.municipioweb.modelo.daos.PersonaDAO;
import com.lucas.municipioweb.modelo.dtos.Persona;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Lucas Oliva
 */
@WebServlet(name = "PersonaServlet", urlPatterns = {"/PersonaServlet"})
public class PersonaServlet extends HttpServlet {

  
   private Modelo<Persona> modelo;

    @Override
    public void init() throws ServletException {
        //super.init();
        this.modelo = new ModeloPersona(new PersonaDAO());
    }
   
   
  /*  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        /*    out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PersonaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PersonaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } */

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //int id;
        String accion = request.getParameter("accion");  
        
        if((accion == null) || accion.isEmpty())
            accion="listar";
            
          switch (accion) {   
            case "listar":             
                 listadoPersonas(request, response);
                 break;
          
            case "insertar":                 //registro.jsp
                  crearPersona(request, response);
                  break;
                  
             case "buscaridPersona":     
                {
                    try {
                        buscaridPersona(request, response);
                    } catch (Exception ex) {
                        request.setAttribute("mensaje", "No se pudo encontrar a la persona" + ex.getMessage());
                      //  ex.printStackTrace();
                    }
                }
                  break;
                  
             case "actualizar":             
                 actualizarPersona(request, response);
                 break;
                 
             case "eliminar": 
             { 
                  try{
                      eliminarPersona(request, response);
                  } catch (Exception ex) {
                        ex.printStackTrace();
                    } 
             }   
                break;
                
             } //cierra switch
       }
  
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //processRequest(request, response);
   /*    String accion = request.getParameter("accion");
        if("validar".equals(accion))
           validarpersona(request, response); */

   /*   String accion = request.getParameter("accion");  
        
        if((accion == null) || accion.isEmpty()){
            accion="listar";
            
          switch (accion) {   
            case "listar":             
                 listadoPersonas(request, response);
                 break;
          
            case "insertar":                 //registro.jsp
                  crearPersona(request, response);
                  break;
                  
             case "buscaridPersona":     
                  buscaridPersona(request, response);
                  break;
            }
        } */
   
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
     
    

    private void listadoPersonas(HttpServletRequest request, HttpServletResponse response) {
         
        Collection<Persona> personas; //listar() de Modelo devuelve una lista de Personas
        try{
        personas = modelo.listar();  
        
        request.setAttribute("ListaP", personas); //cargo el parametro resquest
        RequestDispatcher rd;  //declaro variable
        rd=request.getRequestDispatcher("pages/ListaDePersonas.jsp");  //redirecciono el servlet mediante el objeto rd hacia el archivo jsp
        rd.forward(request, response);  //paso los recursos: request.setAttribute("ListaP", personas)
         //las 3 lineas anteriores equivale a:
         //request.getRequestDispatcher(".pages/ListaDePersonas.jsp"). forward(request, response);
        }catch(ServletException | IOException ex){
           request.setAttribute("Mensaje", "No se pudo Listar Personas" + ex.getMessage());
           }
    }

    
    //Registro.jsp
    private void crearPersona(HttpServletRequest request, HttpServletResponse response)  
     throws ServletException, IOException  {
    int creada=0;
    int idpersona=0;
       
       String nombre=request.getParameter("nombre");
       String apellido=request.getParameter("apellido");
       int dni=Integer.parseInt(request.getParameter("dni"));
       String mail=request.getParameter("mail");   // ver ???, porque en el formulario el campo es type="email"
       int telef=Integer.parseInt(request.getParameter("telefono"));   // ver ???, porque en el formulario el campo es type="tel"
      // String usuario=request.getParameter("usuario");
      // String clave=request.getParameter("password");   // ver ???, porque en el formulario el campo es type="password"
       //por defecto todas las personas no son administradores
       //creada= modelo.crear(new Persona(dni, nombre, apellido, mail,telef , usuario, clave, false));
      creada= modelo.crear(new Persona(dni, nombre, apellido, mail,telef ));
      
 //// esto lo hago para poder devolver el idpersona generado al crear persona, ya que tuve que dividir el registro en 2      
     // idpersona= modelo.crear(new Persona(dni, nombre, apellido, mail,telef ));
  // SEGUIR: tiene que direccionar al registroCredencial.jsp, ya no vale  if(creada!=0) y que vaya al login.html
 ////////////////////////////////////////////////////////////////////
 
      // RequestDispatcher rd;
      if(creada!=0){
       //redirecciono el servlet mediante el objeto rd hacia el login.html
       response.sendRedirect("pages/login.html");
        // rd=request.getRequestDispatcher("pages/login.html");
         //rd.forward(request, response); 
       } else {
           response.sendRedirect("pages/registro.jsp");
           //rd=request.getRequestDispatcher("pages/registro.html"); //ver como mostrar erro de Registro
           }
    }
    
     private void buscaridPersona(HttpServletRequest request, HttpServletResponse response)  
     throws Exception /* ver si hace falta*/ {
          Persona p;
          int idpersona=Integer.parseInt(request.getParameter("idpersona")); //todo lo que viene de un JSP o HTML es una cadena
          
          p=modelo.buscarEntero(idpersona); //trae todo el objeto Persona, incluso su id, en este caso el id no hace
          //falta pero lo dejo así porque sino hay que hacer otro pullPreparedStatement en la Clase PersonaDAO donde
          // se llame al constructor de Persona sin el id, y al hacer esto ya no sirve el pullPreparedStatement para
          //cuando necesite buscar por un String en lugar de un Entero( se nesecita extraer id de BD porque no viene como parametro)
          request.setAttribute("personaencontrada", p);
          
          //envio los datos de la personaencontrada al formulario de actualización, para actualizar datos
          // es decir de pages/FormularioActualizacion.jsp se muestrar los datos anterios, permitiendo modificarlos
          RequestDispatcher rd;
          rd=request.getRequestDispatcher("pages/FormularioActualizacion.jsp");
          rd.forward(request, response);
     }
    
    
       private void actualizarPersona(HttpServletRequest request, HttpServletResponse response) {
          int actualizada=0;
          
          int idpersona=Integer.parseInt(request.getParameter("idpersona"));
          String nombre=request.getParameter("nombre");
          String apellido=request.getParameter("apellido");
          int dni=Integer.parseInt(request.getParameter("dni"));
          String mail=request.getParameter("mail");  
          int telef=Integer.parseInt(request.getParameter("telefono"));  
         
          try{
          actualizada= modelo.actualizar(new Persona(idpersona,dni, nombre, apellido, mail,telef ));
          if(actualizada!=0)
          listadoPersonas(request, response);
        
   // VER COMO HACER EN CASO DE QUE NO SE ACTUALIZE, DEBERIA VOLVER FORMULARIO ACTUALIZACIO Y DESPUES LINK A PANEL ADMIN
            // } else {
          }catch(Exception e){
             request.setAttribute("Mensaje", "No se pudo actualizar Persona" + e.getMessage());
              //en el Formulario de actualizacion hacer un boton(link) al panel Admin
                 //response.sendRedirect("pages/FormularioActualizacion.jsp");
              } 
       }

    
     private void eliminarPersona(HttpServletRequest request, HttpServletResponse response)  
     throws Exception {
          int eliminada=0;
          int idpersona=Integer.parseInt(request.getParameter("idpersona"));
          eliminada=modelo.eliminar(idpersona);
           listadoPersonas(request, response);
          
     }
       
       
       
   /*
    private void validarpersona(HttpServletRequest request, HttpServletResponse response) {
   
        HttpSession sesionHttp = request.getSession();
        
        String usuario=request.getParameter("usuario");
        String clave=request.getParameter("password");
        PersonaDAO p=new PersonaDAO();
        p.buscarUser(usuario, clave);
       // sesionHttp.setAttribute("listaAlumnos", model.getAlumnos());
       
      //  String usuario=request.getSession("usuario");
      // String clave=request.getSession(getSet);
    }  */
   
    //*************para verificar************
    
  /*   private void buscarUser(HttpServletRequest request, HttpServletResponse response) {
          String usuario=request.getParameter("usuario");
          String clave=request.getParameter("password");
          Persona p;
          //try
          p=modelo.buscarCadena(usuario);             
       
     }*/
    
 /*   
    public void mostrar(){
    Collection<Persona>  personas;
     personas = modelo.listar();
    }
    public static void main(String[] args) {
       
      PersonaServlet p = new PersonaServlet();
       p.mostrar();
        
    }*/
  
       }
