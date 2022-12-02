package com.lucas.municipioweb.modelo.dtos;

/**
 *
 * @author Lucas Oliva
 */
public class Persona {

    private int idpersona;
    private int dni;
    private String nombre;
    private String apellido;
    private String mail;
    private int telef;
    private String user;
    private String clave;
    private int admin; //por defecto todas las personas son contribuyentes(0), es decir no administradores
                           // 0 para contribuyente, 1 para administrador


    public Persona(int idpersona, int dni,String nombre, String apellido, String mail, int telef, String user, String clave, int admin) {
        this.idpersona = idpersona;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telef = telef;
        this.user = user;
        this.clave = clave;
        this.admin = admin;
    }

    public Persona(int dni, String nombre, String apellido, String mail, int telef, String user, String clave, int admin) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telef = telef;
        this.user = user;
        this.clave = clave;
        this.admin = admin;
    }

    public Persona(int idpersona, int dni, String nombre, String apellido, String mail, int telef) {
        this.idpersona = idpersona;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telef = telef;
    }

    public Persona() {
    }

    
    

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelef() {
        return telef;
    }

    public void setTelef(int telef) {
        this.telef = telef;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }  
    
}
