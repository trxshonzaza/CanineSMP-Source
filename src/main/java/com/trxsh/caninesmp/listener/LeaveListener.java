package com.trxsh.caninesmp.listener;

import com.trxsh.caninesmp.data.PlayerList;
import com.trxsh.caninesmp.player.DataPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {

        if(PlayerList.exists(e.getPlayer().getUniqueId())) {

            DataPlayer p = PlayerList.players.get(e.getPlayer().getUniqueId());

            if(p.getDogEntity() != null) {

                p.getDogEntity().remove();
                p.setDogEntity(null);

            }

        }

    }

}
