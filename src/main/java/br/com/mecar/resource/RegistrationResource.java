package br.com.mecar.resource;

import br.com.mecar.dto.UsuarioRequestDto;
import br.com.mecar.service.RegistrationService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/register")
public class RegistrationResource {

    private RegistrationService registrationService = new RegistrationService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UsuarioRequestDto usuarioDto, @Context UriInfo uriInfo) {
        String registrationResult = registrationService.register(usuarioDto);

        if ("Registration successful!".equals(registrationResult)) {
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(usuarioDto.getUsername());
            return Response.created(builder.build()).entity(registrationResult).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(registrationResult).build();
        }
    }

    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    public Response options() {
        return Response.ok()
                .header("Allow", "POST, OPTIONS")
                .build();
    }
}