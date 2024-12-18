package py.com.progweb.estacionamiento.DAO;

import py.com.progweb.estacionamiento.model.Persona;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless

public class PersonaDAO {
    @PersistenceContext(unitName = "pruebaPU")

    private EntityManager em;


    public void agregarPersona(Persona entidad){
        this.em.persist(entidad);
    }

    public List<Persona> listaPersonas(){
        Query q=this.em.createQuery("select p from Persona p");
        return (List<Persona>) q.getResultList();
    }

    public void modificarPersona(Persona entidad) {
        this.em.merge(entidad);
    }

    public void borrarPersona(String nro_doc) {
        Persona personaExiste = listaPersonas().stream().
                filter(per -> per.getNumero_documento().equals(nro_doc)).findFirst().
                orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con número de " +
                        "documento: " + nro_doc));
        this.em.remove(personaExiste);
    }

}
