package br.com.mecar.model;

public class EnergyConsumption {
    private int id;
    private double consumptionKwh;

    public EnergyConsumption() {}

    public EnergyConsumption(int id, double consumptionKwh) {
        this.id = id;
        this.consumptionKwh = consumptionKwh;
    }

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
}
