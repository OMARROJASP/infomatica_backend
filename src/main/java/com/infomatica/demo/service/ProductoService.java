package com.infomatica.demo.service;

import com.infomatica.demo.model.entity.Producto;
import com.infomatica.demo.model.entity.SubGrupo;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAllProducts();

    Optional<Producto> findIdProducto(Long id);
    Producto saveProduct(Producto producto);

    Optional<Producto> updateProducto(Producto producto, Long id);
    void EliminarProducto(Long id);

    List<Producto> findAllProductoBySubGrupo(SubGrupo subGrupo);
}
