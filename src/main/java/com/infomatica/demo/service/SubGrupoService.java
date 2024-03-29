package com.infomatica.demo.service;

import com.infomatica.demo.model.entity.Grupo;
import com.infomatica.demo.model.entity.SubGrupo;

import java.util.List;
import java.util.Optional;

public interface SubGrupoService {

    List<SubGrupo> findAllSubGrupo();

    Optional<SubGrupo> findIdSubGrupo(Long id);
    SubGrupo saveSubGrupo(SubGrupo subGrupo);

    Optional<SubGrupo> updateSubGrupo(SubGrupo subGrupo, Long id);
    void EliminarsubGrupo(Long id);

    List<SubGrupo> allSubGruposByGrupo(Grupo grupo);
}

