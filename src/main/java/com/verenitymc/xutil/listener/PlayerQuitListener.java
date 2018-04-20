package com.verenitymc.xutil.listener;

import com.verenitymc.xutil.Core;
import com.verenitymc.xutil.minigame.Minigame;
import com.verenitymc.xutil.minigame.lobby.Lobby;
import com.verenitymc.xutil.minigame.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by matty on Mat
 *
 * Handles things such as the removal of team members and also lobby members automatically.
 */
public class PlayerQuitListener implements Listener {

   @EventHandler
   public void onQuit(PlayerQuitEvent e)
   {
       Player p = e.getPlayer();

       Team team =  Team.getTeam(p);
       if(team != null)
       {
           team.removeFromTeam(p);
       }

       Minigame minigame = Core.getMinigame();

       if(minigame != null)
       {
           Lobby lobby = minigame.getLobby();
           if(lobby.inLobby(p))
           {
               lobby.removeAndTeleportAwayFromLobby(p, null); //This will call a leave event
           }
       }
   }

}
