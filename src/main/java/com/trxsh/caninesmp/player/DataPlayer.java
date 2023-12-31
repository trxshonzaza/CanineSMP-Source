package com.trxsh.caninesmp.player;

import com.trxsh.caninesmp.data.DogList;
import com.trxsh.caninesmp.utility.IdentityUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

import java.util.Objects;
import java.util.UUID;

public class DataPlayer {

    public String name;

    public UUID playerUUID;
    public UUID dogUUID;

    public Player player;

    public boolean isOnline = false;
    public boolean showStats = false;

    public Wolf dogEntity;

    public Location lastDogLocation;

    public String lastDimension;

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

        if(IdentityUtility.dogOwnerExists(player)) {

            DogList.remove(IdentityUtility.getDogByPlayer(player));
            Objects.requireNonNull(IdentityUtility.getDogEntityByPlayer(player)).remove();

        }

        Wolf dog;

        if(lastDogLocation != null) {

            dog = (Wolf) player.getWorld().spawnEntity(lastDogLocation, EntityType.WOLF);
            player.sendMessage(ChatColor.GREEN + "Your Dog Was Teleported To It's Last Known Location. Please Use /locatedog Or /dogstats To Get Information On Your Dog's Location.");

        } else {

            dog = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);

        }

        dog.setCustomNameVisible(false);
        dog.setRemoveWhenFarAway(false);
        dog.setCanPickupItems(false);
        dog.setPersistent(true);

        dog.setOwner(player);
        dog.setCustomName(ChatColor.GOLD + "" + ChatColor.BOLD + player.getName() + "'s Dog");

        this.dogEntity = dog;
        this.dogUUID = dog.getUniqueId();

        lastDogLocation = dog.getLocation();
        lastDimension = dog.getWorld().getName();

        Bukkit.getLogger().info("lastDogLocation: " + lastDogLocation);
        Bukkit.getLogger().info("lastDimension: " + lastDimension);

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
