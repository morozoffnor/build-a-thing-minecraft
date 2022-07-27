package buildathing.buildathing;

import buildathing.buildathing.bulding.Cleaner;
import buildathing.buildathing.containers.ProtectedBlocksContainer;
import buildathing.buildathing.containers.StructureBlocksContainer;
import buildathing.buildathing.listeners.ProtectionListener;
import buildathing.buildathing.managers.GameManager;
import buildathing.buildathing.misc.GameStatus;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Level;

public final class Buildathing extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Build-a-thing is on!");

        saveDefaultConfig();
        // register commands
        this.getCommand("starta").setExecutor(new Start());
        getServer().getStructureManager().createStructure();

        // register listeners
        getServer().getPluginManager().registerEvents(new ProtectionListener(), this);

    }

    @Override
    public void onDisable() {
        // end the game
        GameManager.getInstance().gameStatus = GameStatus.FINISHED;
        // replace all protected blocks with air
        Cleaner cleaner = new Cleaner();
        cleaner.cleanUpBlocks(ProtectedBlocksContainer.getInstance().protectedBlocks);
        // clear containers
        StructureBlocksContainer.getInstance().structureBlocks.clear();
        ProtectedBlocksContainer.getInstance().protectedBlocks.clear();
    }


}
