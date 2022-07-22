package buildathing.buildathing.containers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/*
This is a singleton class containing everything about protected blocks
 */
public class StructureBlocksContainer {

    public List<Location> structureBlocks = new ArrayList<>();

    private static StructureBlocksContainer instance = null;

    private StructureBlocksContainer() {

    }

    public static StructureBlocksContainer getInstance() {
        if (instance == null) {
            instance = new StructureBlocksContainer();
        }
        return instance;
    }
}
