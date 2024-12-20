package app.estacionamiento.services;

import org.springframework.stereotype.Service;
import app.estacionamiento.models.UsuarioModel;
import app.estacionamiento.repositories.UsuarioRepository;

import java.util.List;
import java.util.NoSuchElementException;


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

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel actualizarUsuario(Long id, UsuarioModel usuario) {
        UsuarioModel existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        existente.setNombre(usuario.getNombre());
        existente.setApellido(usuario.getApellido());
        existente.setEmail(usuario.getEmail());
        existente.setMatricula(usuario.getMatricula());
        existente.setMarcaVehiculo(usuario.getMarcaVehiculo());
        existente.setModeloVehiculo(usuario.getModeloVehiculo());
        existente.setContrasena(usuario.getContrasena());

        return usuarioRepository.save(existente);
    }


}
