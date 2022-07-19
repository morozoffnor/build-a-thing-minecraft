package buildathing.buildathing.listeners;

import buildathing.buildathing.Buildathing.ProtectedBlocks;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.lang.reflect.Array;
import java.util.List;

public class ProtectionListener implements Listener {
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        ProtectedBlocks pb = new ProtectedBlocks();
        List<Location> protectedBlocks = pb.getProtectedBlocks();
        for (int i = 0; i <= protectedBlocks.size(); i++) {
            if (protectedBlocks.get(i).getBlock().getLocation() == event.getBlock().getLocation()) {
                event.setCancelled(true);
            }
        }
    }
}
