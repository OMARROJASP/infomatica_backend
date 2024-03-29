package com.infomatica.demo.service.Impl;

import com.infomatica.demo.model.entity.Producto;
import com.infomatica.demo.model.entity.SubGrupo;
import com.infomatica.demo.repository.ProductoRepository;
import com.infomatica.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public List<Producto> findAllProducts() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findIdProducto(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto saveProduct(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> updateProducto(Producto producto, Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        Producto p = null;
        if (productoOptional.isPresent()){
            Producto productDB = productoOptional.orElseThrow();
            productDB.setNombre(producto.getNombre());
            productDB.setPrecio(producto.getPrecio());
            p = productoRepository.save(productDB);
        }
        return Optional.ofNullable(p);
    }

    @Override
    public void EliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> findAllProductoBySubGrupo(SubGrupo subGrupo) {
        return productoRepository.findProductoBySubGrupo(subGrupo);
    }
}
