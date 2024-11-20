package br.com.mecar.resource;

import br.com.mecar.dto.EnergyConsumptionRequestDto;
import br.com.mecar.service.EnergyConsumptionService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;
import java.util.Map;

@Path("/energy")
public class EnergyConsumptionResource {

    private EnergyConsumptionService energyService = new EnergyConsumptionService();

    // Endpoint to add energy consumption
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEnergyConsumption(EnergyConsumptionRequestDto energyDto) {
        energyService.addConsumption(energyDto);

        return Response.status(Response.Status.CREATED)
                .entity("Energy consumption added successfully.")
                .build();
    }

    // Endpoint to get all energy consumptions
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EnergyConsumptionRequestDto> getEnergyConsumptions() {
        return energyService.getAllConsumptions();
    }

    // Endpoint to calculate emissions for all energy types based on consumption kWh
    @GET
    @Path("/calculate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response calculateCarbonEmissionForAllTypes(@QueryParam("consumptionKwh") double consumptionKwh) {
        try {
            Map<String, Double> emissions = energyService.calculateCarbonEmissionForAllTypes(consumptionKwh);
            return Response.ok(emissions).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEnergyConsumption(@PathParam("id") int id) {
        List<EnergyConsumptionRequestDto> consumptions = energyService.getAllConsumptions();
        EnergyConsumptionRequestDto consumptionToDelete = consumptions.stream()
                .filter(ec -> ec.getId() == id)
                .findFirst()
                .orElse(null);

        if (consumptionToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Energy consumption record not found.")
                    .build();
        }

        consumptions.remove(consumptionToDelete);
        return Response.ok("Energy consumption deleted successfully.")
                .build();
    }


    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    public Response options() {
        return Response.ok()
                .header("Allow", "GET, POST, OPTIONS")
                .build();
    }
}
