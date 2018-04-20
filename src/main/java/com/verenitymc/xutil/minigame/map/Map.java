package com.verenitymc.xutil.minigame.map;

import com.verenitymc.xutil.Core;
import com.verenitymc.xutil.minigame.Minigame;
import com.verenitymc.xutil.minigame.enums.GameState;
import com.verenitymc.xutil.minigame.enums.LoadReason;
import com.verenitymc.xutil.minigame.lobby.Lobby;
import com.verenitymc.xutil.util.CommonUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mat
 * Used to handle the whole map / world, there should only be one map per server
 * However there could be the rotation, so there's the option to have a list
 * of them just in case we need to handle them dynamically.
 */
public class Map
{

    private static List<Map> allMaps = new ArrayList<>();

    private List<Location> spawnPoints;

    private World mapWorld;

    private String worldName;

    private BorderManager borderManager;

    public Map(World world, List<Location> spawnPoints)
    {
        this.spawnPoints = spawnPoints;

        this.borderManager = new BorderManager(this);

        this.mapWorld = world;

        spawnPoints.forEach( location -> new SpawnPoint(location));

        this.worldName = getMapWorld().getName();

        allMaps.add(this);
    }


    /**
     * Get the spawn points within the given map.
     * @return All spawn points that players will join on the map.
     */

    public List<Location> getSpawnPoints()
    {
        return this.spawnPoints;
    }


    /**
     * Get the border manager for the map (there can be more than one border manager remember)
     * @return
     */
    public BorderManager getBorderManager()
    {
        return this.borderManager;

    }


    /**
     * This function is to be called PRIOR TO THE MAP BEING DESTROYED
     *
     * Usually at the start of the game.
     */
    public void saveWorldForRestore()
    {
        Core.log("Saving world for restore...");
        getMapWorld().save();
        Core.log("Copying...");
        CommonUtil.copyWorld(new File(mapWorld.getName()), new File(mapWorld.getName()+"_Copy"));
        Core.log("Copied!");
    }


    public World getMapWorld()
    {
        return this.mapWorld;
    }


    /**
     * This function should only be called when regenerating a world.
     * @param restart - Whether to restart the server afterwards, sometimes just best to do.
     */
    public void beginRegenerationOfMap(boolean restart)
    {
        Core.getServerData().setGameStateSync(GameState.REGENERATING);
        kickPlayersToLobby();
        unloadAndCopyMap();
        updateFields();
        if(restart)
        {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
        }
    }


    private void kickPlayersToLobby()
    {
        Lobby lobby = Core.getMinigame().getLobby();
        if(lobby.getLobbyLocaton() != null)
        {
            Bukkit.getOnlinePlayers().forEach(pl -> pl.teleport(lobby.getLobbyLocaton()));
        } else {
            Bukkit.getOnlinePlayers().forEach( pl -> pl.kickPlayer(ChatColor.RED + "No lobby to teleport to, sorry!"));
        }
    }


    public void unloadAndCopyMap()
    {
        CommonUtil.unloadWorld(worldName);
        Core.log("Coping world " + worldName+"_Copy" + " to " + worldName);
        CommonUtil.copyWorld(new File(Bukkit.getWorldContainer(), worldName+"_Copy"), new File(Bukkit.getWorldContainer(), worldName));
        CommonUtil.loadWorld(worldName);
        CommonUtil.deleteWorld(new File(worldName+"_Copy"));
    }


    private void updateFields()
    {
        World newWorldInstance = Bukkit.getWorld(worldName);

        this.mapWorld = newWorldInstance;
        List<Location> newSpawnLocationInstances = new ArrayList<Location>(); //Need to re register instances of location due to the new world.

        spawnPoints.forEach( location -> {
            newSpawnLocationInstances.add(new Location(getMapWorld(), location.getX(), location.getY(), location.getZ()));
        });

        spawnPoints = newSpawnLocationInstances;

        Bukkit.getWorld(worldName).save(); //Save the new world.

        Core.log("Saved!");
    }




    public static List<Map> getMaps()
    {
        return allMaps;
    }




}
