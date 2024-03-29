package com.infomatica.demo.service.Impl;

import com.infomatica.demo.model.dto.DtoUsuario;
import com.infomatica.demo.model.dto.mapper.DtoMapperUsuario;
import com.infomatica.demo.model.entity.Usuario;
import com.infomatica.demo.model.request.UsuarioRequest;
import com.infomatica.demo.repository.UsuarioRepository;
import com.infomatica.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<DtoUsuario> findAllUsuario() {
        List<Usuario>  user = (List<Usuario>) usuarioRepository.findAll();
        return user
                .stream()
                .map(u-> DtoMapperUsuario
                        .builder()
                        .setUsuario(u)
                        .build())
                .collect(Collectors.toList());
    }



    @Override
    public Optional<DtoUsuario> findIdUsuario(Long id) {
        return usuarioRepository.findById(id)
                .map(u-> DtoMapperUsuario
                        .builder()
                        .setUsuario(u)
                        .build());
    }

    @Override
    public DtoUsuario saveUsuario(Usuario usuario) {
        /*
         usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        Optional<Role> o = roleRepository.findByName("ROLE_USER");

        List<Role> roles = new ArrayList<>();
        if(o.isPresent()){
            roles.add(o.orElseThrow());
        }

        usuario.setRoles(roles);
         */

        return DtoMapperUsuario.builder().setUsuario(usuarioRepository.save(usuario)).build();
    }

    @Override
    public Optional<DtoUsuario> updateUsuario(UsuarioRequest usuario, Long id) {
        Optional<Usuario> o = usuarioRepository.findById(id);

        Usuario usuarioOptional = null;
        if(o.isPresent()){
            Usuario usuarioDb= o.orElseThrow();
            usuarioDb.setNombre(usuario.getNombre());
            usuarioOptional = usuarioRepository.save(usuarioDb);
        }
        return Optional.ofNullable(DtoMapperUsuario.builder().setUsuario(usuarioOptional).build());
    }

    @Override
    public void EliminarGrupo(Long id)  {
        usuarioRepository.deleteById(id);
    }
}
