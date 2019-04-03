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

    /**
     * @return the rol_codigo
     */
    public int getRol_codigo() {
        return rol_codigo;
    }

    /**
     * @param rol_codigo the rol_codigo to set
     */
    public void setRol_codigo(int rol_codigo) {
        this.rol_codigo = rol_codigo;
    }

    /**
     * @return the rol_nombre
     */
    public String getRol_nombre() {
        return rol_nombre;
    }

    /**
     * @param rol_nombre the rol_nombre to set
     */
    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }

    /**
     * @return the rol_id
     */
    public String getRol_id() {
        return rol_id;
    }

    /**
     * @param rol_id the rol_id to set
     */
    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

}
