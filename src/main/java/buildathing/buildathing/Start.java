package buildathing.buildathing;

import buildathing.buildathing.bulding.BlockBuilder;
import buildathing.buildathing.containers.ProtectedBlocksContainer;
import buildathing.buildathing.managers.GameManager;
import buildathing.buildathing.managers.StructuresLoadingManager;
import buildathing.buildathing.misc.GameStatus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;


public class Start implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // command is player-only
        if (!(sender instanceof Player)) return false;
        Buildathing plugin = buildathing.buildathing.Buildathing.getPlugin(Buildathing.class);
        Player player = (Player) sender;

        GameManager.getInstance().gameStatus = GameStatus.STARTING;

        BlockBuilder builder = new BlockBuilder();
        StructuresLoadingManager loader = new StructuresLoadingManager();

        builder.createPlatform(player.getLocation().add(0,10,0), plugin.getConfig().getInt("PLOT_RADIUS"));
        builder.createStructure(loader.getStructure("test.yml"),player.getLocation().add(-4,11,-4));

        player.sendMessage("the platform has spawned");
        GameManager.getInstance().gameStatus = GameStatus.RUNNING;

        return true;
    }
}
