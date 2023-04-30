package org.balsani.entities.dao;

import org.balsani.entities.Usuario;

import org.balsani.exceptions.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UsuarioDao {

    private final Connection conn;

    public UsuarioDao (Connection connection) {
        this.conn = connection;
    }

    public void insert(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,usuario.getNome());
            preparedStatement.setString(2,usuario.getEmail());

            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbException("Falha ao cadastrar usuário");
        }
    }

    public Usuario findById(Long id) {
        Usuario usuario = null;

        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nome = resultSet.getString(2);
                String email = resultSet.getString(3);

               usuario = new Usuario(nome,email);

            }
            preparedStatement.close();
            resultSet.close();
            conn.close();

        } catch (SQLException e) {
            throw new DbException("Erro ao encontrar usuário");
        }
        return usuario;

    }

    public Set<Usuario> findAll() {
        Set<Usuario> usuarios = new HashSet<>();
        String sql = "SELECT * FROM usuarios";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String nome = resultSet.getString(2);
                String email = resultSet.getString(3);
                Usuario usuario = new Usuario(id,nome, email);
                usuarios.add(usuario);
            }
            preparedStatement.close();
            resultSet.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return usuarios;
    }

    public void update(Long id, String nome, String email) {
        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, email);
            preparedStatement.setLong(3, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbException("Falha ao atualizar usuário");
        }
    }




    public void delete(Long id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbException("Falha ao deletar usuário");
        }
    }

}
