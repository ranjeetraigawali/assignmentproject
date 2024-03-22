package org.walnut.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.walnut.entity.User;
import org.walnut.service.UserService;

import java.net.URI;
import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "User API",
                version = "1.0.0"
        ),
        tags = @Tag(name = "user", description = "User operations."))
@Path("/user")
public class UserResource {

    @Inject
    UserService userService;

    /**
     * To get list of all the users
     * @return list of users
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getUsers(){
        List<User> userList = userService.getUsers();
        if(userList != null && !userList.isEmpty()){
            return Response.ok(userList).build();
        }
        return Response.ok("No user found.").build();
    }

    /**
     * To get user based on id
     * @return user
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response getUser(@PathParam("id") Long id){
        User user = userService.getUser(id);
        if(user != null){
            return Response.ok(user).build();
        }
        return Response.ok("No user found.").build();
    }

    /**
     * To save the user
     * @return response obj with success msg
     */
    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/save")
    @Transactional
    public Response saveUser(User user){
        if(userService.save(user)){
            return  Response.created(URI.create("/user/get/"+ user.id)).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
