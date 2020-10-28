import model.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/users")
public class UserServer {
    private final UserRepository userRepository = new UserRepositoryImpl();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user, @Context UriInfo uriInfo) {
        User createdUser = userRepository.add(user);
        URI uri = uriInfo.getAbsolutePathBuilder().path(createdUser.getId()).build();
        return Response.created(uri).entity(createdUser).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User user = userRepository.get(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(user).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") String id, User user, @Context UriInfo uriInfo) {
        if (user.getId() != null && !id.equals(user.getId())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("User ID cannot be modified").build();
        }

        user.setId(id);
        userRepository.update(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") String id) {
        userRepository.remove(id);
        return Response.ok().build();
    }
}