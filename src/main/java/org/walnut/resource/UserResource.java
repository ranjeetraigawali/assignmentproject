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
import java.util.Objects;

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
    public Response getUsers(
            @QueryParam("sortOrder") @DefaultValue("asc") String sortOrder,
            @QueryParam("sortColumn") @DefaultValue("id") String sortColumn,
            @QueryParam("pageSize") @DefaultValue("50") int pageSize,
            @QueryParam("pageNumber") @DefaultValue("1") int pageNumber,
            @QueryParam("filterColumn") @DefaultValue("id") String filterColumn,
            @QueryParam("filterValue") @DefaultValue("0") String filterValue) {

        try{
            List<User> userList = userService.getUsers(sortOrder, sortColumn, pageSize, pageNumber, filterColumn, filterValue);
            if(!userList.isEmpty()){
                return Response.ok(userList).build();
            }
            else {
                return Response.ok("No user found.").build();
            }
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return Response.ok("No records found.").build();
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
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
        return Response.ok(Objects.requireNonNullElse(user, "No user found.")).build();
    }

    /**
     * To save the user
     * @return response obj with success msg
     */
    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/save")
    public Response saveUser(User user){
        if(userService.save(user)){
            return  Response.created(URI.create("/user/get/"+ user.getId())).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
