package com.mycompany.bean;

import com.mycompany.DAO.RolDao;
import com.mycompany.DAO.UsuarioDao;
import com.mycompany.dominio.Rol;
import com.mycompany.dominio.Usuario;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.component.api.UIData;
import org.primefaces.context.RequestContext;

@ViewScoped
@ManagedBean(name = "asignacionBean")

public class AsignacionBean {

    private Usuario usuario;
    private String password;

    private List<Rol> roles = null;
    private UIData obtenerDatos;
    private Rol rol;

    private LoginBean loginBean;

    public AsignacionBean() {

        loginBean = getLoginBean();
        usuario = loginBean.getUsuarioFlotante();

    }

    public void pass() {
        UsuarioDao ud = new UsuarioDao(usuario);
        boolean estado = ud.verificacionPassword(getPassword());
        if (estado == true) {
            System.out.println("correcto");

        } else {
            System.out.println("incorrecto");
        }
    }

    public LoginBean getLoginBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        LoginBean lb = (LoginBean) session.getAttribute("loginBean");
        return lb;
    }

    public void obtenerUserAsignaciones(ActionEvent event) {
        Usuario u = (Usuario) obtenerDatos.getRowData();
        loginBean.setUsuario24(getUsuario());
        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("view", "faces/asignaciones.xhtml");

    }

    public UIData getObtenerDatos() {
        return obtenerDatos;
    }

    /**
     * @param obtenerDatos the obtenerDatos to set
     */
    public void setObtenerDatos(UIData obtenerDatos) {
        this.obtenerDatos = obtenerDatos;
    }

    public void pertenece() {

    }

    public List<Rol> getRolesEnCheckBox() {
        RolDao rd = new RolDao(rol);
        roles = rd.buscarTodos();

        for (int i = 0; i < roles.size(); i++) {
            if (rd.existsRolinUser(roles.get(i), usuario)) {
                roles.get(i).setAsignado(true);
            }
        }
        System.out.println("Array de roles: " + roles);
        return roles;
    }

    public List<Rol> GuardarDatosCheckBox() {
        RolDao rd = new RolDao(rol);
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).isAsignado() == true) {
                if (rd.existsRolinUser(roles.get(i), usuario)) {

                } else {
                    roles.get(i).getRol_codigo();

                }
            }
        }
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
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

}
