package buildathing.buildathing.bulding;

import buildathing.buildathing.containers.ProtectedBlocksContainer;
import buildathing.buildathing.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

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

    public void createStructure(FileConfiguration structureFile, Location baseCord) {
        ConfigurationSection blocks = structureFile.getConfigurationSection("blocks");
        if (blocks == null) {
            Bukkit.getLogger().severe("Couldn't find structures");
        }
        List<Location> structureBlocks = new ArrayList<>();
        for (String key : blocks.getKeys(false)) {
            ConfigurationSection block = blocks.getConfigurationSection(key);
            Location structureBlock = baseCord;
            structureBlock.add(block.getDouble("x"),block.getDouble("y"),block.getDouble("z"));
            try {
                structureBlock.getBlock().setType(Material.valueOf(block.getString("material")));
                Bukkit.getLogger().info("added block");
            } catch (Exception e) {
                structureBlock.getBlock().setType(Material.AIR);
            }
            structureBlocks.add(structureBlock.getBlock().getLocation());
            Bukkit.getLogger().info("protected block");
        }

        ProtectedBlocksContainer.getInstance().protectedBlocks.addAll(structureBlocks);
        Bukkit.getLogger().info("protected blocks");

        Bukkit.getLogger().info("structure loaded");
    }
}
