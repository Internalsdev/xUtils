package com.verenitymc.xutil.minigame.event.queue;

import com.verenitymc.xutil.minigame.queue.ServerQueue;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

/**
 * Created by Mat
 *
 * An event called when a player joins queue
 *
 * Also may hold data about the queue that they have just left
 */
public class ServerQueueJoinEvent extends Event implements Cancellable {


    private static final HandlerList handlerList = new HandlerList();

    private ServerQueue joinedQueue;
    private ServerQueue previousQueues;

    private UUID id;

    private boolean cancelled;

    public ServerQueueJoinEvent(Player p, ServerQueue joined, ServerQueue previousQueues)
    {
        this.joinedQueue = joined;
        this.previousQueues = previousQueues;
        this.id = p.getUniqueId();
    }

    public ServerQueue getJoinedQueue()
    {
        return joinedQueue;
    }

    public ServerQueue getPreviousQueue()
    {
        return previousQueues;
    }

    public Player getPlayer()
    {
        return Bukkit.getServer().getPlayer(id);
    }

    public boolean hadPreviousQueue()
    {
        return getPreviousQueue() != null;
    }

    @Override
    public boolean isCancelled()
    {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b)
    {
        this.cancelled = b;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlerList;
    }
}

