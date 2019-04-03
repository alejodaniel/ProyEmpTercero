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
@Table(name = "usuario_rol")
public class Usuario_Rol implements Serializable {

    @Id
    @Column(name = "usrol_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int usrol_id;
    @Column(name = "usrol_idUsuario", nullable = false, columnDefinition = "int(11)")
    private int usrol_idUsuario;
    @Column(name = "usrol_idRol",nullable = false,columnDefinition = "int(11)")
    private int usrol_idRol;

    /**
     * @return the usrol_id
     */
    public int getUsrol_id() {
        return usrol_id;
    }

    /**
     * @param usrol_id the usrol_id to set
     */
    public void setUsrol_id(int usrol_id) {
        this.usrol_id = usrol_id;
    }

    /**
     * @return the usrol_idUsuario
     */
    public int getUsrol_idUsuario() {
        return usrol_idUsuario;
    }

    /**
     * @param usrol_idUsuario the usrol_idUsuario to set
     */
    public void setUsrol_idUsuario(int usrol_idUsuario) {
        this.usrol_idUsuario = usrol_idUsuario;
    }

    /**
     * @return the usrol_idRol
     */
    public int getUsrol_idRol() {
        return usrol_idRol;
    }

    /**
     * @param usrol_idRol the usrol_idRol to set
     */
    public void setUsrol_idRol(int usrol_idRol) {
        this.usrol_idRol = usrol_idRol;
    }

}
