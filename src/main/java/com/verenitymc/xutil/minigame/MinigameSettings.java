package com.verenitymc.xutil.minigame;

import com.verenitymc.xutil.Core;
import com.verenitymc.xutil.minigame.event.minigame.MinigameTickEvent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by Mat
 *
 * Acts as a configuration base for common values within the minigame
 * Keep all your stuff in one place.
 */
public class MinigameSettings  {

    private int tickDuration; //Acts as the time inbetween the timer ticks, by default it should be one.

    private boolean timerActive; //Used for setting if the timer is active or not, false by default.

    private BukkitTask gameTask; //The timer that the game will use.

    private Minigame minigame;

    public MinigameSettings(Minigame minigame)
    {
        this.minigame = minigame;
        loadDefaultValues();
    }


    private void loadDefaultValues()
    {
        this.tickDuration = 1;
        timerActive = false;
        loadTask();
    }

    private void loadTask()
    {
        gameTask = new BukkitRunnable()
        {
            public void run()
            {
                if(timerActive()) //Check timer is active first
                {
                    MinigameTickEvent tickEvent = new MinigameTickEvent(getMinigame());
                    Bukkit.getServer().getPluginManager().callEvent(tickEvent); //Call the tick event.
                }
            }
        }.runTaskTimer(Core.getPlugin(), 0L, 20L * getTickDuration());
    }

    public Minigame getMinigame()
    {
        return minigame;
    }

    public boolean timerActive()
    {
        return timerActive;
    }

    public void setTimerActive(boolean timerActive)
    {
        this.timerActive = timerActive;
    }



    public int getTickDuration()
    {
        return this.tickDuration;
    }

    public void setTickDuration(int tickDuration)
    {
        this.tickDuration = tickDuration;
    }
}
