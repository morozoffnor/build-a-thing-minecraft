package buildathing.buildathing.containers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/*
This is a singleton class containing everything about protected blocks
 */
public class ProtectedBlocksContainer {

    public List<Location> protectedBlocks = new ArrayList<>();

    private static ProtectedBlocksContainer instance = null;

    private ProtectedBlocksContainer() {

    }

    public static ProtectedBlocksContainer getInstance() {
        if (instance == null) {
            instance = new ProtectedBlocksContainer();
            Location defaultLoc = new Location(Bukkit.getServer().getWorld("world"), 0, 0 , 0 );
            instance.protectedBlocks.add(defaultLoc);
        }
        return instance;
    }
}
