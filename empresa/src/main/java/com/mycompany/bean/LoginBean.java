/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.DAO.DataSource;
import com.mycompany.DAO.UsuarioDao;
import static com.mycompany.bean.LoginBean.getLista;
import com.mycompany.dominio.Usuario;
//import com.mycompany.ucc.Usuarioucc;
//import com.mycompany.util.SessionUtils;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

/**
 *
 * @author Iepiadm
 */
//se crea un bean 
@SessionScoped
//@ViewScoped
@ManagedBean(name = "loginBean")
public class LoginBean {

    private String user;
    private String accion;
    private String password;
    private boolean logeado = false;
    private List<Usuario> usuarios = null;
    private Usuario usuario24;
    private static List<Usuario> lista = new ArrayList();

    public LoginBean() {
        DataSource.getEntityManager();

    }

     public void login(ActionEvent action) {
        this.getUsuario();
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        // Usuario us = new Usuario();
        UsuarioDao ud = new UsuarioDao(null);
        boolean estado = ud.verificacionLogin(getUser(), getPassword());
        if (estado == true) {
//            usuario24 = ud.;
            ud = new UsuarioDao(null);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", getUser());
            System.out.println("WELCOME:  " + user);
            setLogeado(true);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR AL ACCEDER", "Credenciales incorrectos");
            setLogeado(false);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", isLogeado());
        if (isLogeado()) {
            System.out.println("hola");
            context.addCallbackParam("view", "faces/registrar.xhtml");
        } else if (estado == false) {
            context.addCallbackParam("view", "/index.xhtml");
        }

    }
    public List<Usuario> getUsuario() {
        UsuarioDao ud = new UsuarioDao(getUsuario24());
        usuarios = ud.buscarTodos();
        System.out.println("USER:" + usuarios);
        return usuarios;
    }

    public static List<Usuario> getLista() {
        return lista;
    }

    /**
     * @param aLista the lista to set
     */
    public static void setLista(List<Usuario> aLista) {
        lista = aLista;
    }

    /**
     * @return the texto
     */
    public String getUser() {
        return user;
    }

    /**
     * @param texto the texto to set
     */
    public void setUser(String user) {
        this.user = user;
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

    public boolean isLogeado() {
        return logeado;
    }

    /**
     * @param logeado the logeado to set
     */
    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

   

    public String action() {
        logeado = true;
        return "/opcion.xhtml";
    }

//    public String logout() {
//        HttpSession session = SessionUtils.getSession();
//        session.invalidate();
//        return "index";
//    }

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
     * @return the lista
     */
    public void leer(Usuario usuario) {
        setUsuario24(usuario);
        this.setAccion("M");

    }

    /**
     * @return the accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * @return the usuario24
     */
    public Usuario getUsuario24() {
        return usuario24;
    }

    /**
     * @param usuario24 the usuario24 to set
     */
    public void setUsuario24(Usuario usuario24) {
        this.usuario24 = usuario24;
    }

    /**
     * @return the selected
     */
}
