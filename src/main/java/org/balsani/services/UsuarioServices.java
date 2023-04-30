package org.balsani.services;

import org.balsani.db.ConnectionFactory;
import org.balsani.entities.Usuario;
import org.balsani.entities.dao.UsuarioDao;

import org.balsani.exceptions.DbException;

import java.sql.Connection;

import java.util.Set;

public class UsuarioServices {

    private ConnectionFactory connection;
    public UsuarioServices() {
        this.connection = new ConnectionFactory();
    }

    public void cadastrarUsuario(Usuario usuario) {
        Connection conn = connection.getConnection();
        new UsuarioDao(conn).insert(usuario);
    }

    public Set<Usuario> listarUsuarios() {
        Connection conn = connection.getConnection();
        return new UsuarioDao(conn).findAll();
    }

    public Usuario buscarUsuario(Long id) {
        Connection conn = connection.getConnection();
        Usuario usuario = new UsuarioDao(conn).findById(id);
        if (usuario != null) {
            return usuario;
        } else {
            throw new DbException("Usuário não encontrado");
        }
    }

    public void apagarUsuario(Long id) {
        buscarUsuario(id);
        Connection conn = connection.getConnection();
        new UsuarioDao(conn).delete(id);
    }






}
