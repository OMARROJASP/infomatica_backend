package com.infomatica.demo.service;

import com.infomatica.demo.model.entity.Grupo;
import com.infomatica.demo.model.entity.Producto;
import com.infomatica.demo.model.entity.SubGrupo;

import java.util.List;
import java.util.Optional;

public interface GrupoService {

    List<Grupo> findAllGrupo();

    Optional<Grupo> findIdGrupo(Long id);
    Grupo saveGrupo(Grupo grupo);

    Optional<Grupo> updateGrupo(Grupo grupo, Long id);
    void EliminarGrupo(Long id);


}
