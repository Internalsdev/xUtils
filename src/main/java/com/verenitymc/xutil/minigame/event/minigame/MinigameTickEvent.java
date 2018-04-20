package com.verenitymc.xutil.minigame.event.minigame;

import com.verenitymc.xutil.minigame.Minigame;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Mat
 *
 * Whenever the minigame ticks (the timer goes) this event is called.
 * Handle it however you need to, remember you can reactivate and deactivate
 * the timer within the MiniGameSettings class.
 */
public class MinigameTickEvent extends Event {


    private static final HandlerList handlers = new HandlerList();

    private Minigame minigame;

    public MinigameTickEvent(Minigame minigame)
    {
        this.minigame = minigame;
    }

    public Minigame getMinigame()
    {
        return this.minigame;
    }



    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }
}
