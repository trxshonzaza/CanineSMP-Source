package com.trxsh.caninesmp.listener;

import com.trxsh.caninesmp.data.DogList;
import com.trxsh.caninesmp.data.PlayerList;
import com.trxsh.caninesmp.player.DataPlayer;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {

        if(PlayerList.exists(e.getPlayer().getUniqueId())) {

            DataPlayer p = PlayerList.players.get(e.getPlayer().getUniqueId());

            p.isOnline = false;
            p.showStats = false;

            if(p.dogUUID != null && DogList.exists(p.dogUUID))
                DogList.remove(p.dogUUID);

            if(p.getDogEntity() != null) {

                Location l = new Location(p.dogEntity.getWorld(),
                        p.dogEntity.getLocation().getBlockX(),
                        p.dogEntity.getLocation().getBlockY(),
                        p.dogEntity.getLocation().getBlockZ());

                p.lastDogLocation = l;

                Chunk c = p.getDogEntity().getLocation().getChunk();

                if(!c.isLoaded())
                    c.load();

                p.getDogEntity().remove();
                p.setDogEntity(null);
                p.dogUUID = null;

                c.unload();

            } else {

                Chunk c = p.lastDogLocation.getChunk();

                if(!c.isLoaded())
                    c.load();

                if(p.getDogEntity() != null) {

                    Location l = new Location(p.dogEntity.getWorld(),
                            p.dogEntity.getLocation().getBlockX(),
                            p.dogEntity.getLocation().getBlockY(),
                            p.dogEntity.getLocation().getBlockZ());

                    p.lastDogLocation = l;

                    p.getDogEntity().remove();
                    p.setDogEntity(null);
                    p.dogUUID = null;

                }

                c.unload();

            }

        }

    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {

        if(PlayerList.exists(e.getPlayer().getUniqueId())) {

            DataPlayer p = PlayerList.players.get(e.getPlayer().getUniqueId());

            p.isOnline = false;
            p.showStats = false;

            if(p.dogUUID != null && DogList.exists(p.dogUUID))
                DogList.remove(p.dogUUID);

            if(p.getDogEntity() != null) {

                Location l = new Location(p.dogEntity.getWorld(),
                        p.dogEntity.getLocation().getBlockX(),
                        p.dogEntity.getLocation().getBlockY(),
                        p.dogEntity.getLocation().getBlockZ());

                p.lastDogLocation = l;

                Chunk c = p.getDogEntity().getLocation().getChunk();

                if(!c.isLoaded())
                    c.load();

                p.getDogEntity().remove();
                p.setDogEntity(null);
                p.dogUUID = null;

                c.unload();

            } else {

                Chunk c = p.lastDogLocation.getChunk();

                if (!c.isLoaded())
                    c.load();

                if (p.getDogEntity() != null) {

                    Location l = new Location(p.dogEntity.getWorld(),
                            p.dogEntity.getLocation().getBlockX(),
                            p.dogEntity.getLocation().getBlockY(),
                            p.dogEntity.getLocation().getBlockZ());

                    p.lastDogLocation = l;

                    p.getDogEntity().remove();
                    p.setDogEntity(null);
                    p.dogUUID = null;

                }

                c.unload();

            }

        }

    }

}
