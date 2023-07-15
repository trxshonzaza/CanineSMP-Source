package com.trxsh.caninesmp.player;

import com.trxsh.caninesmp.data.DogList;
import com.trxsh.caninesmp.utility.IdentityUtility;
import com.trxsh.caninesmp.utility.NameUtility;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

import java.util.UUID;

public class DataPlayer {

    public String name;

    public UUID playerUUID;
    public UUID dogUUID;

    public Player player;

    public boolean isOnline = false;

    public Wolf dogEntity;

    public DataPlayer(Player player) {

        if(player.isOnline())
            isOnline = true;

        this.name = player.getName();
        this.playerUUID = player.getUniqueId();
        this.player = player;

    }

    public DataPlayer(OfflinePlayer player) {

        this.name = player.getName();
        this.playerUUID = player.getUniqueId();
        this.isOnline = false;

        if(player.isOnline()) {

            this.isOnline = true;
            this.player = player.getPlayer();

        }

    }

    /*public DataPlayer(Player player, Wolf dogEntity) {  *DEPRECATED*

        this.name = player.getName();
        this.playerUUID = player.getUniqueId();
        this.player = player;
        this.dogEntity = dogEntity;

        assert this.dogEntity != null;

        if(this.dogEntity.isValid()) {

            this.dogUUID = this.dogEntity.getUniqueId();
            this.dogEntity.teleport(player.getLocation());
            dogExistsInWorld = true;

            if(DogList.playerExists(playerUUID))
                DogList.replace(this.dogUUID, player);
            else
                DogList.add(this.dogUUID, player);

        } else if(dogExistsInWorld && !dogEntity.isValid()) {

            this.dogEntity = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
            this.dogUUID = this.dogEntity.getUniqueId();
            dogExistsInWorld = true;

            if(DogList.playerExists(playerUUID))
                DogList.replace(this.dogUUID, player);
            else
                DogList.add(this.dogUUID, player);

        } else if(!dogExistsInWorld && !dogEntity.isValid()) {

            this.dogEntity = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
            this.dogUUID = this.dogEntity.getUniqueId();
            dogExistsInWorld = true;

            if(DogList.playerExists(playerUUID))
                DogList.replace(this.dogUUID, player);
            else
                DogList.add(this.dogUUID, player);

        }

    }*/

    public void spawnPersonalDog() {

        if(!isOnline)
            return;

        if(dogEntity != null)
            dogEntity.remove();

        if(IdentityUtility.dogOwnerExists(player))
            DogList.remove(IdentityUtility.getDogByPlayer(player));

        Wolf dog = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);

        dog.setOwner(player);
        dog.setCustomName(ChatColor.GOLD + "" + ChatColor.BOLD + player.getName() + "'s Dog");

        this.dogEntity = dog;
        this.dogUUID = dog.getUniqueId();

        DogList.add(dog.getUniqueId(), player.getUniqueId());

    }

    public void setData(Player player, boolean online) {

        this.player = player;
        this.isOnline = online;
        this.playerUUID = player.getUniqueId();

    }

    public Player getPlayer() {

        return player;

    }

    public void setDogEntity(Wolf entity) {

        dogEntity = entity;

    }

    public Wolf getDogEntity() {

        return dogEntity;

    }

}
