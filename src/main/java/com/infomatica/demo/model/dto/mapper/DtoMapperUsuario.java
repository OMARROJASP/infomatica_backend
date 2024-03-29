package com.infomatica.demo.model.dto.mapper;

import com.infomatica.demo.model.dto.DtoUsuario;
import com.infomatica.demo.model.entity.Usuario;

public class DtoMapperUsuario {

    private Usuario usuario;

    public DtoMapperUsuario() {
    }

    public static DtoMapperUsuario builder(){
        return new DtoMapperUsuario();
    }

    public DtoMapperUsuario setUsuario(Usuario usuario){
        this.usuario = usuario;
        return this;
    }

    public DtoUsuario build(){
        if(usuario == null){
            throw new RuntimeException("DEBE PASAR EL ENTITY USUARIO!");
        }
        return new DtoUsuario(this.usuario.getId(),usuario.getNombre());
    }

}
