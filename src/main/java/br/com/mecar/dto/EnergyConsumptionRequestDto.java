package br.com.mecar.dto;

import br.com.mecar.model.EnergyConsumption;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EnergyConsumptionRequestDto {
    private int id;
    private double consumptionKwh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getConsumptionKwh() {
        return consumptionKwh;
    }

    public void setConsumptionKwh(double consumptionKwh) {
        this.consumptionKwh = consumptionKwh;
    }

    public EnergyConsumptionRequestDto convertToDto(EnergyConsumption energyConsumption) {
        EnergyConsumptionRequestDto energyConsumptionRequestDto = new EnergyConsumptionRequestDto();
        energyConsumptionRequestDto.setId(energyConsumption.getId());
        energyConsumptionRequestDto.setConsumptionKwh(energyConsumption.getConsumptionKwh());
        return energyConsumptionRequestDto;
    }

    public EnergyConsumption convert(EnergyConsumptionRequestDto energyConsumptionRequestDto) {
        EnergyConsumption energyConsumption = new EnergyConsumption();
        energyConsumption.setId(energyConsumptionRequestDto.getId());
        energyConsumption.setConsumptionKwh(energyConsumptionRequestDto.getConsumptionKwh());
        return energyConsumption;
    }
}
