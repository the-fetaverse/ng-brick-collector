package daos;

import configurations.BrickCollectorDBCreds;
import models.LegoSet;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LegoSetDAOImpl implements LegoSetDAO {

    @Override
    public LegoSet createSet(LegoSet legoSet) {
        String sql = "INSERT INTO lego_sets ( name, year, img, num_parts) VALUES (?, ?, ?, ?)";
        try(Connection conn = BrickCollectorDBCreds.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, legoSet.getName());
            ps.setInt(2, legoSet.getYear());
            ps.setString(3, legoSet.getImg());
            ps.setInt(4, legoSet.getNum_parts());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected != 0) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    int key = keys.getInt(1);
                    legoSet.setId(key);
                    return legoSet;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<LegoSet> findAllSets() {
        String sql = "SELECT * FROM lego_sets";

        try( Connection conn = BrickCollectorDBCreds.getInstance().getConnection()) {
            Statement stmt = conn.createStatement();
            LinkedList<LegoSet> sets = new LinkedList<>();
            ResultSet res = stmt.executeQuery(sql);

            while(res.next()) {
                LegoSet legoSet = new LegoSet(res.getInt("set_id"),
                        res.getString("name"),
                        res.getInt("year"),
                        res.getString("img"),
                        res.getInt("num_parts"));
                sets.add(legoSet);
            }
            return sets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LegoSet findSetByID(int id) {
        String sql = "SELECT * FROM lego_sets WHERE set_id=" + id;

        try(Connection conn = BrickCollectorDBCreds.getInstance().getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            if(res.next()) {
                return new LegoSet(res.getInt("set_id"),
                        res.getString("name"),
                        res.getInt("year"),
                        res.getString("img"),
                        res.getInt("num_part"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LegoSet> findSetByName(String name) {
        String sql = "SELECT * FROM lego_sets WHERE name = ?";
        LinkedList<LegoSet> results = new LinkedList<>();

        try(Connection conn = BrickCollectorDBCreds.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet res = ps.executeQuery();
            while(res.next()) {
                LegoSet legoSet = new LegoSet(res.getInt("brick_id"),
                        res.getString("name"),
                        res.getInt("year"),
                        res.getString("img"),
                        res.getInt("num_part"));
                results.add(legoSet);
            }
            return results;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LegoSet updateSet(LegoSet legoSet, int id) {
        String sql = "UPDATE lego_sets SET name = ? , year = ?, num_parts = ? WHERE set_id = ?";
        try(Connection conn = BrickCollectorDBCreds.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, legoSet.getName());
            ps.setInt(2, legoSet.getYear());
            ps.setInt(3, legoSet.getNum_parts());
            ps.setInt(4, id);
            ps.executeUpdate();
            return legoSet;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * deleteBrick()
     * @param id: The id provided by the user.
     */
    @Override
    public void deleteSet(int id) {
        String sql = "DELETE FROM lego_sets WHERE set_id = ?";
        try(Connection conn = BrickCollectorDBCreds.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}