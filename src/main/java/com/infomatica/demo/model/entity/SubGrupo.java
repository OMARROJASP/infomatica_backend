package com.infomatica.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "subgrupos")
public class SubGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre_sub_grupo")
    private String nombreSubgrupo   ;

    @OneToMany(mappedBy = "subGrupo")
    @JsonIgnore
    private List<Producto> productos;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSubgrupo() {
        return nombreSubgrupo;
    }

    public void setNombreSubgrupo(String nombreSubgrupo) {
        this.nombreSubgrupo = nombreSubgrupo;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
