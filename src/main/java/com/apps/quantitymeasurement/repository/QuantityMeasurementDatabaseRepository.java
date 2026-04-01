package com.apps.quantitymeasurement.repository;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    private final String url = "jdbc:h2:mem:testdb";
    private final String username = "sa";
    private final String password = "";

    public QuantityMeasurementDatabaseRepository() {
        initDatabase();
    }

    private void initDatabase() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS quantity_measurement (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "value DOUBLE," +
                    "unit VARCHAR(50)," +
                    "operation VARCHAR(50)," +
                    "result DOUBLE)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String sql = "INSERT INTO quantity_measurement(value, unit, operation, result) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, entity.getValue());
            ps.setString(2, entity.getUnit());
            ps.setString(3, entity.getOperation());
            ps.setDouble(4, entity.getResult());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();

        String sql = "SELECT * FROM quantity_measurement";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new QuantityMeasurementEntity(
                        rs.getDouble("value"),
                        rs.getString("unit"),
                        rs.getString("operation"),
                        rs.getDouble("result")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void deleteAll() {

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            stmt.execute("DELETE FROM quantity_measurement");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
