package com.lucas.municipioweb.controlador;

import com.lucas.municipioweb.modelo.ModeloPersona;
import com.lucas.municipioweb.modelo.daos.PersonaDAO;
import com.lucas.municipioweb.modelo.Modelo;
import com.lucas.municipioweb.modelo.ModeloReclamo;
import com.lucas.municipioweb.modelo.daos.ReclamoDAO;
import com.lucas.municipioweb.modelo.dtos.Categoria;
import com.lucas.municipioweb.modelo.dtos.Persona;
import com.lucas.municipioweb.modelo.dtos.Reclamo;
import com.lucas.municipioweb.modelo.dtos.TipoCategoria;
import java.time.LocalDate;
import java.util.Date;


public class prueba {
    
   //Modelo modelo = new ModeloPersona(new PersonaDAO());
  // int creada= modelo.crear(new Persona(dni, nombre, apellido, mail,telef,usuario,clave,0 ));
   
    public static void main(String[] args) {
        PersonaDAO per=new PersonaDAO();
        Modelo modelo = new ModeloPersona(new PersonaDAO());
       /* per.listarPersonas();
        //per.buscarCadena("User1");
        //int creada= modelo.crear(new Persona(3022, "septimo", "contri5", "sin5@mail", 155, "USER5", "f5", 0));
        //System.out.println("Peronas creadas"+creada);
        // System.out.println("Persona eliminada "+per.eliminarPersona(111));
        for (Persona p : per.listarPersonas()) {
            System.out.println(p.getDni()+"  "+p.getIdpersona());
        }
        //System.out.println("Persona eliminada "+per.eliminarPersona(112));
        System.out.println(per.buscarKey(105).getIdpersona());
        System.out.println("persona eliminada: "+modelo.eliminar(110));
        */
        
       ReclamoDAO recla=new ReclamoDAO();
       Modelo modelor = new ModeloReclamo(new ReclamoDAO());
       LocalDate fe= LocalDate.now(); // muestra este formato: yyyy-MM-dd
      
      // SimpleDateFormat formateo= new SimpleDateFormat("yyyy-MM-dd");
       Date fechacreacion=java.sql.Date.valueOf(fe);
       Persona p=new Persona(108,3022, "septimo", "contri5", "sin5@mail", 155, "USER5", "f5", 0);
       int insertado=modelor.crear(new Reclamo(fechacreacion,null,"cualquiera4",new Categoria(TipoCategoria.valueOf("limpieza".toUpperCase())),"comentario4",p));
       Categoria cat1=new Categoria(TipoCategoria.valueOf("arbolado".toUpperCase()));
  
       //System.out.println("Categoria: "+ cat1.getIdcategoria());
       System.out.println("Categoria: "+ TipoCategoria.valueOf("limpieza".toUpperCase()));
       System.out.println("CATEGORIA: "+ cat1.getCategoria());
      // int idcat=recla.obtenerIdCategoria(new Reclamo(fechacreacion,null,"cualquiera4",new Categoria(TipoCategoria.valueOf("limpieza".toUpperCase())),"comentario4",p));
       // System.out.println("id categoria: "+idcat);
    } 
    
}
