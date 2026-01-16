import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {

    public void save(Animal animal, Integer zookeeperId) throws SQLException {

        String sql = "INSERT INTO animals (name, age, type, zookeeper_id) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, animal.getName());
            ps.setInt(2, animal.getAge());
            ps.setString(3, animal.getClass().getSimpleName());

            if (zookeeperId == null)
                ps.setNull(4, Types.INTEGER);
            else
                ps.setInt(4, zookeeperId);

            ps.executeUpdate();
        }
    }

    public List<Animal> findAll() throws SQLException {

        List<Animal> list = new ArrayList<>();

        String sql = "SELECT * FROM animals";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                String name = rs.getString("name");
                int age = rs.getInt("age");
                String type = rs.getString("type");

                Animal a;

                if ("lion".equalsIgnoreCase(type))
                    a = new lion(name, age);

                else if ("elephant".equalsIgnoreCase(type))
                    a = new elephant(name, age);

                else
                    continue;

                list.add(a);
            }
        }

        return list;
    }

    public void updateAge(int id, int newAge) throws SQLException {

        String sql = "UPDATE animals SET age=? WHERE id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, newAge);
            ps.setInt(2, id);

            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {

        String sql = "DELETE FROM animals WHERE id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
