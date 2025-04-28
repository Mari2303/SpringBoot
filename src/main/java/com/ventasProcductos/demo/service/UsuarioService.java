package com.ventasProcductos.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventasProcductos.demo.model.Usuario;
import com.ventasProcductos.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    //bucar usuarios por nombre, apellido, numero de documento y numero de celular
    public List<Usuario> searchUsuarios(String keyword) {
        return usuarioRepository.searchUsuarios(keyword);
    }

    // Obtener un usuario por ID
    public Optional<Usuario> findById(int id) {
        return usuarioRepository.findById(id);
    }


    // Eliminar un usuario por ID
    public void deleteById(int id) {
        usuarioRepository.deleteById(id);
    }

    

    //obtener un usuario por numero de documento
    public Usuario findByNumeroDocumento(int numeroDocumento) {
        return usuarioRepository.findByNumeroDocumento(numeroDocumento);
    }


    public Usuario updateNumeroDocumento(int numeroDocumentoActual, int nuevoNumeroDocumento) {
        Usuario usuario = usuarioRepository.findByNumeroDocumento(numeroDocumentoActual);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    
        usuario.setNumeroDocumento(nuevoNumeroDocumento);
        return usuarioRepository.save(usuario);
    }
    
    public Usuario save(Usuario usuario) {
        // Validaciones de campos vacíos o nulos
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del usuario no puede estar vacío.");
        }
        if (usuario.getApellido() == null || usuario.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del usuario no puede estar vacío.");
        }
        if (usuario.getNumeroDocumento() == null) {
            throw new IllegalArgumentException("El número de documento no puede ser nulo.");
        }
        if (usuario.getNumeroCelular() == null) {
            throw new IllegalArgumentException("El número de celular no puede ser nulo.");
        }
    
        // Validación de número de documento duplicado (solo si es nuevo)
        if ((usuario.getId() == null || usuario.getId() == 0) && usuarioRepository.existsByNumeroDocumento(usuario.getNumeroDocumento())) {
            throw new IllegalArgumentException("El número de documento ya está registrado.");
        }
        
    
        return usuarioRepository.save(usuario);
    }

    //eliminar un usuario por numero de documento
    public void deleteByNumeroDocumento(int numeroDocumento) {
        Usuario usuario = usuarioRepository.findByNumeroDocumento(numeroDocumento);
        usuarioRepository.delete(usuario);
}

}