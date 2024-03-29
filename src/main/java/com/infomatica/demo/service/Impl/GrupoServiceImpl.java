package com.infomatica.demo.service.Impl;

import com.infomatica.demo.model.entity.Grupo;
import com.infomatica.demo.model.entity.SubGrupo;
import com.infomatica.demo.repository.GrupoRepository;
import com.infomatica.demo.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;


    @Override
    public List<Grupo> findAllGrupo() {
        return (List<Grupo>) grupoRepository.findAll();
    }

    @Override
    public Optional<Grupo> findIdGrupo(Long id) {
        return grupoRepository.findById(id);
    }

    @Override
    public Grupo saveGrupo(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Override
    public Optional<Grupo> updateGrupo(Grupo grupo, Long id) {
       Optional<Grupo> grupoOptional = grupoRepository.findById(id);
       Grupo grupoAux = null;

       if(grupoOptional.isPresent()){
           Grupo grupoDB = grupoOptional.orElseThrow();
           grupoDB.setNombreGrupo(grupo.getNombreGrupo());
           grupoDB.setSubGrupos(grupo.getSubGrupos());
           grupoAux = grupoRepository.save(grupoDB);
       }
        return Optional.ofNullable(grupoAux);
    }

    @Override
    public void EliminarGrupo(Long id) {
            grupoRepository.deleteById(id);
    }



}
