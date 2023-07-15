package com.trxsh.caninesmp;

import com.trxsh.caninesmp.command.DogLocation;
import com.trxsh.caninesmp.command.DogTeleport;
import com.trxsh.caninesmp.data.PlayerList;
import com.trxsh.caninesmp.data.file.FileManager;
import com.trxsh.caninesmp.data.file.managers.PlayerFileManager;
import com.trxsh.caninesmp.listener.DamageListener;
import com.trxsh.caninesmp.listener.DeathListener;
import com.trxsh.caninesmp.listener.JoinListener;
import com.trxsh.caninesmp.listener.LeaveListener;
import com.trxsh.caninesmp.player.DataPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    public static Main Instance = null;

    public static FileManager p;
    public static FileManager b;

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("Enabling Plugin (prod trxsh 2.0#1988)");

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new LeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);

        Bukkit.getPluginCommand("locatedog").setExecutor(new DogLocation());
        Bukkit.getPluginCommand("teleportdog").setExecutor(new DogTeleport());

        p = new PlayerFileManager(new File("players.sav"));
        b = new PlayerFileManager(new File("banned.sav"));

        try {

            Bukkit.getLogger().info("Loading Data From Save File (prod trxsh 2.0#1988)");

            if(p.exists())
                p.load();

            if(b.exists())
                b.load();

            Bukkit.getLogger().info("Loaded! (prod trxsh 2.0#1988)");

        }catch(IOException e){

            Bukkit.getLogger().warning("failed to load file");

            e.printStackTrace();

        }

        for(Player p : Bukkit.getOnlinePlayers()) {

            DataPlayer dp = new DataPlayer(p);

            if(!PlayerList.exists(p.getUniqueId()))
                PlayerList.add(p.getUniqueId(), dp);
            else
                dp = PlayerList.players.get(p.getUniqueId());

            if(!dp.isOnline)
                dp.setData(p, true);

            if(dp.getDogEntity() == null)
                dp.spawnPersonalDog();

        }

        Instance = this;

        Bukkit.getLogger().info("Enabled Plugin (prod trxsh 2.0#1988)");

    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info("Disabling Plugin (prod trxsh 2.0#1988)");

        try {

            Bukkit.getLogger().info("Saving Data (prod trxsh 2.0#1988)");

            p = new PlayerFileManager(new File("players.sav"));
            b = new PlayerFileManager(new File("banned.sav"));

            b.save();
            p.save();

            Bukkit.getLogger().info("Saved! (prod trxsh 2.0#1988)");

        } catch (IOException e) {

            Bukkit.getLogger().warning("failed to save file");

            e.printStackTrace();

        }

        Bukkit.getLogger().info("Disabled Plugin (prod trxsh 2.0#1988)");

    }
}
