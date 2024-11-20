package br.com.mecar.dao;

import br.com.mecar.dao.ConnectionFactory;
import br.com.mecar.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private Connection conexao;
    public UserDao() {
        this.conexao = ConnectionFactory.obterConexao();
    }

    public void registerUser(Usuario usuario) {
        PreparedStatement comandoSql = null;
        try {
            String sql = "INSERT INTO usuario (username, password) VALUES (?, ?)";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setString(1, usuario.getUsername());
            comandoSql.setString(2, usuario.getPassword());

            comandoSql.executeUpdate();
            comandoSql.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario getUsuarioByUsername(String username) {
        Usuario usuario = null;
        PreparedStatement comandoSql = null;
        try {
            String sql = "SELECT * FROM usuario WHERE username = ?";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setString(1, username);
            ResultSet rs = comandoSql.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }

            comandoSql.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public void fecharConexao() {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
