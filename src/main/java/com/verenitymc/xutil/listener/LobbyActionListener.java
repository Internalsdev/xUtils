package com.verenitymc.xutil.listener;

import com.verenitymc.xutil.Core;
import com.verenitymc.xutil.minigame.Minigame;
import com.verenitymc.xutil.minigame.lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by Mat
 *
 * Acts as a listener for things such as pvp, building and dropping
 * items in the lobby, depending on how it's configured.
 */
public class LobbyActionListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e)
    {
        Minigame game = Core.getMinigame();
        if(game!=null)
        {
            Lobby lobby = game.getLobby();
            Player p = e.getPlayer();
            if(lobby.inLobby(p))
            {
                if(!lobby.isDroppingEnabled())
                {
                    e.setCancelled(true);
                }
            }

        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e)
    {
        if(!(e.getEntity() instanceof  Player)) return;
        Minigame game = Core.getMinigame();
        if(game!=null)
        {
            Lobby lobby = game.getLobby();
            if(lobby.inLobby( (Player) e ))
            {
                if(!lobby.isPvpEnabled())
                {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onLobbyBuild(PlayerDropItemEvent e)
    {
        Minigame game = Core.getMinigame();
        if(game!=null)
        {
            Lobby lobby = game.getLobby();
            Player p = e.getPlayer();
            if(lobby.inLobby(p))
            {
                if(!lobby.isDroppingEnabled())
                {
                    e.setCancelled(true);
                }
            }

        }
    }


}
