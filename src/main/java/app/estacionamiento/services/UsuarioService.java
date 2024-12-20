package app.estacionamiento.services;

import org.springframework.stereotype.Service;
import app.estacionamiento.models.UsuarioModel;
import app.estacionamiento.repositories.UsuarioRepository;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel registrarUsuario(UsuarioModel usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }
        return usuarioRepository.save(usuario);
    }
}