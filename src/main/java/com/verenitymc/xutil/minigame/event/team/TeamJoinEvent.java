package com.verenitymc.xutil.minigame.event.team;

import com.verenitymc.xutil.minigame.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

/**
 * Created by matty on 03/01/2018.
 */
public class TeamJoinEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private UUID id;

    private Team team;

    public TeamJoinEvent(Player player, Team team)
    {
        this.team = team;
        this.id = player.getUniqueId();
    }

    /**
     * Get the team joined
     * @return joined team
     */
    public Team getTeam()
    {
        return this.team;
    }

    public UUID getUUID()
    {
        return this.id;
    }


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
