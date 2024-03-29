package com.infomatica.demo.repository;

import com.infomatica.demo.model.entity.Producto;
import com.infomatica.demo.model.entity.SubGrupo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoRepository extends CrudRepository<Producto,Long> {
    List<Producto> findProductoBySubGrupo(SubGrupo subGrupo);
}
