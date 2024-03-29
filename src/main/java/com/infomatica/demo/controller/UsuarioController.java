package com.infomatica.demo.controller;

import com.infomatica.demo.model.dto.DtoUsuario;
import com.infomatica.demo.model.entity.Usuario;
import com.infomatica.demo.model.request.UsuarioRequest;
import com.infomatica.demo.service.UsuarioService;
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
@RequestMapping("/usuarios")
@CrossOrigin(originPatterns = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public List<DtoUsuario> list(){
        return usuarioService.findAllUsuario();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<DtoUsuario> userDtoOptional = usuarioService.findIdUsuario(id);

        if(userDtoOptional.isPresent()){
            return ResponseEntity.ok(userDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }



    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Usuario user, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(user));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UsuarioRequest user, BindingResult result, @PathVariable Long id) {
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<DtoUsuario> o = usuarioService.updateUsuario(user, id);

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<DtoUsuario> o = usuarioService.findIdUsuario(id);

        if(o.isPresent()) {
            usuarioService.EliminarGrupo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err-> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
