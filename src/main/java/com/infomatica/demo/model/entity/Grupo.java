package com.infomatica.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "grupos")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre_grupo")
    private String nombreGrupo   ;

    @OneToMany(mappedBy = "grupo") // Corregido el mappedBy aquí
    @JsonIgnore
    private List<SubGrupo> subGrupos;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public List<SubGrupo> getSubGrupos() {
        return subGrupos;
    }

    public void setSubGrupos(List<SubGrupo> subGrupos) {
        this.subGrupos = subGrupos;
    }
}
