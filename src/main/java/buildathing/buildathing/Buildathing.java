package buildathing.buildathing;

import buildathing.buildathing.containers.ProtectedBlocksContainer;
import buildathing.buildathing.listeners.ProtectionListener;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Level;

public final class Buildathing extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Build-a-thing is on!");

        // register commands
        this.getCommand("starta").setExecutor(new Start());
        getServer().getStructureManager().createStructure();

        // register listeners
        getServer().getPluginManager().registerEvents(new ProtectionListener(), this);

        // initiate ProtectedBlocksContainer
        ProtectedBlocksContainer.getInstance().protectedBlocks.isEmpty();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

//    List<Location> protectedBlocks;
//
//    public static class ProtectedBlocks {
//
//        public List<Location> getProtectedBlocks() {
//            return protectedBlocks;
//        }
//
//        public void addProtectedBlock(Location loc) {
//            protectedBlocks.add(loc);
//        }
//    }

}
