package com.infomatica.demo.service;

import com.infomatica.demo.model.dto.DtoUsuario;
import com.infomatica.demo.model.entity.Grupo;
import com.infomatica.demo.model.entity.Usuario;
import com.infomatica.demo.model.request.UsuarioRequest;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<DtoUsuario> findAllUsuario();

    Optional<DtoUsuario> findIdUsuario(Long id);
    DtoUsuario saveUsuario(Usuario usuario);

    Optional<DtoUsuario> updateUsuario(UsuarioRequest usuario, Long id);
    void EliminarGrupo(Long id);
}
