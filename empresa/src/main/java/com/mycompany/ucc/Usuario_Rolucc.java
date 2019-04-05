/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ucc;

import com.mycompany.DAO.Usuario_RolDao;
import com.mycompany.dominio.Usuario_Rol;



/**
 *
 * @author Iepiadm
 */
public class Usuario_Rolucc {

    public boolean editarUsuario(Usuario_Rol ur) {
        Usuario_RolDao urd = new Usuario_RolDao(ur);
        try {
            urd.update();
            return true;
        } catch (Exception e) {
            System.out.println("Error al editar: " + e);
            return false;
        }
    }

    public boolean guardarUsuario(Usuario_Rol ur) {
        Usuario_RolDao urd = new Usuario_RolDao(ur);
        try {
            urd.persist();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteUsuario(Usuario_Rol ur) {
      Usuario_RolDao urd = new Usuario_RolDao(ur);
        try {
            urd.remove();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
