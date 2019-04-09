/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.DAO;

import com.mycompany.dominio.Rol;
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

}
