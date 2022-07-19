package buildathing.buildathing.listeners;

import buildathing.buildathing.containers.ProtectedBlocksContainer;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.lang.reflect.Array;
import java.util.List;

public class ProtectionListener implements Listener {
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        List<Location> pb = ProtectedBlocksContainer.getInstance().protectedBlocks;
        for (int i = 0; i <= pb.size(); i++) {
            if (pb.get(i).getBlock().getLocation() == event.getBlock().getLocation()) {
                event.setCancelled(true);
            }
        }
    }
}
