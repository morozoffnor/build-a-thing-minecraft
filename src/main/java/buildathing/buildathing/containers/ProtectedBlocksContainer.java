package buildathing.buildathing.containers;

import org.bukkit.Location;

import java.util.List;

/*
This is a singleton class containing everything about protected blocks
 */
public class ProtectedBlocksContainer {

    public List<Location> protectedBlocks;

    private static ProtectedBlocksContainer instance = null;

    private ProtectedBlocksContainer() {

    }

    public static ProtectedBlocksContainer getInstance() {
        if (instance == null) {
            instance = new ProtectedBlocksContainer();
        }
        return instance;
    }
}
