package com.infomatica.demo.controller;

import com.infomatica.demo.model.entity.Grupo;
import com.infomatica.demo.model.entity.Producto;
import com.infomatica.demo.model.entity.SubGrupo;
import com.infomatica.demo.service.ProductoService;
import com.infomatica.demo.service.SubGrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/productos")
@CrossOrigin(originPatterns = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private SubGrupoService subGrupoService;

    @GetMapping
    private List<Producto> obtenerProductos(){
        return productoService.findAllProducts();
    }



    @GetMapping("/{id}")
    private ResponseEntity<?> obtenerProducto(@PathVariable ("id") Long id){
        Optional<Producto> detallesOptional= productoService.findIdProducto(id);
        if(detallesOptional.isPresent()){
            return ResponseEntity.ok(detallesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lista_grupo/{id}")
    public List<Producto> listaSubGrupoPorGrupo(@PathVariable Long id) {
        Optional<SubGrupo> subGrupoOptional = subGrupoService.findIdSubGrupo(id);

        if(subGrupoOptional.isPresent()){
            SubGrupo grupoDB = subGrupoOptional.orElseThrow();
            SubGrupo subGrupo = new SubGrupo();
            subGrupo.setId(grupoDB.getId());
            subGrupo.setGrupo(grupoDB.getGrupo());
            subGrupo.setNombreSubgrupo(grupoDB.getNombreSubgrupo());
            subGrupo.setProductos(grupoDB.getProductos());
            return productoService.findAllProductoBySubGrupo(subGrupo);
        }else{
            return Collections.emptyList();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardarProductos(@Valid @RequestBody Producto producto, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.saveProduct(producto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> actualizarProductos(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable("id") Long id){
        if (result.hasErrors()){
            return validation(result);
        }
        Optional<Producto> detallesOptional = productoService.updateProducto(producto, id);
        if(detallesOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(detallesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductos(@PathVariable("id") Long id){
        Optional<Producto> o = productoService.findIdProducto(id);
        if(o.isPresent()){
            productoService.EliminarProducto(id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
