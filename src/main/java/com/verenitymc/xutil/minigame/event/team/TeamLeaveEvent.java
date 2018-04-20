package com.verenitymc.xutil.minigame.event.team;


import com.verenitymc.xutil.minigame.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

/**
 * Created by Mat
 *
 * Event is called when someone is removed from the team.
 *
 * THIS EVENT IS ALSO CALLED WHEN A PLAYER LEAVES THE GAME WHILE IN THE TEAM, AS IT WILL REMOVE THEM FROM THE TEAM MEMBER LIST/
 */
public class TeamLeaveEvent extends Event {


    private static final HandlerList handlers = new HandlerList();

    private UUID id;

    private Team team;

    public TeamLeaveEvent(Player player, Team team)
    {
        this.team = team;
        this.id = player.getUniqueId();
    }

    /**
     * Get the team that is left.
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
