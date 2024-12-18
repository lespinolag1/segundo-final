package py.com.progweb.estacionamiento.rest;

import py.com.progweb.estacionamiento.DAO.PersonaDAO;
import py.com.progweb.estacionamiento.model.Persona;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("persona")
@Consumes("application/json")
@Produces("application/json")

public class PersonaREST {

    @Inject
    private PersonaDAO personaDAO;

    @GET
    @Path("/")
    public Response listar(){
        return Response.ok(personaDAO.listaPersonas()).build();
    }

    @POST
    @Path("/")
    public Response crear(Persona p){
        this.personaDAO.agregarPersona(p);
    return Response.ok().build();
    }

    @PUT
    @Path("/{nro_doc}")
    public Response modificar(@PathParam("nro_doc") String nro_doc, Persona p) {
        Persona personaExiste = personaDAO.listaPersonas().stream().filter(per -> per.getNumero_documento().equals(nro_doc)).findFirst().orElse(null);
        if (personaExiste == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        p.setNumero_documento(nro_doc);
        this.personaDAO.modificarPersona(p);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{nro_doc}")
    public Response borrar(@PathParam("nro_doc") String nro_doc) {
        try {
            this.personaDAO.borrarPersona(nro_doc);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

}
