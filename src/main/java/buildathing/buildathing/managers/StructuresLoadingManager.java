package buildathing.buildathing.managers;

import buildathing.buildathing.Buildathing;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import sun.security.krb5.Checksum;
import sun.security.provider.MD5;

import java.io.*;
import java.net.URL;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


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

    public void downloadStructuresArchive(URL url) throws IOException {
        File structuresArchive = new File(plugin.getDataFolder(), "/structures/structures.zip");
        if (structuresArchive == null) {
            try {
                FileUtils.copyURLToFile(url, structuresArchive);
            } catch (Exception e) {
                Bukkit.getLogger().severe("Couldn't get structures from a remote source./nCheck your internet connection or firewall settings./nYOu can also download structures archive yourself from github.");
                e.printStackTrace();
            }
        } else {
            if (checkArchiveHash(structuresArchive, plugin.getConfig().getString("STRUCTURES_HASH"))) return;
            else {
                Bukkit.getLogger().info("Downloading structures archive");
                try {
                    FileUtils.copyURLToFile(url, structuresArchive);
                    unzipStructures(structuresArchive);
                } catch (Exception e) {
                    Bukkit.getLogger().severe("Couldn't get structures from a remote source./nCheck your internet connection or firewall settings./nYOu can also download structures archive yourself from github.");
                    e.printStackTrace();
                }
            }
        }

    }
    public boolean checkArchiveHash(File archive, String hash ) throws IOException {
        CRC32 crc = new CRC32();
        FileUtils.checksum(archive, crc);
        if (Long.getLong(hash) == crc.getValue()) return true;
        return false;
    }
    // TODO unzipStructures method
    public void unzipStructures(File archive) throws IOException {
        File destination = new File(plugin.getDataFolder(), "/structures/ ");
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(archive));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destination, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
