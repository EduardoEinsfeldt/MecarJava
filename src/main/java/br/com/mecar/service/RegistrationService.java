package br.com.mecar.service;

import br.com.mecar.dao.UserDao;
import br.com.mecar.dto.UsuarioRequestDto;
import br.com.mecar.model.Usuario;

public class RegistrationService {

    private UserDao userDao = new UserDao();

    public String register(UsuarioRequestDto usuarioDto) {
        Usuario usuario = usuarioDto.convert(usuarioDto);

        if (userDao.getUsuarioByUsername(usuario.getUsername()) != null) {
            return "Username already exists!";
        }

        userDao.registerUser(usuario);
        return "Registration successful!";
    }

    public void closeConnection() {
        userDao.fecharConexao();
    }
}