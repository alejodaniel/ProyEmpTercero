/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@SessionScoped
@ManagedBean(name = "accesoBean")
public class AccesoBean {

    private String username;
    private String password;
     private boolean logeado = false;

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.getUsername().equals("senadi") && this.getPassword().equals("senadiadm2019")) {
            context.getExternalContext().getSessionMap().put("user", getUsername());
            setLogeado(true);
            System.out.println("INICIO: "+getUsername());
            try {
                context.getExternalContext().redirect("registrar.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            setLogeado(false);
            //Enviar un mensaje de error en caso de error de inicio de sesión
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales Incorrectos", "Credenciales Incorrectos"));

        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the logeado
     */
    public boolean isLogeado() {
        return logeado;
    }

    /**
     * @param logeado the logeado to set
     */
    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }
}
