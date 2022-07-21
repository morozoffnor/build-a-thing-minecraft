package buildathing.buildathing.bulding;

import buildathing.buildathing.containers.ProtectedBlocksContainer;
import buildathing.buildathing.managers.GameManager;
import org.bukkit.Location;
import org.bukkit.Material;

public class BlockBuilder {
    // this will do initial setup
    public final void buildABlock(Location location) {
        // pass the game center to GameManager
        GameManager.getInstance().gameCenter = location;

        for (int i = 0; i < 6; i++) {
            // add values to cords
            Location platformBlock = location.getBlock().getLocation().add(i,0, 0);
            Location negativePlatformBlock = location.getBlock().getLocation().add(-i,0,0);
            // set blocks
            platformBlock.getBlock().setType(Material.WHITE_CONCRETE);
            negativePlatformBlock.getBlock().setType(Material.WHITE_CONCRETE);
            // add blocks to container
            ProtectedBlocksContainer.getInstance().protectedBlocks.add(negativePlatformBlock);
            ProtectedBlocksContainer.getInstance().protectedBlocks.add(platformBlock);
            for (int h = 0; h < 6; h++) {
                // add values to cords
                Location platformBlockZ = location.getBlock().getLocation().add(i,0, h);
                Location negativePlatformBlockZ = location.getBlock().getLocation().add(-i,0,-h);
                // set blocks
                platformBlockZ.getBlock().setType(Material.WHITE_CONCRETE);
                negativePlatformBlockZ.getBlock().setType(Material.WHITE_CONCRETE);
                // add blocks to container
                ProtectedBlocksContainer.getInstance().protectedBlocks.add(negativePlatformBlockZ);
                ProtectedBlocksContainer.getInstance().protectedBlocks.add(platformBlockZ);
            }

        }
    }
}
