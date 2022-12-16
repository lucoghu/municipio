package com.lucas.municipioweb.modelo;

import com.lucas.municipioweb.modelo.daos.ReclamoDAO;
import com.lucas.municipioweb.modelo.dtos.Reclamo;
import java.util.Collection;

/**
 *
 * @author Lucas Oliva
 */
public class ModeloReclamo implements Modelo<Reclamo> {
     private ReclamoDAO reclamoDAO;
   

    public ModeloReclamo(ReclamoDAO reclamoDAO) {
        this.reclamoDAO = reclamoDAO;
    }

     @Override
    public Collection<Reclamo> listar() {
        return reclamoDAO.listarReclamos();
    }

    
    @Override
    public int crear(Reclamo r) {
           return reclamoDAO.crearReclamo(r);
     }
   
    
    @Override
    public int actualizar(Reclamo r) {
           return reclamoDAO.actualizarReclamo(r); 
    }
           
    @Override
    public Reclamo buscarCadena(String dato){
          return reclamoDAO.buscarCadena(dato);
    }
    
    
    @Override
    public Reclamo buscarKey(int dato){
          return reclamoDAO.buscarKey(dato);
    }
    
     @Override
    public int eliminar(int dato) {
           return reclamoDAO.eliminarReclamo(dato);
     }
    
    @Override
    public Collection<Reclamo> buscarEntero(int dato){
          return reclamoDAO.buscarEntero(dato);
    }
}
