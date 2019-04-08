/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.DAO.DataSource;
import com.mycompany.DAO.UsuarioDao;
import com.mycompany.dominio.Usuario;
import com.mycompany.ucc.Usuarioucc;
import com.mycompany.util.SessionUtils;
//import com.mycompany.ucc.Usuarioucc;
//import com.mycompany.util.SessionUtils;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
//import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.component.api.UIData;
import static org.primefaces.component.focus.Focus.PropertyKeys.context;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Iepiadm
 */
@ViewScoped
//@SessionScoped
@ManagedBean(name = "actionBean")
public class ActionBean {

    private String user;
    private String accion;
    private String password;
    private boolean logeado = false;
    private List<Usuario> usuarios = null;
    private Usuario usuarioBase;
    private static List<Usuario> lista = new ArrayList();
    private UIData datosObtenidos;

    public ActionBean() {
        System.out.println("otro bean");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        LoginBean lv = (LoginBean) session.getAttribute("loginBean");
        user = lv.getUser();
        usuarios = lv.getUsuarios();
        System.out.println("obtener el otro login beam");
    }

    public List<Usuario> getUsuario() {
        UsuarioDao ud = new UsuarioDao(usuarioBase);
        usuarios = ud.buscarTodos();
        System.out.println("Array de Usuarios:" + usuarios);
        return usuarios;
    }

    public void modificar(Usuario us) {

       UsuarioDao udd = new UsuarioDao(null);
        if (udd.editarUsuario(us) == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se edito correctamente", "Se edito correctamente"));
            System.out.println("El Usuario :" + us + "fue editado correctamente");
            getUsuario();
            
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se edito", "No se edito"));
        }

//        Usuario u = (Usuario) datosObtenidos.getRowData();
//        System.out.println("se ha elegido para editar" + u.getUsu_login()+ u.getUsu_password());
//        Usuarioucc ucc = new Usuarioucc();
//        if (ucc.editarUsuario(u) == true) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se edito correctamente"));
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se edito"));
//        }
    }

    public void eliminar(ActionEvent event) {
        Usuario u = (Usuario) datosObtenidos.getRowData();
        System.out.println("se ha seleccionado la tabla: " + u.getUsu_login());
        Usuarioucc ucc = new Usuarioucc();
        if (ucc.deleteUsuario(u) == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "se borro"));
            getUsuario();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "no se borro"));
        }

    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }
    
    public void vinculo(){
          RequestContext context = RequestContext.getCurrentInstance();
          context.addCallbackParam("view", "faces/registrar.xhtml");
    }

    public static List<Usuario> getLista() {
        return lista;
    }

    public static void setLista(List<Usuario> aLista) {
        lista = aLista;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public String action() {
        logeado = true;
        return "/home.xhtml";
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void leer(Usuario usuario) {
        usuarioBase = usuario;
        this.setAccion("M");

    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public UIData getDatosObtenidos() {
        return datosObtenidos;
    }

    public void setDatosObtenidos(UIData datosObtenidos) {
        this.datosObtenidos = datosObtenidos;
    }

}
