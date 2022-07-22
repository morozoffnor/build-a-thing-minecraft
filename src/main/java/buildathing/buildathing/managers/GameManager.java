package buildathing.buildathing.managers;

import buildathing.buildathing.containers.ProtectedBlocksContainer;
import buildathing.buildathing.misc.GameStatus;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class GameManager {
    private static GameManager instance = null;

    private GameManager() {

    }

    // values
    public GameStatus gameStatus = GameStatus.NOTSTARTED;
    public Location gameCenter = new Location(Bukkit.getServer().getWorld("world"), 0, 0 , 0 );

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
}
