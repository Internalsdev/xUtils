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
 * Acts as an event that's called when a player leaves the queue
 */
public class ServerQueueLeaveEvent extends Event implements Cancellable {

    private static final HandlerList handlerList = new HandlerList();

    private ServerQueue leftQueue;
    private ServerQueue nextQueue;

    private UUID id;

    private boolean cancelled;

    public ServerQueueLeaveEvent(Player p, ServerQueue leaving, ServerQueue nextQueue)
    {
        this.leftQueue = leaving;
        this.nextQueue = nextQueue;
        this.id = p.getUniqueId();
    }

    public ServerQueue getNextQueue()
    {
        return nextQueue;
    }

    public ServerQueue getLeftQueue()
    {
        return this.leftQueue;
    }

    public Player getPlayer()
    {
        return Bukkit.getServer().getPlayer(id);
    }

    public boolean isTransferringServerQueue()
    {
        return getNextQueue() != null;
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
