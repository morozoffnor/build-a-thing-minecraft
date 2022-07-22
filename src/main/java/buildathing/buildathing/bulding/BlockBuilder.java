package buildathing.buildathing.bulding;

import buildathing.buildathing.containers.ProtectedBlocksContainer;
import buildathing.buildathing.managers.GameManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockBuilder {
    public void createPlatform(Location location, int radius) {
        // pass the game center to GameManager
        GameManager.getInstance().gameCenter = location;
        // create a platform
        for (int x = (int)location.getX()-radius; x < (int)location.getX()+radius; x++) {
            for (int z = (int)location.getZ()-radius; z < (int)location.getZ()+radius; z++) {
                Block block = location.getWorld().getBlockAt(x, (int)location.getY(), z);
                block.setType(Material.WHITE_CONCRETE);
                ProtectedBlocksContainer.getInstance().protectedBlocks.add(block.getLocation());
            }
        }
    }
}
