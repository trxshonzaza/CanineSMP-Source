package com.trxsh.caninesmp.listener;

import com.trxsh.caninesmp.data.PlayerList;
import com.trxsh.caninesmp.player.DataPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        DataPlayer p = new DataPlayer(e.getPlayer());

        if(!PlayerList.exists(e.getPlayer().getUniqueId()))
            PlayerList.add(e.getPlayer().getUniqueId(), p);
        else
            p = PlayerList.players.get(e.getPlayer().getUniqueId());

        p.setData(e.getPlayer(), true);

        if(p.getDogEntity() == null)
            p.spawnPersonalDog();

    }

}
