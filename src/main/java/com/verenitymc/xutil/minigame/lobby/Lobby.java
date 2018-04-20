package com.verenitymc.xutil.minigame.lobby;

import com.verenitymc.xutil.minigame.Minigame;
import com.verenitymc.xutil.minigame.event.lobby.LobbyJoinEvent;
import com.verenitymc.xutil.minigame.event.lobby.LobbyLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mat
 *
 * Acts as a "lobby", in the lobby you can toggle things such the
 * spawn location and whether or not pvp is active, building etc.
 *
 * ALWAYS REMEMBER TO SET THE LOBBY LOCATIONS, if there's no lobby location the they can't send the player to the lobby
 */
public class Lobby {

    private Minigame minigame; //The actual minigame.

    private List<UUID> members; //Members of the lobby

    private Location lobbyLocaton; //Lobby worlds should be in a different world to the game world, needs to be set!

    private boolean pvpEnabled; //Whether pvp is enabled

    private boolean droppingEnabled; //Whether dropping items is allowed

    private boolean buildingAvailable; //Whether players can be allowed to build

    public Lobby(Minigame minigame)
    {
        this.minigame = minigame;
        this.pvpEnabled = false;
        this.droppingEnabled = false;
        this.buildingAvailable = false;
        this.members = new ArrayList<>();
    }

    public boolean canPvP()
    {
        return this.pvpEnabled;
    }


    public void setPvpEnabled(boolean enabled)
    {
        this.pvpEnabled = enabled;
    }

    public boolean isBuildingAvailable()
    {
        return buildingAvailable;
    }

    public boolean isDroppingEnabled()
    {
        return droppingEnabled;
    }

    public boolean isPvpEnabled()
    {
        return pvpEnabled;
    }

    public void setBuildingAvailable(boolean buildingAvailable)
    {
        this.buildingAvailable = buildingAvailable;
    }

    public void setDroppingEnabled(boolean droppingEnabled)
    {
        this.droppingEnabled = droppingEnabled;
    }



    public Minigame getMinigame()
    {
        return minigame;
    }


    /**
     * Get the list of people in the lobby, important that this
     * is monitored correctly as issues can arise.
     * @return
     */

    public List<UUID> getMembers() { return members; }


    /**
     * Checks if a certain player is in a lobby
     * @param p - Player to query
     * @return if the player is inside the lobby.
     */

    public boolean inLobby(Player p){return getMembers().contains(p.getUniqueId());}


    /**
     * Can be used to check if a multiple set of players are in a lobby
     * @param players - unlimited arguments of players to check against
     * @return Whether or not they are all in the lobby.
     *
     */
    public boolean playersInLobby(Player... players)
    {
        for(Player p : players)
        {
            if(!getMembers().contains(p.getUniqueId()))
            {
                return false;
            }
        }
        return true;
    }

    public Location getLobbyLocaton(){ return lobbyLocaton; }

    public void setLobbyLocaton(Location lobbyLocaton){ this.lobbyLocaton = lobbyLocaton;}




    /**
     * Sends the player to the lobby, this will call a join event.
     * @param p
     */
    public void addAndSendToLobby(Player p)
    {
        if(getLobbyLocaton() != null && (!getMembers().contains(p.getUniqueId())))
        {
            getMembers().add(p.getUniqueId());
            p.teleport(getLobbyLocaton());
            Event joinEvent = new LobbyJoinEvent(p, this);
            Bukkit.getServer().getPluginManager().callEvent(joinEvent);
        }
    }


    /**
     * Removes a player from the lobby, also teleports them to a certain location
     * @param p - Player to remove.
     * @param toBeTeleportedTo - The place to be teleported to, usually a the player's team spawn.
     */

    public void removeAndTeleportAwayFromLobby(Player p, Location toBeTeleportedTo)
    {
        if(getMembers().contains(p.getUniqueId()))
        {
            getMembers().remove(p.getUniqueId());
            if(toBeTeleportedTo != null)
            {
                p.teleport(toBeTeleportedTo);
            }
            Event leaveEvent = new LobbyLeaveEvent(p, this, toBeTeleportedTo);
            Bukkit.getServer().getPluginManager().callEvent(leaveEvent);
        }
    }




}
