package com.infomatica.demo.controller;

import com.infomatica.demo.model.entity.Grupo;
import com.infomatica.demo.model.entity.SubGrupo;
import com.infomatica.demo.service.GrupoService;
import com.infomatica.demo.service.SubGrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/sub_grupos")
@CrossOrigin(originPatterns = "*")
public class SubGrupoController {

    @Autowired
    private SubGrupoService subGrupoService;

    @Autowired
    private GrupoService grupoService;

    @GetMapping
    private List<SubGrupo> obtenerSubGrupos(){
        return subGrupoService.findAllSubGrupo();
    }


    @GetMapping("/{id}")
    private ResponseEntity<?> obtenerSubGrupos(@PathVariable ("id") Long id){
        Optional<SubGrupo> subGrupoOptional= subGrupoService.findIdSubGrupo(id);
        if(subGrupoOptional.isPresent()){
            return ResponseEntity.ok(subGrupoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lista_grupo/{id}")
    public List<SubGrupo> listaSubGrupoPorGrupo(@PathVariable Long id) {
        Optional<Grupo> grupoOptional = grupoService.findIdGrupo(id);

        if(grupoOptional.isPresent()){
            Grupo grupoDB = grupoOptional.orElseThrow();
            Grupo grupo = new Grupo();
            grupo.setId(grupoDB.getId());
            grupo.setNombreGrupo(grupoDB.getNombreGrupo());
            grupo.setSubGrupos(grupoDB.getSubGrupos());
            return subGrupoService.allSubGruposByGrupo(grupo);
        }else{
            return Collections.emptyList();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardarSubGrupos(@Valid @RequestBody SubGrupo subGrupo, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(subGrupoService.saveSubGrupo(subGrupo));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> actualizarSubGrupos(@Valid @RequestBody SubGrupo subGrupo, BindingResult result, @PathVariable("id") Long id){
        if (result.hasErrors()){
            return validation(result);
        }
        Optional<SubGrupo> suGrupoOptional = subGrupoService.updateSubGrupo(subGrupo, id);
        if(suGrupoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(suGrupoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubGrupos(@PathVariable("id") Long id){
        Optional<SubGrupo> o = subGrupoService.findIdSubGrupo(id);
        if(o.isPresent()){
            subGrupoService.EliminarsubGrupo(id);
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
