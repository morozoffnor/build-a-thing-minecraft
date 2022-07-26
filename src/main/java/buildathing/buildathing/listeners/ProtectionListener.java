package buildathing.buildathing.listeners;

import buildathing.buildathing.containers.ProtectedBlocksContainer;
import buildathing.buildathing.managers.GameManager;
import buildathing.buildathing.misc.GameStatus;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.text.DecimalFormat;
import java.util.List;

public class ProtectionListener implements Listener {
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        // don't do this if the game isn't running
        if (!(GameManager.getInstance().gameStatus == GameStatus.RUNNING)) return;
        // get list of protected blocks
        List<Location> pb = ProtectedBlocksContainer.getInstance().protectedBlocks;

        Player player = event.getPlayer();
        // debug things
        player.sendMessage("Protected blocks are: " + pb);
        player.sendMessage("Event block is " + event.getBlock().getLocation());
        //
        DecimalFormat df = new DecimalFormat("#.0");

        // I haven't figured out how to make this nightmare better, hope to do it later (no)
        double eventBlockX = Double.parseDouble(df.format(event.getBlock().getLocation().getBlockX()));
        double eventBlockY = Double.parseDouble(df.format(event.getBlock().getLocation().getBlockY()));
        double eventBlockZ = Double.parseDouble(df.format(event.getBlock().getLocation().getBlockZ()));
        for (Location location : pb) {
            double pBlockX = Double.parseDouble(df.format(location.getBlock().getLocation().getBlockX()));
            double pBlockY = Double.parseDouble(df.format(location.getBlock().getLocation().getBlockY()));
            double pBlockZ = Double.parseDouble(df.format(location.getBlock().getLocation().getBlockZ()));

            if (eventBlockX == pBlockX && eventBlockY == pBlockY && eventBlockZ == pBlockZ) {
                event.setCancelled(true);
            }
        }
    }
}
