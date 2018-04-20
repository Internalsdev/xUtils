package com.verenitymc.xutil.minigame.event.lobby;

import com.verenitymc.xutil.minigame.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

/**
 * Created by Mat
 *
 * Event is called when the player is removed from the lobby
 */
public class LobbyLeaveEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Lobby lobby;

    private UUID uuid;

    private Location toBeTeleportedTo;

    public LobbyLeaveEvent(Player player, Lobby lobby, Location toBeTeleportedTo)
    {
        this.lobby = lobby;
        this.uuid = player.getUniqueId();
        this.toBeTeleportedTo = toBeTeleportedTo;
    }


    public UUID getUUID()
    {
        return uuid;
    }

    /**
     * Get the lobby, can also be used to get the minigame.
     * @return The lobby
     */
    public Lobby getLobby()
    {
        return this.lobby;
    }

    /**
     * Get the player object that is leaving
     * @return The player.
     */
    public Player getPlayer()
    {
        return Bukkit.getPlayer(getUUID());
    }


    /**
     * Get where the player is being teleported to after being removed from the lobby
     * Possibly the team spaawn or something.
     * @return
     */
    public Location getToBeTeleportedTo() { return this.toBeTeleportedTo;}

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

}
