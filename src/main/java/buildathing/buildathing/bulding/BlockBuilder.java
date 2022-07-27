package buildathing.buildathing.bulding;

import buildathing.buildathing.containers.ProtectedBlocksContainer;
import buildathing.buildathing.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class BlockBuilder {
    public void createPlatform(Location location, int radius) {
        // pass the game center to GameManager
        GameManager.getInstance().gameCenter = location;
        // create a platform
        for (int x = (int) location.getX() - radius; x < (int) location.getX() + radius; x++) {
            for (int z = (int) location.getZ() - radius; z < (int) location.getZ() + radius; z++) {
                Block block = location.getWorld().getBlockAt(x, (int) location.getY(), z);
                block.setType(Material.WHITE_CONCRETE);
                ProtectedBlocksContainer.getInstance().protectedBlocks.add(block.getLocation());
            }
        }
    }

    public void createStructure(FileConfiguration structureFile, final Location baseCord) {
        ConfigurationSection blocks = structureFile.getConfigurationSection("blocks");
        if (blocks == null) {
            Bukkit.getLogger().severe("Couldn't find structures");
        }
        // new list to temporarily store blocks that need to be protected
        List<Location> structureBlocks = new ArrayList<>();
        for (String key : blocks.getKeys(false)) {

            // get individual block (section in cfg file)
            ConfigurationSection block = blocks.getConfigurationSection(key);

            Location structureBlock = baseCord.add(block.getDouble("x"),block.getDouble("y"),block.getDouble("z"));
            try {
                // try set block type with material stated in cfg file
                structureBlock.getBlock().setType(Material.valueOf(block.getString("material")));

            } catch (Exception e) {
                structureBlock.getBlock().setType(Material.AIR);
            }
            structureBlocks.add(structureBlock.getBlock().getLocation());
            structureBlock = baseCord.add(-block.getDouble("x"),-block.getDouble("y"),-block.getDouble("z"));;

        }
        // protect blocks
        ProtectedBlocksContainer.getInstance().protectedBlocks.addAll(structureBlocks);
    }
}
