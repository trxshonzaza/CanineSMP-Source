package com.trxsh.caninesmp.data.file.managers;

import com.trxsh.caninesmp.data.PlayerList;
import com.trxsh.caninesmp.data.file.FileManager;
import com.trxsh.caninesmp.player.DataPlayer;
import com.trxsh.caninesmp.utility.NameUtility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

public class DogLocationFileManager extends FileManager {


    public DogLocationFileManager(File file) {
        super(file);
    }

    @Override
    public void save() throws IOException {

        FileWriter writer = new FileWriter(file);
        StringBuilder sb = new StringBuilder();

        for(DataPlayer player : PlayerList.players.values()) {

            Bukkit.getLogger().info("Player: " + player.name);
            Bukkit.getLogger().info("Dog Location: " + player.lastDogLocation);
            Bukkit.getLogger().info("Dimension: " + player.lastDimension);

            if (player.lastDogLocation == null) {

                Bukkit.getLogger().warning("Skipping player " + player.name + " due to null lastDogLocation");

                continue;

            }

            Location dogLocation = player.lastDogLocation;
            String dimension = player.lastDimension;

            sb.append(player.playerUUID)
                    .append("::")
                    .append((int)dogLocation.getX())
                    .append("==")
                    .append((int)dogLocation.getY())
                    .append("=!")
                    .append((int)dogLocation.getZ())
                    .append("!=")
                    .append(dimension)
                    .append(NameUtility.getSeparator());

        }

        writer.write(sb.toString());
        writer.close();

    }

    @Override
    public void load() throws IOException {

        for(String line : Files.readAllLines(file.toPath())) {

            String[] data = line.split("::");
            if (data.length != 2) {

                Bukkit.getLogger().warning("Invalid data format in line: " + line);

                continue;

            }

            UUID playerID = UUID.fromString(data[0]);

            String locationData = data[1];

            String[] locationInfo = locationData.split("==|=!|!=");

            if (locationInfo.length != 4) {

                Bukkit.getLogger().warning("Invalid location data format in line: " + line);

                continue;

            }

            double x = Double.parseDouble(locationInfo[0]);
            double y = Double.parseDouble(locationInfo[1]);
            double z = Double.parseDouble(locationInfo[2]);

            String worldName = locationInfo[3];

            World world = Bukkit.getWorld(worldName);

            if (world == null) {

                Bukkit.getLogger().warning("Invalid world name in line: " + line);

                continue;

            }

            Location l = new Location(world, x, y, z);

            DataPlayer player = PlayerList.players.get(playerID);

            if (player == null) {

                Bukkit.getLogger().warning("Player with UUID " + playerID + " not found");

                continue;

            }

            player.lastDogLocation = l;
            player.lastDimension = world.getName();

        }

    }

}
