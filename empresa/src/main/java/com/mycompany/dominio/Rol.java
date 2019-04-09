/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Iepiadm
 */
@Entity
@Table(name = "rol")
public class Rol implements Serializable {

    @Id
    @Column(name = "rol_codigo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rol_codigo;
    @Column(name = "rol_nombre", nullable = false, columnDefinition = "varchar(50)")
    private String rol_nombre;
    @Column(name = "rol_id", nullable = false, columnDefinition = "varchar(50)")
    private String rol_id;
    @Column(name = "descripcion", nullable = false, columnDefinition = "varchar(250)")
    private String descripcion;
    @Column(name = "link", nullable = false, columnDefinition = "varchar(100)")
    private String link;

    @Transient  
    private boolean asignado;
    
    
    public boolean pertenece(Usuario usuario){
        return false;
        
    }
    
    public int getRol_codigo() {
        return rol_codigo;
    }

    public void setRol_codigo(int rol_codigo) {
        this.rol_codigo = rol_codigo;
    }

    
    public String getRol_nombre() {
        return rol_nombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }

   
    public String getRol_id() {
        return rol_id;
    }

    
    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    
    public String getDescripcion() {
        return descripcion;
    }

    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    public String getLink() {
        return link;
    }

   
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the asignado
     */
    public boolean isAsignado() {
        return asignado;
    }

    /**
     * @param asignado the asignado to set
     */
    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }

}
