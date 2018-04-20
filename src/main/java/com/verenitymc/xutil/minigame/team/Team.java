package com.verenitymc.xutil.minigame.team;

import com.verenitymc.xutil.Core;
import com.verenitymc.xutil.minigame.Minigame;
import com.verenitymc.xutil.minigame.aesthetic.NametagChanger;
import com.verenitymc.xutil.minigame.enums.TagAction;
import com.verenitymc.xutil.minigame.event.team.TeamJoinEvent;
import com.verenitymc.xutil.minigame.event.team.TeamLeaveEvent;
import org.bukkit.BanEntry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitTask;
import sun.invoke.util.BytecodeName;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * Created by Mat
 *
 * Acts as a base class for all teams, each team
 * has several properties, this class can be extended and modified as needed.
 *
 */
public class Team
{

    private static List<Team> allTeams = new ArrayList<>();
    public static List<Team> getTeams(){ return allTeams; }

    private String name;
    private ChatColor teamColour;
    private List<UUID> members;

    public Team(String name, ChatColor teamColour)
    {
        this.name = name;
        this.teamColour = teamColour;
        this.members = new ArrayList<>();
        if(Core.getMinigame() != null)
        {
            Core.getMinigame().getTeams().add(this);
        }
        allTeams.add(this);
    }

    /**
     * Get name of the team
     * @return The raw name of the team.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the colour of the team
     * @return - The colour for the team.
     */
    public ChatColor getTeamColour()
    {
        return teamColour;
    }

    /**
     * Gets all members
     * @return - All members
     */
    public List<UUID> getMembers()
    {
        return members;
    }


    /**
     * Easy to use method to quickly put team coloured tags above their head depending
     * on the team colour
     * @param colourTags - whether or not to colour the tags.
     */
    public void setColouredTags(boolean colourTags)
    {
        getMembers().forEach( member ->
        {
            Player p = Bukkit.getPlayer(member);
            if(p!=null)
            {
                ChatColor tagColour = colourTags ? getTeamColour() : ChatColor.WHITE;
                NametagChanger.setPlayerName(p, tagColour + "", "", TagAction.UPDATE );
            }
        });
    }


    /**
     * Add a player to a team, this will call a team join event.
     * THIS FUNCTION WILL NOT WORK IF A PLAYER ALREADY HAS HIS TEAM
     * @param p - Player to add,
     */
    public void addToTeam(Player p)
    {
        if(getTeam(p)!=null)
        {
            getMembers().add(p.getUniqueId());
            Event joinEvent = new TeamJoinEvent(p, this);
            Bukkit.getPluginManager().callEvent(joinEvent);
        }
    }


    /**
     * Removes player from team, when a player is removed from a team
     * by the developer, this will call the TeamLeaveEvent.
     * Only works if a player is in the team
      * @param p
     */
    public void removeFromTeam(Player p)
    {
        if(getMembers().contains(p.getUniqueId()))
        {
            getMembers().remove(p.getUniqueId());
            Event leaveEvent = new TeamLeaveEvent(p, this);
            Bukkit.getPluginManager().callEvent(leaveEvent);
        }
    }


    public static Team getTeam(String name)
    {
        return getTeams().stream().filter(team -> team.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }


    /**
     * Get the team that a player is allocated on
     * @param p
     * @return
     */
    public static Team getTeam(Player p)
    {
       for(Team t : getTeams())
       {
           for(UUID id : t.getMembers())
           {
               if(p.getUniqueId().equals(id))
               {
                   return t;
               }
           }
       }
       return null;
    }

}
