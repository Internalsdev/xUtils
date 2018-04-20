package com.verenitymc.xutil.minigame.event.lobby;

import com.verenitymc.xutil.minigame.Minigame;
import com.verenitymc.xutil.minigame.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.persistence.Lob;

import java.util.UUID;

/**
 * Created by Mat
 *
 * Called when a player is sent to the lobby.
 */
public class LobbyJoinEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Lobby lobby;

    private UUID uuid;

    public LobbyJoinEvent(Player player, Lobby lobby)
    {
        this.lobby = lobby;
        this.uuid = player.getUniqueId();
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
     * Get the player object that is joining
     * @return The player.
     */
    public Player getPlayer()
    {
        return Bukkit.getPlayer(getUUID());
    }


    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }
}
