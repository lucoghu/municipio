package com.lucas.municipioweb.modelo.dtos;

//import java.time.LocalDate;

import java.util.Date;


/**
 *
 * @author Lucas Oliva
 */
public class Reclamo {
    
    private int idreclamo;
    //private LocalDate fechacreacion;
    private Date fechacreacion;
   // private LocalDate fecharesolucion;
    private Date fecharesolucion;  //LocalDate.now() fecha actual
    private String domicilio;
     private Categoria categoria;
     private String comentario;
    private Persona persona;
   

    public Reclamo(int idreclamo, Date fechacreacion, Date fecharesolucion, String domicilio, Categoria categoria, String comentario, Persona persona) {
        this.idreclamo = idreclamo;
        this.fechacreacion = fechacreacion;
        this.fecharesolucion = fecharesolucion;
        this.domicilio = domicilio;
        this.categoria = categoria;
         this.comentario = comentario;
        this.persona = persona;
     
    }

    public Reclamo(Date fechacreacion, Date fecharesolucion, String domicilio, Categoria categoria, String comentario, Persona persona) {
        this.fechacreacion = fechacreacion;
        this.fecharesolucion = fecharesolucion;
        this.domicilio = domicilio;
        this.categoria = categoria;
        this.comentario = comentario;
        this.persona = persona;
    }
    
    
    
     public int getIdreclamo() {
        return idreclamo;
    }

    public void setIdreclamo(int idreclamo) {
        this.idreclamo = idreclamo;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFecharesolucion() {
        return fecharesolucion;
    }

    public void setFecharesolucion(Date fecharesolucion) {
        this.fecharesolucion = fecharesolucion;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    
  

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
     
}
