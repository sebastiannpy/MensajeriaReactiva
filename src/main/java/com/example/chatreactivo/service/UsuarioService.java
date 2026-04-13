package com.example.chatreactivo.service;

import org.springframework.stereotype.Service;

import com.example.chatreactivo.model.Usuario;
import com.example.chatreactivo.repository.UsuarioRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Flux<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Mono<Usuario> guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
