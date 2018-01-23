package com.task.rainyhills.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.task.rainyhills.service.RainyHillsVolumeService;

/**
 * Created by Valeriy Shtanko on 2018-Jan-20, 22:16
 */
@ApplicationScoped
@Path("/calculate")
public class RainyHillsController {
    @Inject
    private RainyHillsVolumeService volumeService;

    /**
     * Display information string about application usage.
     *
     * @return Information string
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello, I am Rainy Hills application. Use POST method with JSON body:\n\n" +
               "{ \"hills\" : [1, 10, 5 ...] }\n\n" +
               "to calculate the volume of water.";
    }

    /**
     * Calculates the volume of water which remained between hills after the rain.
     *
     * @param request - message payload, with contains hills landscape
     *
     * @return JSON string with calculated result.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String calculateVolume(CalculateVolumeRequest request) {
        return String.format("{ \"volume\": %d }",
                             volumeService.calculateVolume(request.getHills()));
    }
}
