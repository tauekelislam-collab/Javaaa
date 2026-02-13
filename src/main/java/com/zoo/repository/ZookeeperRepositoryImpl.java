package com.zoo.repository;

import com.zoo.domain.Zookeeper;
import com.zoo.repository.CrudRepository;
import com.zoo.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ZookeeperRepositoryImpl implements CrudRepository<Zookeeper> {

    @Override
    public Zookeeper save(Zookeeper entity) {
        String sql = "INSERT INTO zookeepers(full_name, email, experience_years) " +
                "VALUES (?, ?, ?) RETURNING id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getFullName());
            ps.setString(2, entity.getEmail());
            ps.setInt(3, entity.getExperienceYears());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) entity.setId(rs.getInt("id"));
            }
            return entity;

        } catch (SQLException e) {
            throw new RuntimeException("DB error on save zookeeper", e);
        }
    }

    @Override
    public List<Zookeeper> findAll() {
        String sql = "SELECT * FROM zookeepers ORDER BY id";
        List<Zookeeper> result = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) result.add(mapRow(rs));
            return result;

        } catch (SQLException e) {
            throw new RuntimeException("DB error on findAll zookeepers", e);
        }
    }

    @Override
    public Optional<Zookeeper> findById(int id) {
        String sql = "SELECT * FROM zookeepers WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB error on findById zookeeper", e);
        }
    }

    @Override
    public Zookeeper update(Zookeeper entity) {
        String sql = "UPDATE zookeepers SET full_name=?, email=?, experience_years=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getFullName());
            ps.setString(2, entity.getEmail());
            ps.setInt(3, entity.getExperienceYears());
            ps.setInt(4, entity.getId());

            ps.executeUpdate();
            return entity;

        } catch (SQLException e) {
            throw new RuntimeException("DB error on update zookeeper", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM zookeepers WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("DB error on delete zookeeper", e);
        }
    }

    // Доп. метод для проверки email (под ваш Exception AlreadyExists)
    public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM zookeepers WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB error on existsByEmail", e);
        }
    }

    private Zookeeper mapRow(ResultSet rs) throws SQLException {
        return new Zookeeper(
                rs.getInt("id"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getInt("experience_years")
        );
    }
}
