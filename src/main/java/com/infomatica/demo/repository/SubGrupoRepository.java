package com.infomatica.demo.repository;

import com.infomatica.demo.model.entity.Grupo;
import com.infomatica.demo.model.entity.SubGrupo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubGrupoRepository extends CrudRepository<SubGrupo,Long> {

    List<SubGrupo> findSubGrupoByGrupo(Grupo grupo);
}
