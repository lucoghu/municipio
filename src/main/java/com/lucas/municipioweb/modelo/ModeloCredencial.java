package com.lucas.municipioweb.modelo;

import com.lucas.municipioweb.modelo.daos.CredencialDAO;
import com.lucas.municipioweb.modelo.dtos.Credencial;
import java.util.Collection;

/**
 *
 * @author Lucas Oliva
 */
public class ModeloCredencial implements Modelo<Credencial>{
    
     private CredencialDAO credencialDAO;
   

    public ModeloCredencial(CredencialDAO credencialDAO) {
        this.credencialDAO = credencialDAO;
    }

    @Override
    public Collection<Credencial> listar() {
        
       //Collection<Persona> personas=personaDAO.listarPersonas();  //para prueba
        // System.out.println("com.lucas.municipioweb.modelo.ModeloPersona.listar()");//para prueba
    
        return credencialDAO.listarCredenciales();
 
    }

    
    @Override
    public int crear(Credencial c) {
           return credencialDAO.crearCredencial(c);
     }
  /* 
      @Override
    public Persona buscar(Persona p) {
           return personaDAO.buscar(p);
    
 */  
    /* @Override
    public Reclamo buscar(<?> dato){
          return reclamoDAO.buscar(dato);
    }*/
    
    @Override
    public int actualizar(Credencial c) {
           return credencialDAO.actualizarCredencial(c);    
    }
    
    @Override
    public Credencial buscarCadena(String dato){
         return credencialDAO.buscarCadena(dato);
    }
      
    @Override
    public Credencial buscarEntero(int dato){
          return credencialDAO.buscarEntero(dato);
    }
          
     @Override
    public int eliminar(int dato) {
           return credencialDAO.eliminarCredencial(dato);    
    }
    
     // para verficar funcionamiento
  /* public static void main(String[] args) {
        ModeloPersona per=new ModeloPersona(new PersonaDAO());
        per.listar();
    }*/

}
