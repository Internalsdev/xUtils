package com.verenitymc.xutil.minigame.queue;

import com.verenitymc.xutil.Core;
import com.verenitymc.xutil.minigame.event.queue.ServerQueueJoinEvent;
import com.verenitymc.xutil.network.query.OnlineCounter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

/**
 * Created by Mat
 *
 * Acts as a queue handler, you can create separate ones
 * for separate servers or just create different instances of it
 * for whatever reason.
 */
public class ServerQueue {

    private static HashSet<ServerQueue> queueList = new HashSet<>();
    public static HashSet<ServerQueue> getAllQueues(){return queueList;}

    private String targetServer; //The server to be sent to
    private List<UUID> queueMembers; //The members inside this queue
    private int checkDuration; //Time in which to query the target server
    private int maxPlayers; //Players to query against on the target server.
    private BukkitTask queueTask; //The timer in which to query.

    public ServerQueue(String targetServer, int checkDuration, int maxPlayers)
    {
        this.targetServer = targetServer;
        this.checkDuration = checkDuration;
        this.maxPlayers = maxPlayers;
        this.queueMembers = new ArrayList<>();
        getAllQueues().add(this);
        startQueueTimer();
    }


    public String getTargetServer()
    {
        return this.targetServer;
    }

    public List<UUID> getQueue()
    {
        return this.queueMembers;
    }

    public int getCheckDuration()
    {
        return checkDuration;
    }

    public int getMaxPlayers()
    {
        return maxPlayers;
    }


    /**
     * Add a player to the queue, if the player has a queue already
     * he is removed from that queue, the remove queue event is called
     * @param player
     */
    public void addToQueue(Player player)
    {
        ServerQueue previousQueues = getServerQueue(player);
        ServerQueueJoinEvent event = new ServerQueueJoinEvent(player, this, previousQueues);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled())
        {
            getQueue().add(player.getUniqueId());
        }
    }



    public void removeFromQueue(Player p)
    {
        //TODO CALL THE SERVERQUEUE LEAVE EVENT CORRECTLY
    }


    private void startQueueTimer()
    {
        queueTask = new BukkitRunnable()
        {
            public void run()
            {
                //TODO CONSISTENTLY QUERY SERVER DEPENDENT ON DATABASE TOKEN, NEED PORT OF SERVER.
            }
        }.runTaskTimerAsynchronously(Core.getPlugin(), 20L, 20L * getCheckDuration());
    }


    /**
     * The main method of getting the queue a player is in
     * To check if a player is in queue just check getQueue != null
     * @param p - Player to check.
     * @return - The target server queue, if not it's null.
     */
    public static ServerQueue getServerQueue(Player p)
    {
        return getAllQueues().stream().filter(q -> q.getQueue().contains(p.getUniqueId())).findFirst().orElse(null);
    }


    /**
     * Get a server queue by name.
     * @param serverName - Target server, not case sensitive.
     * @return - The target server queue.
     */
    public static ServerQueue getServerQueue(String serverName)
    {
        return getAllQueues().stream().filter(q -> q.getTargetServer().equalsIgnoreCase(serverName)).findFirst().orElse(null);
    }


    /**
     * Clean up singular server queue
     */
    public void removeQueue()
    {
        getAllQueues().remove(this);
        queueTask.cancel();
        queueTask = null;
    }

    /**
     * Remove all the queues in the game.
     */
    public void cleanUpQueues()
    {
        getAllQueues().forEach(serverQueue -> serverQueue.removeQueue());
    }

}
