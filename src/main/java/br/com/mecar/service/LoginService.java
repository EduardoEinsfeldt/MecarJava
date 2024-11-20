package br.com.mecar.service;

import br.com.mecar.dao.UserDao;
import br.com.mecar.dto.UsuarioRequestDto;
import br.com.mecar.model.Usuario;

public class LoginService {

    private UserDao userDao = new UserDao();

    public boolean authenticate(UsuarioRequestDto usuarioDto) {
        Usuario usuario = userDao.getUsuarioByUsername(usuarioDto.getUsername());
        if (usuario != null) {
            return usuario.getPassword().equals(usuarioDto.getPassword());
        }
        return false;
    }

    public void closeConnection() {
        userDao.fecharConexao();
    }
}