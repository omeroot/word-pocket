package com.yazlab.resource;

import com.yazlab.dto.TextDTO;
import com.yazlab.dto.TokenDTO;
import com.yazlab.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/")
public class Main {
    @Context
    private HttpServletRequest httpServletRequest;

    @Autowired
    MainService mainService;

    @POST
    @Path("myfrequency")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getFrequency(TokenDTO tokenDTO){
        return Response.ok(mainService.getWords(tokenDTO)).build();
    }

    @POST
    @Path("frequency")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setFrequency(TextDTO textDTO){
        return Response.status(201).entity("omer").build();
    }
}
