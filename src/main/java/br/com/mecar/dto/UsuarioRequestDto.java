package br.com.mecar.dto;

import br.com.mecar.model.Usuario;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioRequestDto {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsuarioRequestDto convertToDto(Usuario usuario) {
        UsuarioRequestDto usuarioRequestDto = new UsuarioRequestDto();
        usuarioRequestDto.setId(usuario.getId());
        usuarioRequestDto.setUsername(usuario.getUsername());
        usuarioRequestDto.setPassword(usuario.getPassword());
        return usuarioRequestDto;
    }

    public Usuario convert(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioRequestDto.getId());
        usuario.setUsername(usuarioRequestDto.getUsername());
        usuario.setPassword(usuarioRequestDto.getPassword());
        return usuario;
    }
}