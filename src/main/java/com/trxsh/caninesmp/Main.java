package com.trxsh.caninesmp;

import com.trxsh.caninesmp.command.*;
import com.trxsh.caninesmp.command.operator.GiveReviveToken;
import com.trxsh.caninesmp.command.operator.ToggleDog;
import com.trxsh.caninesmp.command.operator.ToggleDogAttack;
import com.trxsh.caninesmp.command.operator.UnbanPlayer;
import com.trxsh.caninesmp.data.PlayerList;
import com.trxsh.caninesmp.data.file.FileManager;
import com.trxsh.caninesmp.data.file.managers.BanFileManager;
import com.trxsh.caninesmp.data.file.managers.DogLocationFileManager;
import com.trxsh.caninesmp.data.file.managers.PlayerFileManager;
import com.trxsh.caninesmp.listener.*;
import com.trxsh.caninesmp.player.DataPlayer;
import com.trxsh.caninesmp.stat.DogStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin implements Listener {

    public static Main Instance = null;

    public static FileManager p;
    public static FileManager b;
    public static FileManager dl;

    public boolean attackDogs = true;

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("Enabling Plugin (prod trxsh 2.0#1988)");

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new LeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(this, this);

        Bukkit.getPluginCommand("locatedog").setExecutor(new DogLocation());
        Bukkit.getPluginCommand("teleportdog").setExecutor(new DogTeleport());
        Bukkit.getPluginCommand("doghealth").setExecutor(new DogHealth());
        Bukkit.getPluginCommand("dogstats").setExecutor(new StatsCommand());
        Bukkit.getPluginCommand("tradedoghealth").setExecutor(new TradeDogHealth());
        Bukkit.getPluginCommand("stopattacking").setExecutor(new StopAttacking());

        // Operator Commands

        Bukkit.getPluginCommand("unbandataplayer").setExecutor(new UnbanPlayer());
        Bukkit.getPluginCommand("giverevivetoken").setExecutor(new GiveReviveToken());
        Bukkit.getPluginCommand("toggledog").setExecutor(new ToggleDog());
        Bukkit.getPluginCommand("toggledogattack").setExecutor(new ToggleDogAttack());

        p = new PlayerFileManager(new File("players.sav"));
        b = new BanFileManager(new File("banned.sav"));
        dl = new DogLocationFileManager(new File("doglocation.sav"));

        try {

            Bukkit.getLogger().info("Loading Data From Save File (prod trxsh 2.0#1988)");

            if(p.exists())
                p.load();

            if(b.exists())
                b.load();

            if(dl.exists())
                dl.load();

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

            dp.setData(p, true);

            if(dp.getDogEntity() == null)
                dp.spawnPersonalDog();

        }

        Instance = this;

        DogStats.start();

        Bukkit.getLogger().info("Enabled Plugin (prod trxsh 2.0#1988)");

    }

    @Override
    public void onDisable() {

        // data is handled in onPluginDisable()

    }

    @EventHandler
    public void onPluginDisable(PluginDisableEvent e) {

        if(e.getPlugin() == this) {

            Bukkit.getLogger().info("Disabling Plugin (prod trxsh 2.0#1988)");

            try {

                Bukkit.getLogger().info("Saving Data (prod trxsh 2.0#1988)");

                p = new PlayerFileManager(new File("players.sav"));
                b = new BanFileManager(new File("banned.sav"));
                dl = new DogLocationFileManager(new File("doglocation.sav"));

                b.save();
                p.save();
                dl.save();

                Bukkit.getLogger().info("Saved! (prod trxsh 2.0#1988)");

            } catch (IOException err) {

                Bukkit.getLogger().warning("failed to save file");

                err.printStackTrace();

            }

            for(DataPlayer p : PlayerList.players.values())
                if(p.getDogEntity() != null)
                    p.getDogEntity().remove();

            Bukkit.getLogger().info("Disabled Plugin (prod trxsh 2.0#1988)");

        }

    }

}
