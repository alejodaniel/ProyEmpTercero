package com.mycompany.bean;

import com.mycompany.DAO.RolDao;
import com.mycompany.DAO.UsuarioDao;
import com.mycompany.DAO.Usuario_RolDao;
import com.mycompany.dominio.Rol;
import com.mycompany.dominio.Usuario;
import com.mycompany.dominio.Usuario_Rol;
import java.util.List;
import javax.faces.application.FacesMessage;
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
    private String prueba;
    private String newPassword;
    private String rNewPassword;

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

    public void cambiarPassword(ActionEvent event) {
        if (password != null && newPassword != null && rNewPassword != null) {
            System.out.println("inicio");
            System.out.println("ggggg " + newPassword.equals(rNewPassword));
            if (newPassword.equals(rNewPassword)) {
                System.out.println("prueba");
                if (usuario.getUsu_password().equals(password)) {
                    usuario.setUsu_password(newPassword);
                    UsuarioDao user = new UsuarioDao(usuario);
                    user.update();
                } else {
                    System.out.println("fallo");
                }
            } else {
                System.out.println("pruebafin");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrectos", "Incorrectos"));

            }

        } else {
            System.out.println("iniciofin");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales Incorrectos", "Credenciales Incorrectos"));

        }
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
        return roles;
    }

    public List<Rol> GuardarDatosCheckBox(ActionEvent event) {
        System.out.println("PRUEBA");
        RolDao rd = new RolDao(rol);
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).isAsignado() == true) {
                if (rd.existsRolinUser(roles.get(i), usuario)) {

                } else {
                    Usuario_Rol usr = new Usuario_Rol();
                    usr.getUsrol_id();
                    usr.setUsrol_idRol(i);
                    usr.setUsrol_idUsuario(usuario.getUsu_id());
                    Usuario_RolDao urd = new Usuario_RolDao(usr);
                    urd.persist();
                }
            } else {
                if (rd.existsRolinUser(roles.get(i), usuario)) {
                    Usuario_Rol usr = new Usuario_Rol();
                    usr.getUsrol_id();
                    usr.getUsrol_idUsuario();
                    usr.getUsrol_idRol();
                    Usuario_RolDao urd = new Usuario_RolDao(usr);
                    urd.remove();

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

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the rNewPassword
     */
    public String getrNewPassword() {
        return rNewPassword;
    }

    /**
     * @param rNewPassword the rNewPassword to set
     */
    public void setrNewPassword(String rNewPassword) {
        this.rNewPassword = rNewPassword;
    }

    /**
     * @return the prueba
     */
    public String getPrueba() {
        return prueba;
    }

    /**
     * @param prueba the prueba to set
     */
    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

}
