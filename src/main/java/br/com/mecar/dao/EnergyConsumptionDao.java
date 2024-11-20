package br.com.mecar.dao;

import br.com.mecar.model.EnergyConsumption;
import br.com.mecar.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnergyConsumptionDao {

    private Connection conexao;

    public EnergyConsumptionDao() {
        this.conexao = ConnectionFactory.obterConexao();
    }

    public void addConsumption(EnergyConsumption energyConsumption) {
        PreparedStatement comandoSql = null;
        try {
            String sql = "INSERT INTO energy_consumption (consumption_kwh) VALUES (?)";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setDouble(1, energyConsumption.getConsumptionKwh());
            comandoSql.executeUpdate();
            comandoSql.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<EnergyConsumption> getAllConsumptions() {
        List<EnergyConsumption> consumptions = new ArrayList<>();
        PreparedStatement comandoSql = null;
        try {
            String sql = "SELECT * FROM energy_consumption";
            comandoSql = conexao.prepareStatement(sql);
            ResultSet rs = comandoSql.executeQuery();

            while (rs.next()) {
                EnergyConsumption consumption = new EnergyConsumption();
                consumption.setId(rs.getInt("id"));
                consumption.setConsumptionKwh(rs.getDouble("consumption_kwh"));
                consumptions.add(consumption);
            }
            comandoSql.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consumptions;
    }

    public void fecharConexao() {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
