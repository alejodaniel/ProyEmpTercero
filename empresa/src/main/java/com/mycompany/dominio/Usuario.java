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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "EmployeSeq",sequenceName = "EMPLOYEES_SEQ",initialValue = 1,allocationSize = 10 )
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "EmployeSeq")
    @Column(name = "usu_id")
    private int usu_id;
    @Column(name = "usu_nombre", nullable = false, columnDefinition = "varchar(30)")
    private String usu_nombre;
    @Column(name = "usu_login", nullable = false, columnDefinition = "varchar(30)")
    private String usu_login;
    @Column(name = "usu_password", nullable = false, columnDefinition = "varchar(50)")
    private String usu_password;
    @Column(name = "estado", nullable = false, columnDefinition = "varchar(15)")
    private String estado;
    @Column(name = "identificacion", nullable = false, columnDefinition = "varchar(15)")
    private String identificacion;

    /**
     * @return the usu_id
     */
    public int getUsu_id() {
        return usu_id;
    }

    /**
     * @param usu_id the usu_id to set
     */
    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }

    /**
     * @return the usu_nombre
     */
    public String getUsu_nombre() {
        return usu_nombre;
    }

    /**
     * @param usu_nombre the usu_nombre to set
     */
    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    /**
     * @return the usu_login
     */
    public String getUsu_login() {
        return usu_login;
    }

    /**
     * @param usu_login the usu_login to set
     */
    public void setUsu_login(String usu_login) {
        this.usu_login = usu_login;
    }

    /**
     * @return the usu_password
     */
    public String getUsu_password() {
        return usu_password;
    }

    /**
     * @param usu_password the usu_password to set
     */
    public void setUsu_password(String usu_password) {
        this.usu_password = usu_password;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

}
