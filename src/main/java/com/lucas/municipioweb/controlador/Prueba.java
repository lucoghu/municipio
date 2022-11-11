package com.lucas.municipioweb.controlador;


import com.lucas.municipioweb.modelo.Modelo;
import com.lucas.municipioweb.modelo.ModeloPersona;
import com.lucas.municipioweb.modelo.daos.PersonaDAO;
import com.lucas.municipioweb.modelo.dtos.Persona;
import java.util.Collection;

/**
 *
 * @author Lucas Oliva
 */
public class Prueba {
    //Persona T;

  
   // private Modelo<T> modelo= new ModeloPersona(new PersonaDAO());
   //private Modelo<Persona> modelo= new ModeloPersona(perDAO);
   PersonaDAO perDAO=new PersonaDAO();
   Collection<Persona> personas; //listar() devuelve una lista de Personas
   ModeloPersona mp= new ModeloPersona(perDAO);
   
   public Collection<Persona> listarprueba(){
     return mp.listar();
    }
  
   // personas = (Collection<Persona>)modelo.listar();
public static void main(String[] args) {
    //PersonaDAO perDAO=new PersonaDAO();
    //perDAO.listarPersonas();
    //ModeloPersona mp= new ModeloPersona(perDAO); 
    //mp.listar();
    Prueba pb=new Prueba();
    System.out.println("Lista de Prueba: " + pb.listarprueba());     
    }
  }
