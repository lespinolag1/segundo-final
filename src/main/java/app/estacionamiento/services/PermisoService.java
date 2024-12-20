package app.estacionamiento.services;

import org.springframework.stereotype.Service;
import app.estacionamiento.models.PermisoModel;
import app.estacionamiento.models.UsuarioModel;
import app.estacionamiento.repositories.PermisoRepository;
import app.estacionamiento.repositories.UsuarioRepository;

import java.util.List;

@Service
public class PermisoService {
    private final PermisoRepository permisoRepository;
    private final UsuarioRepository usuarioRepository;
    public PermisoService(PermisoRepository permisoRepository, UsuarioRepository usuarioRepository) {
        this.permisoRepository = permisoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public PermisoModel asignarPermiso(Long usuarioId, PermisoModel permiso) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new IllegalArgumentException("El usuario no existe.");
        }
        if (permisoRepository.existsByUsuarioId(usuarioId)) {
            throw new IllegalArgumentException("El usuario ya tiene un permiso asignado.");
        }
        UsuarioModel usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        return permisoRepository.save(permiso);
    }
    public List<PermisoModel> obtenerPermisos() {
        return permisoRepository.findAll();
    }

}
