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

    private List<Rol> roles = null;
    private UIData obtenerDatos;
    private Rol rol;

    private LoginBean loginBean;

    public AsignacionBean() {

        loginBean = getLoginBean();
        usuario = loginBean.getUsuarioFlotante();

    }

    public LoginBean getLoginBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        LoginBean lb = (LoginBean) session.getAttribute("loginBean");
        return lb;
    }

    public void obtenerUserAsignaciones(ActionEvent event) {
        Usuario u = (Usuario) obtenerDatos.getRowData();
//        System.out.println("se ha seleccionado la tabla para asignar rol: " + u.getUsu_login());
        //LoginBean lb = new LoginBean();
        loginBean.setUsuario24(getUsuario());
        RequestContext context = RequestContext.getCurrentInstance();
        //  System.out.println("paso al siguiente nivel" + context);
        context.addCallbackParam("view", "faces/asignaciones.xhtml");
        // System.out.println("fin de llegada" + context);

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

    public List<Rol> getRoles() {
        RolDao rd = new RolDao(rol);
        roles = rd.buscarTodos();

        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).pertenece(usuario)) {
                roles.get(i).setAsignado(true);
            }
        }

        System.out.println("Array de roles: " + roles);
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

}
