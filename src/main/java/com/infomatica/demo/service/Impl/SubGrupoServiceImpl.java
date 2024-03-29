package com.infomatica.demo.service.Impl;

import com.infomatica.demo.model.entity.Grupo;
import com.infomatica.demo.model.entity.SubGrupo;
import com.infomatica.demo.repository.SubGrupoRepository;
import com.infomatica.demo.service.SubGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubGrupoServiceImpl implements SubGrupoService {

    @Autowired
    private SubGrupoRepository subGrupoRepository;

    @Override
    public List<SubGrupo> findAllSubGrupo() {
        return (List<SubGrupo>) subGrupoRepository.findAll();
    }

    @Override
    public Optional<SubGrupo> findIdSubGrupo(Long id) {
        return subGrupoRepository.findById(id);
    }

    @Override
    public SubGrupo saveSubGrupo(SubGrupo subGrupo) {
        return subGrupoRepository.save(subGrupo);
    }

    @Override
    public Optional<SubGrupo> updateSubGrupo(SubGrupo subGrupo, Long id) {
        Optional<SubGrupo> subGrupoOptional = subGrupoRepository.findById(id);
        SubGrupo subGrupo1 = null;

        if(subGrupoOptional.isPresent()){
            SubGrupo subGrupoDB = subGrupoOptional.orElseThrow();
            subGrupoDB.setNombreSubgrupo(subGrupo.getNombreSubgrupo());
            subGrupoDB.setProductos(subGrupo.getProductos());
            subGrupoDB.setGrupo(subGrupo.getGrupo());
            subGrupo1 = subGrupoRepository.save(subGrupoDB);
        }
        return Optional.ofNullable(subGrupo1);
    }

    @Override
    public void EliminarsubGrupo(Long id) {
        subGrupoRepository.deleteById(id);
    }

    @Override
    public List<SubGrupo> allSubGruposByGrupo(Grupo grupo) {
        return subGrupoRepository.findSubGrupoByGrupo(grupo);
    }


}
