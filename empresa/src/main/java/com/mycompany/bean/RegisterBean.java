/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.DAO.DataSource;
import com.mycompany.DAO.UsuarioDao;
import com.mycompany.DAO.Usuario_RolDao;
import com.mycompany.dominio.Usuario;
import com.mycompany.dominio.Usuario_Rol;
import com.mycompany.ucc.Password;
import static com.mycompany.ucc.Password.getMD5;
import com.mycompany.ucc.Usuario_Rolucc;
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
    private String estado = "ACTIVO";
    private String cedula;
    private String nombre;
    private boolean logeado;
    private List<Usuario> usuarios = null;
    private List<String> lista = new ArrayList<String>();
    private String md5;
    //private int rol = 7;

    public RegisterBean() {
//        DataSource.getEntityManager();

    }

    {
        lista.add(0, "ACTIVO");
        lista.add(1, "INACTIVO");
    }

    public void ChangeRadio(ValueChangeEvent event) {
        estado = (String) event.getNewValue();

    }

    public void register(ActionEvent action) {

        ValidarCedulaEcuatoriana vce = new ValidarCedulaEcuatoriana();

        UsuarioDao usuarioDao = new UsuarioDao(usuario);
        //System.out.println("id"+usuarioDao.ultimoId());
        System.out.println("user:" + user);
        System.out.println("pass:" + password);
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        Usuario us = new Usuario();
        md5 = getMD5(getPassword());
        us.setUsu_login(getUser());
        us.setUsu_password(md5);
        System.out.println(getMD5(getPassword()));

        //------------------------------------------
        us.setUsu_nombre(getNombre());
        us.setIdentificacion(getCedula());
        us.setEstado(getEstado());
        if (!vce.verificarCedula(getCedula())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cedula incorrecta", "Cedula incorrecta"));
            System.out.println("mala cedula");

            //boolean igual = usuarioDao.usuariosIguales(getUser());
            //me consulta en la bdd a ver si el dato ingresado no es el mismo que los q fueron ingresados
        } else if (usuarioDao.usuariosIguales(getUser()) == true) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "el username ya existe", "el username ya existe"));

        } else if (usuarioDao.cedulasIguales(getCedula())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La Cédula le pertenece a otra persona", "La Cédula le pertenece a otra persona"));

        } else {
            boolean estado = usuarioDao.guardarUsuario(us);
            if (estado == true) {

                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Los datos ingresados fueron guardados correctamente", user);
                // usuarioDao.getUsuarioById(rol)
                usuarioDao = new UsuarioDao(null);
                //Aqui se asigna la relacion de usuario y el rol  
                Usuario u = usuarioDao.getUser(us.getUsu_login());
                System.out.println("" + u.getUsu_id());
                Usuario_Rol ur = new Usuario_Rol();
           
                ur.setUsrol_id(0);
                ur.setUsrol_idUsuario(u.getUsu_id());
                ur.setUsrol_idRol(7);
               // ur.setUsrol_idRol(4);
                Usuario_RolDao urd = new Usuario_RolDao(ur);
                urd.persist();
               

                //System.out.println("el user es: " + usuarioDao.getUser(getUser()));
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

    public String getMd5() {
        return md5;
    }

    /**
     * @param md5 the md5 to set
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * @return the rol
     */
//    public int getRol() {
//        return rol;
//    }
//
//    /**
//     * @param rol the rol to set
//     */
//    public void setRol(int rol) {
//        this.rol = rol;
//    }
    /**
     * @return the radio
     */
}
