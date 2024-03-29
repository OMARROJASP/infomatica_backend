package com.infomatica.demo.controller;

import com.infomatica.demo.model.entity.Grupo;
import com.infomatica.demo.service.GrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/grupos")
@CrossOrigin(originPatterns = "*")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping
    private List<Grupo> obtenerGrupos(){
        return grupoService.findAllGrupo();
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> obtenerGrupos(@PathVariable("id") Long id){
        Optional<Grupo> gruposOptional= grupoService.findIdGrupo(id);
        if(gruposOptional.isPresent()){
            return ResponseEntity.ok(gruposOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardarGrupos(@Valid @RequestBody Grupo grupo, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoService.saveGrupo(grupo));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarGrupos(@Valid @RequestBody Grupo grupos, BindingResult result, @PathVariable("id") Long id){
        if (result.hasErrors()){
            return validation(result);
        }
        Optional<Grupo> detallesOptional = grupoService.updateGrupo(grupos, id);
        if(detallesOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(detallesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGrupo(@PathVariable("id") Long id){
        Optional<Grupo> o = grupoService.findIdGrupo(id);
        if(o.isPresent()){
            grupoService.EliminarGrupo(id);
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
