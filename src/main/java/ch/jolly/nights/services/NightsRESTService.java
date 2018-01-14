package ch.jolly.nights.services;

import ch.jolly.nights.dao.UserDAO;
import ch.jolly.nights.entity.UserEntity;
import ch.jolly.nights.handler.PasswordHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Path("/")
public class NightsRESTService {
    @POST
    @Path("/addUserService")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crunchifyREST(InputStream incomingData) {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder crunchifyBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
            String line = null;
            while ((line = in.readLine()) != null) {
                crunchifyBuilder.append(line);
            }
            UserEntity user = mapper.readValue(crunchifyBuilder.toString(), UserEntity.class);
            user.setPassword(PasswordHandler.hashIt(user.getPassword()));
            UserDAO.addUser(user);
        } catch (Exception e) {
            System.out.println("Error Parsing: - ");
            return Response.status(400).entity("Adding a User was Unsuccessful").build();
        }

        // return HTTP response 200 in case of success
        return Response.status(200).entity("Adding a User was Successful").build();
    }

    @GET
    @Path("/verify")
    @Produces(MediaType.TEXT_PLAIN)
    public Response verifyRESTService(InputStream incomingData) {
        String result = "NightsRESTService Successfully started..";

        return Response.status(200).entity(result).build();
    }
}
