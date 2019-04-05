/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.DAO;
import com.mycompany.dominio.Usuario_Rol;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Iepiadm
 */
public class Usuario_RolDao  extends DAOAbstract<Usuario_Rol>{

    public Usuario_RolDao(Usuario_Rol usuario_Rol) {
        super(usuario_Rol);
    }

    @Override
    public List<Usuario_Rol> buscarTodos() {
         Query query = this.getEntityManager().createQuery("Select ur from Usuario_Rol ur");
        return query.getResultList();
    }

  
}
