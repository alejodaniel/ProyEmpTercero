/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.DAO;

import com.mycompany.dominio.Rol;
import com.mycompany.dominio.Usuario;
import com.mycompany.dominio.Usuario_Rol;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Iepiadm
 */
public class RolDao extends DAOAbstract<Rol> {

    public RolDao(Rol rol) {
        super(rol);
    }

    @Override
    public List<Rol> buscarTodos() {
        Query query = this.getEntityManager().createQuery("Select r from Rol r");
        return query.getResultList();

    }

    public boolean existsRolinUser(Rol rol, Usuario u) {
     
        try {

            Query query = this.getEntityManager().createQuery("Select ur from Usuario_Rol ur where ur.usrol_idRol = " + rol.getRol_codigo() + " and ur.usrol_idUsuario = " + u.getUsu_id());

            Usuario_Rol asignacion = (Usuario_Rol) query.getSingleResult();
            if (asignacion == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
            return false;
        }
    }
}
