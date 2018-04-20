package com.verenitymc.xutil.minigame.map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mat
 *
 * SpawnPoint object, goes into the map, which goes in the minigame.
 *
 * Handles teams or per player spawnpoint.
 */
public class SpawnPoint {

    private static List<SpawnPoint> spawnPointList = new ArrayList<>();
    public static List<SpawnPoint> getSpawnPoints(){return spawnPointList;}

    private Location baseLocation;

    private List<Player> players;

    public SpawnPoint(Location location)
    {
        players = new ArrayList<>();
        this.baseLocation = location;

        spawnPointList.add(this);
    }

    public Location getLocation(){
        return baseLocation;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void addPlayer(Player p){
        getPlayers().add(p);
    }

    public void removePlayer(Player p){
        getPlayers().remove(p);
    }


    public static SpawnPoint findEmptySpawnPoint(){
        return getSpawnPoints().stream().filter(point -> point.getPlayers().size()==0).findFirst().orElse(null);
    }

    public static SpawnPoint findHalfFilledSpawnPoint(){
        return getSpawnPoints().stream().filter(point -> point.getPlayers().size()==1).findFirst().orElse(null);
    }

}
