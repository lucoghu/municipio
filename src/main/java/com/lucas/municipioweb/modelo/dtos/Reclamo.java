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
    private int idpersona;
    private int idcategoria;
    private String comentario;

    public Reclamo(int idreclamo, Date fechacreacion, Date fecharesolucion, String domicilio, int idpersona, int idcategoria, String comentario) {
        this.idreclamo = idreclamo;
        this.fechacreacion = fechacreacion;
        this.fecharesolucion = fecharesolucion;
        this.domicilio = domicilio;
        this.idpersona = idpersona;
        this.idcategoria = idcategoria;
        this.comentario = comentario;
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

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
     
     

}
