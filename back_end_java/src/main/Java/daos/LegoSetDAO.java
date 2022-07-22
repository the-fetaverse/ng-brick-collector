package daos;

import models.LegoSet;

import java.util.List;

public interface LegoSetDAO {
    // CRUD operations

    // Create:
    public LegoSet createSet(LegoSet brick);

    // Read:
    public List<LegoSet> findAllSets();
    public LegoSet findSetByID(int id);
    public List<LegoSet> findSetByName(String name);

    // Update:
    public LegoSet updateSet(LegoSet legoSet, int id);

    // Delete:
    public void deleteSet(int id);
}