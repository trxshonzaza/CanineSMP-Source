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

        for(UUID id : PlayerList.players.keySet()) {

            Location dogLocation = PlayerList.players.get(id).lastDogLocation;
            String dimension = PlayerList.players.get(id).lastDimension;

            sb.append(id)
                    .append("::")
                    .append(dogLocation.getX())
                    .append("==")
                    .append(dogLocation.getY())
                    .append("=!")
                    .append(dogLocation.getZ())
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

            UUID playerID = UUID.fromString(line.split("::")[0]);

            double x = Double.parseDouble(line.split("==")[0].split("::")[1]);
            double y = Double.parseDouble(line.split("=!")[0].split("==")[1]);
            double z = Double.parseDouble(line.split("!=")[0].split("!=")[1]);

            World world = Bukkit.getWorld(line.split("!=")[1]);

            Location l = new Location(world, x, y, z);

            DataPlayer player = PlayerList.players.get(playerID);

            player.lastDogLocation = l;

            assert world != null;
            player.lastDimension = world.getName();

        }

    }
}
