package com.yazlab.resource;

import com.yazlab.dto.ResponseDTO;
import com.yazlab.dto.TextDTO;
import com.yazlab.dto.TokenDTO;
import com.yazlab.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/")
public class Main {
    @Autowired
    MainService mainService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/frequency")
    public ResponseDTO setFrequency(TextDTO textDTO) {
        return mainService.setWords(textDTO);
    }


    @POST
    @Path("/myfrequency")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDTO getFrequency(TokenDTO tokenDTO) {
        return mainService.getWords(tokenDTO);

    }
}
