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
import java.sql.SQLDataException;
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
        super.init();
        this.modelo = new ModeloPersona(new PersonaDAO());
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
  
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

     String accion = request.getParameter("accion");  
        
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
                 try {
                        buscaridPersona(request, response);
                    } catch (Exception ex) {
                        request.setAttribute("mensaje", "No se pudo encontrar a la persona" + ex.getMessage());
                      //  ex.printStackTrace();
                    }
                  break;
               
              case "validar":             
                    validarPersona(request, response);
                    break;
                
               case "cerrar":             
                    cerrarSesion(request, response);
                    break;
            }
        }
   
    }

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
       
       String nombre=request.getParameter("nombre");
       String apellido=request.getParameter("apellido");
       int dni=Integer.parseInt(request.getParameter("dni"));
       String mail=request.getParameter("mail");   
       int telef=Integer.parseInt(request.getParameter("telefono"));
       String usuario=request.getParameter("usuario");
       String clave=request.getParameter("password");
       
      // para prueba, sigue sin funcionar el servlet
    /*   response.setContentType("text/html;charset=UTF-8");
       try(PrintWriter out= response.getWriter())
       { 
           out.println("nombre ");
           out.println("apellido ");
           out.println("dni ");
           out.println("mail ");                 
        }*/
       
       //por defecto todas las personas no son administradores, con Admin=0
       creada= modelo.crear(new Persona(dni, nombre, apellido, mail,telef,usuario,clave,0 ));
       RequestDispatcher rd;
       if(creada!=0){
       //redirecciono el servlet mediante el objeto rd hacia el login.html
       response.sendRedirect("pages/login.jsp");
        // rd=request.getRequestDispatcher("pages/login.jsp");  //tampoco funciona
       //  rd.forward(request, response); 
       } else {
           response.sendRedirect("pages/registro.jsp");
           //rd=request.getRequestDispatcher("pages/registro.html"); //ver como mostrar error de Registro
           }
    }
    
    
     private void buscaridPersona(HttpServletRequest request, HttpServletResponse response)  
     throws Exception  {
          Persona p;
          int idpersona=Integer.parseInt(request.getParameter("idpersona")); //todo lo que viene de un JSP o HTML es una cadena
          
          p=modelo.buscarKey(idpersona); //trae todo el objeto Persona, incluso su id, en este caso el id no hace
          request.setAttribute("personaencontrada", p);
          
          //envio los datos de la personaencontrada al formulario de actualización, para actualizar datos
          RequestDispatcher rd;
          rd=request.getRequestDispatcher("pages/FormularioActualizacion.jsp");
          rd.forward(request, response);
     }
    
    
       private void actualizarPersona(HttpServletRequest request, HttpServletResponse response) 
       throws ServletException, IOException{
           int actualizada=0;
          
          int idpersona=Integer.parseInt(request.getParameter("idpersona"));
          String nombre=request.getParameter("nombre");
          String apellido=request.getParameter("apellido");
          int dni=Integer.parseInt(request.getParameter("dni"));
          String mail=request.getParameter("mail");  
          int telef=Integer.parseInt(request.getParameter("telefono"));  
          actualizada= modelo.actualizar(new Persona(idpersona,dni, nombre, apellido, mail,telef ));
       
          // RequestDispatcher rd;
          if(actualizada!=0){
         //redirecciono el servlet mediante el objeto rd hacia el login.jsp
          response.sendRedirect("pages/login.jsp");
         // rd=request.getRequestDispatcher("pages/login.jsp");
         //rd.forward(request, response); 
             } else {
                 response.sendRedirect("pages/registro.jsp");
           //rd=request.getRequestDispatcher("pages/registro.jsp"); //ver como mostrar erro de Registro
           }
       }

    
    
    
    private void validarPersona(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        
      try{
        //  String usuario=request.getSession("usuario");
      // String clave=request.getSession("password");
        
        String usuario=request.getParameter("usuario");
        String clave=request.getParameter("password");
        
        
        Persona p=new Persona();
        p=modelo.buscarCadena(usuario); //obtengo una persona
        
        if(usuario.equals(p.getUser()) && clave.equals(p.getClave())){
           HttpSession sesion = request.getSession();
 //En realidad se necesita pasarle a sesion una persona, ya que la necesito en ReclamoServlet para generar Reclamo
         sesion.setAttribute("usuariosesion", usuario);
         //  sesion.setAttribute("idpersona", p.getIdpersona()); //guardo su id para mostrar reclamos por idpersona PANEL CONTRIBUYENTE
        sesion.setMaxInactiveInterval(1000); 
        sesion.setAttribute("persona", p);  
           
           if(p.getAdmin()==0){
              RequestDispatcher rd;
              rd=request.getRequestDispatcher("pages/PanelContribuyente.jsp");
              rd.forward(request, response);
            }else { //direcciono al PANEL ADMINSTRADOR, y recupero la variable dentro de este panel con session.getAtribute("usuariosesion")
                RequestDispatcher rd;
                rd=request.getRequestDispatcher("pages/PanelAdministrador.jsp");
                rd.forward(request, response);
            }  
                
        } else{ //direcciono a JSP "loginIncorrecto"
              RequestDispatcher rd;
              rd=request.getRequestDispatcher("pages/loginIncorrecto.jsp");
              rd.forward(request, response);
              }  
      }catch (ServletException | IOException ex){
             request.setAttribute("Mensaje", "No se pudo Validar Usuario" + ex.getMessage());
            // ex.printStackTrace();
           } 
    }
    
    
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
         HttpSession sesion = request.getSession();
         sesion.setAttribute("usuariosesion", null);
         sesion.invalidate();
         response.sendRedirect("index.jsp");
        
    }
        
}
