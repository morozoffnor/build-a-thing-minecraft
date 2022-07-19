package buildathing.buildathing;

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
        Player player = (Player) sender;
        Buildathing.ProtectedBlocks pb = new Buildathing.ProtectedBlocks();
        if (sender instanceof Player) {
            Location loc = player.getLocation().add(10.0,0.0,10.0);
            loc.getBlock().setType(Material.BEDROCK);
            pb.addProtectedBlock(loc);

        }
        player.sendMessage(String.valueOf(pb.getProtectedBlocks().size()));
        return true;
    }
}
