/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.DAO.DataSource;
import com.mycompany.DAO.UsuarioDao;
import com.mycompany.dominio.Usuario;
import com.mycompany.ucc.ValidarCedulaEcuatoriana;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Iepiadm
 */
//se crea un bean 
@ViewScoped
@ManagedBean(name = "registerBean")
public class RegisterBean {

    private Usuario usuario;
    private String user;
    private String password;
    private String estado;
    private String cedula;
    private String nombre;
    private boolean logeado;
    private List<Usuario> usuarios = null;
    private List<String> lista = new ArrayList<String>();
    private String radio = "Seleccione una opcion";

    public RegisterBean() {
//        DataSource.getEntityManager();
        lista.add(0,"activo");
        lista.add(1,"inactivo");
    }
    public void seleccionarRadio(ValueChangeEvent event){
        setRadio((String) event.getNewValue()); // captura el nuevo valor y lo pasas a una variable 
        System.out.println("valor es:"+event);
        
    }

    public void register(ActionEvent action) {
        ValidarCedulaEcuatoriana vce = new ValidarCedulaEcuatoriana();
        UsuarioDao usuarioDao = new UsuarioDao(usuario);
        System.out.println("user:" + user);
        System.out.println("pass:" + password);
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        Usuario us = new Usuario();
        us.setUsu_login(getUser());
        us.setUsu_password(getPassword());
        us.setUsu_nombre(getNombre());
        us.setIdentificacion(getCedula());
        us.setEstado(getEstado());
        if (!vce.verificarCedula(getCedula())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cedula incorrecta", "Cedula incorrecta"));
            System.out.println("mala cedula");
        } else {
            boolean estado = usuarioDao.guardarUsuario(us);
            if (estado == true) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Usuario y contraseña fueron guardados correctamente", user);
                setLogeado(true);
                DataSource dt = new DataSource();
                dt.getEntityManager();

            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se guardaron los datos ingresados", null);
                setLogeado(false);
            }
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addCallbackParam("estaLogeado", isLogeado());
            if (isLogeado()) {
                context.addCallbackParam("view", "index.xhtml");
            } else if (estado == false) {
                context.addCallbackParam("view", "home.xhtml");
            }
        }

    }

    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogeado() {
        return logeado;
    }

    /**
     * @param logeado the logeado to set
     */
    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public void newUser(ActionEvent action) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;

        UsuarioDao usuarioDao = new UsuarioDao(usuario);
        if (usuario != null) {
            usuario.setUsu_login(user);
            usuario.setUsu_password(password);
            boolean estado = usuarioDao.editarUsuario(usuario);
            if (estado == true) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se edito correctamente", user);
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se editaron los datos", null);
            }
        } else {
            Usuario us = new Usuario();
            us.setUsu_login(getUser());
            us.setUsu_password(getPassword());

            boolean estado = usuarioDao.guardarUsuario(us);
            if (estado == true) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Usuario y contraseña fueron guardados correctamente", user);
                setLogeado(true);
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se guardaron los datos ingresados", null);
                setLogeado(false);
            }
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addCallbackParam("estaLogeado", isLogeado());
            if (isLogeado()) {
                context.addCallbackParam("view", "home.xhtml");
            } else if (estado == false) {
                context.addCallbackParam("view", "index.xhtml");
            }

        }

    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the lista
     */
    public List<String> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<String> lista) {
        this.lista = lista;
    }

    /**
     * @return the radio
     */
    public String getRadio() {
        return radio;
    }

    /**
     * @param radio the radio to set
     */
    public void setRadio(String radio) {
        this.radio = radio;
    }
}
