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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int usrol_id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int usrol_idusuario;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int usrol_idrol;

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
     * @return the usrol_idusuario
     */
    public int getUsrol_idusuario() {
        return usrol_idusuario;
    }

    /**
     * @param usrol_idusuario the usrol_idusuario to set
     */
    public void setUsrol_idusuario(int usrol_idusuario) {
        this.usrol_idusuario = usrol_idusuario;
    }

    /**
     * @return the usrol_idrol
     */
    public int getUsrol_idrol() {
        return usrol_idrol;
    }

    /**
     * @param usrol_idrol the usrol_idrol to set
     */
    public void setUsrol_idrol(int usrol_idrol) {
        this.usrol_idrol = usrol_idrol;
    }

}
