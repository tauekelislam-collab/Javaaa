package com.zoo.repository;

import com.zoo.domain.Animal;
import com.zoo.domain.Elephant;
import com.zoo.domain.Lion;
import com.zoo.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimalRepositoryImpl implements CrudRepository<Animal> {

    // ================= SAVE =================
    @Override
    public Animal save(Animal entity) {

        String sql = """
                INSERT INTO animals(type, name, age, weight_kg, mane_length, trunk_length)
                VALUES (?, ?, ?, ?, ?, ?) RETURNING id
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            fillParams(ps, entity);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                entity.setId(rs.getInt("id"));
            }

            return entity;

        } catch (SQLException e) {
            e.printStackTrace(); // 👈 ПОКАЖЕТ РЕАЛЬНУЮ ОШИБКУ
            throw new RuntimeException("DB error on save animal", e);
        }
    }

    // ================= FIND ALL =================
    @Override
    public List<Animal> findAll() {

        String sql = "SELECT * FROM animals ORDER BY id";

        List<Animal> animals = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                animals.add(mapRow(rs));
            }

            return animals;

        } catch (SQLException e) {
            e.printStackTrace(); // 👈 ЭТО ГЛАВНОЕ ИЗМЕНЕНИЕ
            throw new RuntimeException("DB error on findAll animals", e);
        }
    }

    // ================= FIND BY ID =================
    @Override
    public Optional<Animal> findById(int id) {

        String sql = "SELECT * FROM animals WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DB error on findById animal", e);
        }
    }

    // ================= UPDATE =================
    @Override
    public Animal update(Animal entity) {

        String sql = """
                UPDATE animals
                SET type=?, name=?, age=?, weight_kg=?, mane_length=?, trunk_length=?
                WHERE id=?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            fillParams(ps, entity);
            ps.setInt(7, entity.getId());

            ps.executeUpdate();

            return entity;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DB error on update animal", e);
        }
    }

    // ================= DELETE =================
    @Override
    public void delete(int id) {

        String sql = "DELETE FROM animals WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate(); // 👈 ОБЯЗАТЕЛЬНО executeUpdate

            System.out.println("Deleted rows = " + rows); // для проверки

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DB error on delete animal", e);
        }
    }

    // ================= HELPERS =================

    private void fillParams(PreparedStatement ps, Animal a) throws SQLException {

        if (a instanceof Lion lion) {

            ps.setString(1, "LION");
            ps.setString(2, lion.getName());
            ps.setInt(3, lion.getAge());
            ps.setDouble(4, lion.getWeightKg());
            ps.setDouble(5, lion.getManeLength());
            ps.setNull(6, Types.DOUBLE);

        } else if (a instanceof Elephant el) {

            ps.setString(1, "ELEPHANT");
            ps.setString(2, el.getName());
            ps.setInt(3, el.getAge());
            ps.setDouble(4, el.getWeightKg());
            ps.setNull(5, Types.DOUBLE);
            ps.setDouble(6, el.getTrunkLength());

        } else {
            throw new IllegalArgumentException("Unknown animal type");
        }
    }

    private Animal mapRow(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String type = rs.getString("type");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        double weight = rs.getDouble("weight_kg");

        if ("LION".equalsIgnoreCase(type)) {
            double mane = rs.getDouble("mane_length");
            return new Lion(id, name, age, weight, mane);
        }

        if ("ELEPHANT".equalsIgnoreCase(type)) {
            double trunk = rs.getDouble("trunk_length");
            return new Elephant(id, name, age, weight, trunk);
        }

        throw new IllegalArgumentException("Unknown animal type in DB: " + type);
    }
}

