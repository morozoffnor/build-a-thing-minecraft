package buildathing.buildathing.bulding;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

public class Cleaner {
    public void cleanUpBlocks(List<Location> blocks) {
        for (Location location : blocks) {
            location.getBlock().setType(Material.AIR);
        }
    }
}
