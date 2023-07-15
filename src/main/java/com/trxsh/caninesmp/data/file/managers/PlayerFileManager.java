package com.trxsh.caninesmp.data.file.managers;

import com.trxsh.caninesmp.data.PlayerList;
import com.trxsh.caninesmp.data.file.FileManager;
import com.trxsh.caninesmp.player.DataPlayer;
import com.trxsh.caninesmp.utility.NameUtility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.UUID;

public class PlayerFileManager extends FileManager {

    public PlayerFileManager(File file) {
        super(file);
    }

    @Override
    public void save() throws IOException {

        FileWriter writer = new FileWriter(file);
        StringBuilder sb = new StringBuilder();

        for(UUID id : PlayerList.players.keySet())
            sb.append(id + NameUtility.getSeparator());

        writer.write(sb.toString());

    }

    @Override
    public void load() throws IOException {

        for(String line : Files.readAllLines(file.toPath())) {

            DataPlayer p = null;

            UUID id = UUID.fromString(line);
            OfflinePlayer player = Bukkit.getOfflinePlayer(id);

            if(player.isOnline())
                p = new DataPlayer(Objects.requireNonNull(player.getPlayer()));
            else
                p = new DataPlayer(player);

            PlayerList.add(id, p);

        }

    }
}
