package br.com.mecar.service;

import br.com.mecar.dao.EnergyConsumptionDao;
import br.com.mecar.dto.EnergyConsumptionRequestDto;
import br.com.mecar.model.EnergyConsumption;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnergyConsumptionService {

    private EnergyConsumptionDao energyConsumptionDao = new EnergyConsumptionDao();

    // Method to add energy consumption
    public void addConsumption(EnergyConsumptionRequestDto consumptionDto) {
        EnergyConsumption consumption = consumptionDto.convert(consumptionDto);
        energyConsumptionDao.addConsumption(consumption);
    }

    // Method to get all energy consumptions
    public List<EnergyConsumptionRequestDto> getAllConsumptions() {
        List<EnergyConsumption> consumptions = energyConsumptionDao.getAllConsumptions();
        return consumptions.stream()
                .map(energy -> new EnergyConsumptionRequestDto().convertToDto(energy))
                .collect(Collectors.toList());
    }

    // Method to calculate carbon emissions for all energy types
    public Map<String, Double> calculateCarbonEmissionForAllTypes(double consumptionKwh) {
        Map<String, Double> emissions = new HashMap<>();
        emissions.put("Solar", consumptionKwh * 20); // g CO₂/kWh for Solar
        emissions.put("Wind (Eólica)", consumptionKwh * 15); // g CO₂/kWh for Wind
        emissions.put("Hydroelectric (Hidrelétrica)", consumptionKwh * 10); // g CO₂/kWh for Hydroelectric
        emissions.put("Geothermal (Geotérmica)", consumptionKwh * 40); // g CO₂/kWh for Geothermal
        emissions.put("Biomass (Biomassa)", consumptionKwh * 100); // g CO₂/kWh for Biomass
        return emissions;
    }
}
