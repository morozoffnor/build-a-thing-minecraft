package buildathing.buildathing;

import buildathing.buildathing.bulding.BlockBuilder;
import buildathing.buildathing.containers.ProtectedBlocksContainer;
import buildathing.buildathing.managers.GameManager;
import buildathing.buildathing.misc.GameStatus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public class Start implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // command is player-only
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        Location loc = player.getLocation().add(10.0, 0.0, 10.0);
        GameManager.getInstance().gameStatus = GameStatus.STARTING;
        loc.getBlock().setType(Material.BEDROCK);
        ProtectedBlocksContainer.getInstance().protectedBlocks.add(loc);

        BlockBuilder builder = new BlockBuilder();
        builder.createPlatform(player.getLocation().add(0,30,0), 10);

        player.sendMessage("the platform has spawned");
        GameManager.getInstance().gameStatus = GameStatus.RUNNING;

        return true;
    }
}
