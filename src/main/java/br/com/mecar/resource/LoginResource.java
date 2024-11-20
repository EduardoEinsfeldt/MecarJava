package br.com.mecar.resource;

import br.com.mecar.dto.UsuarioRequestDto;
import br.com.mecar.service.LoginService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.servlet.http.HttpServletRequest;

@Path("/login")
public class LoginResource {

    private LoginService loginService = new LoginService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UsuarioRequestDto usuarioDto, @Context HttpServletRequest request) {
        boolean authenticated = loginService.authenticate(usuarioDto);

        if (authenticated) {
            request.getSession(true);
            return Response.ok("Login successful!").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid username or password").build();
        }
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpServletRequest request) {
        if (request.getSession(false) != null) {
            request.getSession().invalidate();
            return Response.ok("Logout successful!").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("No active session to log out from.").build();
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
