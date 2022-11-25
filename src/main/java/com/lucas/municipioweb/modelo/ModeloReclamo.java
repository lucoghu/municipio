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
    
     /* @Override
    public Reclamo buscar(<?> dato){
          return reclamoDAO.buscar(dato);
    }*/
    
    @Override
    public int actualizar(Reclamo r) {
           return reclamoDAO.actualizarReclamo(r); 
    }
           
    @Override
    public Reclamo buscarCadena(String dato){
          return reclamoDAO.buscarCadena(dato);
    }
    
   @Override
    public Reclamo buscarEntero(int dato){
          return reclamoDAO.buscarEntero(dato);
    }
    
     @Override
    public int eliminar(int dato) {
           return reclamoDAO.eliminarReclamo(dato);
     }
}
