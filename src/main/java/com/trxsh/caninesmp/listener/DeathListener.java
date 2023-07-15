package com.trxsh.caninesmp.listener;

import com.trxsh.caninesmp.data.BanList;
import com.trxsh.caninesmp.data.DogList;
import com.trxsh.caninesmp.data.PlayerList;
import com.trxsh.caninesmp.utility.BanUtility;
import com.trxsh.caninesmp.utility.IdentityUtility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {

        if(e.getEntity() instanceof Wolf) {

            Wolf w = (Wolf) e.getEntity();

            if(IdentityUtility.dogExists(w.getUniqueId())) {

                Player p = IdentityUtility.identifyDogOwner(w.getUniqueId());

                DogList.remove(w.getUniqueId());
                PlayerList.remove(p.getUniqueId());

                //BanUtility.ban(p.getUniqueId());

                p.kickPlayer(BanUtility.getBanReason());

            }

        }

    }

}
