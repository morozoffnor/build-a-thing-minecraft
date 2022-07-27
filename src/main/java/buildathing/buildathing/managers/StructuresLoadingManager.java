package buildathing.buildathing.managers;

import buildathing.buildathing.Buildathing;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class StructuresLoadingManager {
    private Buildathing plugin = buildathing.buildathing.Buildathing.getPlugin(Buildathing.class);

    public FileConfiguration getStructure(String name) {
        File structureFile = new File(plugin.getDataFolder(), "/structures/"+name);
        FileConfiguration structure = new YamlConfiguration();
        try {
            structure.load(structureFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return structure;
    }

    // TODO downloadStructures method
    // TODO unzipStructures method
    // TODO check structure zip archive hash
}
