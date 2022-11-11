package com.lucas.municipioweb.controlador;

import com.lucas.municipioweb.modelo.Modelo;
import com.lucas.municipioweb.modelo.ModeloPersona;
import com.lucas.municipioweb.modelo.daos.PersonaDAO;
import com.lucas.municipioweb.modelo.dtos.Persona;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Collection;


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
   
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PersonaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PersonaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //int id;
        String accion = request.getParameter("accion");  //lectura de la accion
        if(accion == null || accion.isEmpty()){
            accion="listar";
            
          switch (accion) { 
            case "listar":               //ListaDePersonas.jsp
                 listadoPersonas(request, response);
                 break;
           // case "nuevo":                 
             //   RequestDispatcher  rdd;
            //    rdd = request.getRequestDispatcher("pages/registro.html");
             //     break;
            case "insertar":                 //registro.jsp
                  crearPersona(request, response);
                  break;
            }
        }  
    }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //processRequest(request, response);
   /*    String accion = request.getParameter("accion");
        if("validar".equals(accion))
           validarpersona(request, response); */
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

    
    
    private void crearPersona(HttpServletRequest request, HttpServletResponse response)  
     throws ServletException, IOException  {
     int creada=0;
    // Persona p;
       
       String nombre=request.getParameter("nombre");
       String apellido=request.getParameter("apellido");
       int dni=Integer.parseInt(request.getParameter("dni"));
       String mail=request.getParameter("mail"); 
       int telef=Integer.parseInt(request.getParameter("telefono")); 
       String usuario=request.getParameter("usuario");
       String clave=request.getParameter("password");
       //por defecto todas las personas no son administradores
       creada= modelo.crear(new Persona(dni, nombre, apellido, mail,telef , usuario, clave, false));
       
      // RequestDispatcher rd;
       if(creada!=0){
       //redirecciono el servlet mediante el objeto rd hacia el login.html
       response.sendRedirect("/pages/login.html");
        // rd=request.getRequestDispatcher("pages/login.html");
         //rd.forward(request, response); 
       } else {
           response.sendRedirect("pages/registro.jsp");
           //rd=request.getRequestDispatcher("pages/registro.html"); //ver como mostrar erro de Registro
           }
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
    }
    */
    //*************para verificar************
    public void mostrar(){
    Collection<Persona>  personas;
     personas = modelo.listar();
    }
    public static void main(String[] args) {
       
      PersonaServlet p = new PersonaServlet();
       p.mostrar();
        
    }
   
    
}
